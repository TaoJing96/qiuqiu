package qiuqiu.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import qiuqiu.dto.TextResponse;
import qiuqiu.enums.ActionEnum;
import qiuqiu.model.ActionContext;
import qiuqiu.model.DialogAction;
import qiuqiu.service.ImageService;
import qiuqiu.service.impl.actors.Actor;
import qiuqiu.util.DialogUtil;

/**
 * @author Jing Tao
 * @date 2025/10/9 17:00
 */
@Slf4j
@Service
public class ImageServiceImpl extends CommonService implements ImageService {

    @Override
    public TextResponse replyImage(String mediaUrl, String toUserName) {
        TextResponse responseText = new TextResponse();
        DialogAction dialogAction = DialogUtil.getCurDialogStep(toUserName);
        ActionEnum currentAction;
        if (dialogAction == null) {
            responseText.setContent(buildActionList());
            return responseText;
        } else {
            currentAction = dialogAction.getCurAction();
        }
        Actor actor = actorRegistry.getActor(currentAction);

        ActionContext actionContext = new ActionContext();
        actionContext.setPicUrl(mediaUrl);
        actionContext.setToUserName(toUserName);
        actor.act(actionContext);

        responseText.setContent(actionContext.getResp());
        return responseText;
    }
}
