package com.tencent.wxcloudrun.controller;

import com.google.gson.Gson;
import com.tencent.wxcloudrun.dto.BaseResponseMessage;
import com.tencent.wxcloudrun.dto.TextRequest;
import com.tencent.wxcloudrun.enums.MaterialEnum;
import com.tencent.wxcloudrun.service.TextService;
import com.tencent.wxcloudrun.util.XmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @author Jing Tao
 * @date 2023/1/11 18:31
 */
@RestController
@RequestMapping(value = "/msg")
@Slf4j
public class WxController {

    @Resource
    private TextService textService;

    @RequestMapping(method = RequestMethod.POST, produces = {"application/xml;charset=UTF-8"})
    public void receiveMessage(@RequestBody String xml, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        Map<String, Object> params = XmlUtil.xmlStrToMap(xml);
        log.info("xml:{}", xml);
        // 获取发送者ID
        String fromUserName = (String) params.get("FromUserName");
        // 获取消息类型
        String msgType = (String) params.get("MsgType");
        PrintWriter out = response.getWriter();
        if (MaterialEnum.TEXT.getType().equals(msgType)) {
            // 用于储存文本或语音转换文本结果
            String content;
            TextRequest message = (TextRequest) XmlUtil.mapToBean(params, TextRequest.class);
            content = message.getContent();
            BaseResponseMessage baseResponseMessage = replayMessage(MaterialEnum.TEXT, "你发送了:\n " + content, fromUserName);
            out.write(new Gson().toJson(baseResponseMessage));
        }
        out.close();
    }

        private BaseResponseMessage replayMessage(MaterialEnum type, Object obj, String toUserName) {
        BaseResponseMessage responseMessage = null;
        if (MaterialEnum.TEXT.equals(type)) {
            responseMessage = textService.returnText((String) obj);
        }
//        else if (MaterialEnum.IMAGE.equals(type)) {
//            responseMessage = imageService.returnImage((String) obj);
//        } else if (MaterialEnum.VIDEO.equals(type)) {
//            responseMessage = videoService.returnVideo((Video) obj);
//        } else if (MaterialEnum.VOICE.equals(type)) {
//            responseMessage = voiceService.returnVoice((String) obj);
//        } else if (MaterialEnum.NEWS.equals(type)) {
//            responseMessage = newsServce.returnNews((Item) obj);
//        }
        // 配置基本回复属性
        if (responseMessage != null) {
            responseMessage.setToUserName(toUserName);
            responseMessage.setFromUserName("gh_fffa730a3a73");
            responseMessage.setCreateTime(String.valueOf(System.currentTimeMillis()));
        }
        return responseMessage;
    }
}
