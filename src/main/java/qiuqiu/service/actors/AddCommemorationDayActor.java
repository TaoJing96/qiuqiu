package qiuqiu.service.actors;

import org.springframework.stereotype.Component;
import qiuqiu.enums.ActionEnum;
import qiuqiu.model.ActionContext;

/**
 * @author Jing Tao
 * @date 2025/9/28 16:48
 */
@Component
public class AddCommemorationDayActor implements Actor {

    @Override
    public void act(ActionContext context) {
    }

    @Override
    public ActionEnum getActionType() {
        return ActionEnum.ADD_COMMEMORATION_DAY;
    }
}
