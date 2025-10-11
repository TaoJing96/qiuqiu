package qiuqiu.service.actors;

import qiuqiu.model.ActionContext;
import qiuqiu.model.ActionRecordDO;
import qiuqiu.model.ActionRecordDetailDO;
import qiuqiu.model.QueryActionRecordCondition;
import qiuqiu.service.ActionRecordService;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author Jing Tao
 * @date 2025/10/11 15:05
 */
public abstract class CommonActor implements Actor {

    @Resource
    protected ActionRecordService actionRecordService;

    protected ActionRecordDO buildActionRecord(ActionContext context) {

        ActionRecordDO actionRecordDO = new ActionRecordDO();
        actionRecordDO.setActionId(Integer.parseInt(getActionType().getNum()));
        actionRecordDO.setOperator(context.getToUserName());
        actionRecordDO.setCreatedAt(LocalDateTime.now());
        actionRecordDO.setUpdatedAt(LocalDateTime.now());
        actionRecordDO.setActionRecordDetailDO(new ActionRecordDetailDO());
        return actionRecordDO;
    }

    protected ActionRecordDO getLatestActionRecord(ActionContext context) {
        return actionRecordService.query(new QueryActionRecordCondition()
                .setOperator(context.getToUserName())
                .setActionId(Integer.parseInt(getActionType().getNum()))
                .setLimit(1))
                .get(0);
    }
}
