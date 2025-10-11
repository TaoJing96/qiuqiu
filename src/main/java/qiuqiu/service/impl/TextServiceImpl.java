package qiuqiu.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import qiuqiu.dto.TextRequest;
import qiuqiu.dto.TextResponse;
import qiuqiu.enums.ActionEnum;
import qiuqiu.model.ActionContext;
import qiuqiu.model.DialogAction;
import qiuqiu.service.TextService;
import qiuqiu.service.actors.Actor;
import qiuqiu.util.DialogUtil;


/**
 * TextMessageServiceImpl class
 *
 * @author BowenWang
 * @date 2019/08/03
 */
@Service
@Slf4j
public class TextServiceImpl extends CommonService implements TextService {

    @Override
    public void saveText(TextRequest requestTextMessage) {
        //todo
    }

    @Override
    public TextResponse returnText(String content, String toUserName) {
        TextResponse responseText = new TextResponse();
        DialogAction dialogAction = DialogUtil.getCurDialogStep(toUserName);
        ActionEnum currentAction;
        if (dialogAction == null) {
            currentAction = ActionEnum.findByNum(content);
            if (currentAction == null) {
                log.info("没有找到对应的操作, content:{}", content);
                responseText.setContent(buildActionList());
                return responseText;
            } else {
                validateActionAndReturnErrorMsg(currentAction, toUserName);
                DialogUtil.initDialog(toUserName, currentAction);
            }
        } else {
            currentAction = dialogAction.getCurAction();
        }
        Actor actor = actorRegistry.getActor(currentAction);

        ActionContext actionContext = new ActionContext();
        actionContext.setContent(content);
        actionContext.setToUserName(toUserName);
        actor.act(actionContext);

        responseText.setContent(actionContext.getResp());
        return responseText;
    }
}
