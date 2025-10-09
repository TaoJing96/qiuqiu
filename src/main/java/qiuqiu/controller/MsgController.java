package qiuqiu.controller;

import com.google.gson.Gson;
import qiuqiu.dto.BaseResponseMessage;
import qiuqiu.enums.MaterialEnum;
import qiuqiu.service.CacheService;
import qiuqiu.service.ImageService;
import qiuqiu.service.TextService;
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

    @Resource
    private ImageService imageService;

    @Resource
    private CacheService cacheService;

    @PostMapping(produces = {"application/xml;charset=UTF-8"})
    public Object processMessage(HttpServletRequest request,
                                 HttpServletResponse response) {
        // 获取消息类型
        String message = (String) request.getAttribute("message");
        String fromUserName = (String) request.getAttribute("sender");
        String msgId = (String) request.getAttribute("msgId");
        String type = (String) request.getAttribute("type");
        log.info("sender:{}, message:{}, msgId:{}", fromUserName, message, msgId);
        BaseResponseMessage msgResp = cacheService.getMsgResp(msgId);
        if (msgResp != null) {
            return msgResp;
        }
        BaseResponseMessage responseMessage = replayMessage(type, message, fromUserName, request);
        cacheService.putMsgResp(msgId, responseMessage);
        return responseMessage;
    }

    private BaseResponseMessage replayMessage(String type, Object obj, String toUserName, HttpServletRequest request) {
        BaseResponseMessage responseMessage = null;
        if (MaterialEnum.TEXT.getType().equals(type)) {
            responseMessage = textService.returnText((String) obj, toUserName);
        } else if (MaterialEnum.IMAGE.getType().equals(type)) {
            String picUrl = (String) request.getAttribute("picUrl");
            responseMessage = imageService.replyImage(picUrl, toUserName);
        }
//        else if (MaterialEnum.VIDEO.equals(type)) {
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
