package qiuqiu.service.impl;

import com.google.common.collect.ImmutableMap;
import qiuqiu.enums.ActionEnum;
import qiuqiu.service.impl.actors.ActorRegistry;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author Jing Tao
 * @date 2025/10/10 17:21
 */
public abstract class CommonService {

    private static final Map<String, ActionEnum> USER_TO_ACTIONS = ImmutableMap.of("oiOx76PwDdjbOR1Ai2MQ0sm3pFCs", ActionEnum.ADD_COMMEMORATION_DAY);

    @Resource
    protected ActorRegistry actorRegistry;

    protected String buildActionList() {
        StringBuilder sb = new StringBuilder();
        for (ActionEnum actionEnum : ActionEnum.values()) {
            sb.append(actionEnum.getNum()).append(": ").append(actionEnum.getCnCode()).append("\n");
        }
        return sb.toString();
    }

    protected String validateActionAndReturnErrorMsg(ActionEnum actionEnum, String toUserName) {
        if (!USER_TO_ACTIONS.containsKey(toUserName)) {
            return "游客";
        }
        if (!USER_TO_ACTIONS.get(toUserName).equals(actionEnum)) {
            return "没有权限";
        }
        return null;
    }
}
