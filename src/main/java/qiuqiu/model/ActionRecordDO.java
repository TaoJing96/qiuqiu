package qiuqiu.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import qiuqiu.dao.po.ActionRecordPO;
import qiuqiu.util.JsonUtil;

import java.time.LocalDateTime;

/**
 * @author Jing Tao
 * @date 2025/9/30 15:30
 */
@Data
public class ActionRecordDO {

    private Integer id;
    private Integer actionId;
    private String operator;
    private ActionRecordDetailDO actionRecordDetailDO;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ActionRecordDO fromPO(ActionRecordPO po) {
        ActionRecordDO actionRecordDO = new ActionRecordDO();
        actionRecordDO.setId(po.getId());
        actionRecordDO.setActionId(po.getActionId());
        actionRecordDO.setOperator(po.getOperator());
        actionRecordDO.setCreatedAt(po.getCreatedAt());
        actionRecordDO.setUpdatedAt(po.getUpdatedAt());
        if (StringUtils.isNotBlank(po.getDetail())) {
            actionRecordDO.setActionRecordDetailDO(JsonUtil.fromJson(po.getDetail(), ActionRecordDetailDO.class));
        }
        return actionRecordDO;
    }

    public static ActionRecordPO toPO(ActionRecordDO actionRecordDO) {
        ActionRecordPO actionRecordPO = new ActionRecordPO();
        actionRecordPO.setId(actionRecordDO.getId());
        actionRecordPO.setActionId(actionRecordDO.getActionId());
        actionRecordPO.setOperator(actionRecordDO.getOperator());
        actionRecordPO.setCreatedAt(actionRecordDO.getCreatedAt());
        if (actionRecordDO.getActionRecordDetailDO() != null) {
            actionRecordPO.setDetail(JsonUtil.toJson(actionRecordDO.getActionRecordDetailDO()));
        }
        actionRecordPO.setCreatedAt(actionRecordDO.getCreatedAt());
        actionRecordPO.setUpdatedAt(actionRecordDO.getUpdatedAt());
        return actionRecordPO;
    }
}
