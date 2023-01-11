package com.tencent.wxcloudrun.controller;

import com.google.gson.Gson;
import com.tencent.wxcloudrun.dto.TextRequest;
import com.tencent.wxcloudrun.enums.MaterialEnum;
import com.tencent.wxcloudrun.util.XmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Jing Tao
 * @date 2023/1/11 18:31
 */
@RestController
@RequestMapping
@Slf4j
public class WxController {

    @RequestMapping(value = "/msg", method = RequestMethod.POST, produces = {"application/xml;charset=UTF-8"})
    public void receiveMessage(@RequestBody String xml, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        Map<String, Object> params = XmlUtil.xmlStrToMap(xml);
        log.info("xml:{}end", xml);
        log.info("params:{}", new Gson().toJson(params));
        // 获取发送者ID
        String fromUserName = (String) params.get("FromUserName");
        // 获取消息类型
        String msgType = (String) params.get("MsgType");
        if (MaterialEnum.TEXT.getType().equals(msgType)) {
            // 用于储存文本或语音转换文本结果
            String content;
            TextRequest message = (TextRequest) XmlUtil.mapToBean(params, TextRequest.class);
            content = message.getContent();
            request.setAttribute("message", content);
            request.setAttribute("sender", fromUserName);
            request.getRequestDispatcher("/message").forward(request, response);
        }
    }
}
