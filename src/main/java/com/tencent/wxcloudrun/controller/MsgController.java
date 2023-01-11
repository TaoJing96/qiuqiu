package com.tencent.wxcloudrun.controller;

import com.google.gson.Gson;
import com.tencent.wxcloudrun.dto.BaseResponseMessage;
import com.tencent.wxcloudrun.enums.MaterialEnum;
import com.tencent.wxcloudrun.service.TextService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Jing Tao
 * @date 2023/1/11 17:55
 */
@RestController
@RequestMapping(value = "/message")
@Slf4j
public class MsgController {

    @Resource
    private TextService textService;

    @PostMapping(produces = {"application/xml;charset=UTF-8"})
    public Object processMessage(HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        // 获取消息类型
        String message = (String) request.getAttribute("message");
        String fromUserName = (String) request.getAttribute("sender");

        return replayMessage(MaterialEnum.TEXT, message, fromUserName);
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
        log.info("responseMessage:{}", new Gson().toJson(responseMessage));
        return responseMessage;
    }
}
