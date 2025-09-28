package qiuqiu.service.impl;

import org.springframework.stereotype.Service;
import qiuqiu.dto.TextRequest;
import qiuqiu.dto.TextResponse;
import qiuqiu.enums.ActionEnum;
import qiuqiu.service.TextService;
import qiuqiu.service.impl.actors.Actor;
import qiuqiu.service.impl.actors.ActorRegistry;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * TextMessageServiceImpl class
 *
 * @author BowenWang
 * @date 2019/08/03
 */
@Service
public class TextServiceImpl implements TextService {

    private static final Map<String, ActionEnum> USER_TO_ACTIONS = Map.of("oiOx76PwDdjbOR1Ai2MQ0sm3pFCs", ActionEnum.ADD_COMMEMORATION_DAY);

    private static final Map<String, ActionEnum> USER_TO_LATEST_ACTION = new ConcurrentHashMap<>();

    @Resource
    private ActorRegistry actorRegistry;

    @Override
    public void saveText(TextRequest requestTextMessage) {
        //todo
    }

    @Override
    public TextResponse returnText(String content, String toUserName) {
        String resp = "无法理解，请先输入要执行的命令";
        ActionEnum currentAction = getCurrentAction(toUserName);
        if (currentAction == null) {
            currentAction = getAction(content, toUserName);
        }
        if (currentAction != null) {
            Actor actor = actorRegistry.getActor(currentAction);
            actor.act(content, toUserName);
        }
        TextResponse responseText = new TextResponse();
        responseText.setContent(resp);
        return responseText;
    }

    private ActionEnum getCurrentAction(String user) {
        return USER_TO_LATEST_ACTION.get(user);
    }

    private ActionEnum getAction(String content, String user) {
        for (ActionEnum actionEnum : ActionEnum.values()) {
            if (actionEnum.getCnCode().equals(content)) {
                validateActionAndReturnErrorMsg(actionEnum, user);
                return actionEnum;
            }
        }
        return null;
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
