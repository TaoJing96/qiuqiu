package qiuqiu.service.impl;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Service;
import qiuqiu.dto.TextRequest;
import qiuqiu.dto.TextResponse;
import qiuqiu.enums.ActionEnum;
import qiuqiu.model.ActionContext;
import qiuqiu.model.DialogAction;
import qiuqiu.service.TextService;
import qiuqiu.service.impl.actors.Actor;
import qiuqiu.service.impl.actors.ActorRegistry;
import qiuqiu.util.DialogUtil;

import javax.annotation.Resource;
import java.util.Map;


/**
 * TextMessageServiceImpl class
 *
 * @author BowenWang
 * @date 2019/08/03
 */
@Service
public class TextServiceImpl implements TextService {

    private static final Map<String, ActionEnum> USER_TO_ACTIONS = ImmutableMap.of("oiOx76PwDdjbOR1Ai2MQ0sm3pFCs", ActionEnum.ADD_COMMEMORATION_DAY);

    @Resource
    private ActorRegistry actorRegistry;

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

    private String buildActionList() {
        StringBuilder sb = new StringBuilder();
        for (ActionEnum actionEnum : ActionEnum.values()) {
            sb.append(actionEnum.getNum()).append(": ").append(actionEnum.getCnCode()).append("\n");
        }
        return sb.toString();
    }

    private String validateActionAndReturnErrorMsg(ActionEnum actionEnum, String toUserName) {
        if (!USER_TO_ACTIONS.containsKey(toUserName)) {
            return "游客";
        }
        if (!USER_TO_ACTIONS.get(toUserName).equals(actionEnum)) {
            return "没有权限";
        }
        return null;
    }
}
