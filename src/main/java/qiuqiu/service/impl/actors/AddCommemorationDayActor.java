package qiuqiu.service.impl.actors;

import org.springframework.stereotype.Component;
import qiuqiu.enums.ActionEnum;

/**
 * @author Jing Tao
 * @date 2025/9/28 16:48
 */
@Component
public class AddCommemorationDayActor implements Actor {

    @Override
    public String act(String content, String user) {
        return content + "添加成功";
    }

    @Override
    public ActionEnum getActionType() {
        return ActionEnum.ADD_COMMEMORATION_DAY;
    }
}
