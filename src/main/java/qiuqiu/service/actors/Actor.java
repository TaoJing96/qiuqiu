package qiuqiu.service.actors;

import qiuqiu.enums.ActionEnum;
import qiuqiu.model.ActionContext;

/**
 * @author Jing Tao
 * @date 2025/9/28 15:57
 */
public interface Actor {

    void act(ActionContext context);

    ActionEnum getActionType();
}
