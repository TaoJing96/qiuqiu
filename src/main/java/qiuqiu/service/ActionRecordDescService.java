package qiuqiu.service;

import qiuqiu.model.ActionRecordDO;
import qiuqiu.model.QueryActionRecordCondition;

import java.util.List;

/**
 * @author Jing Tao
 * @date 2025/10/11 11:16
 */
public interface ActionRecordDescService {

    void save(ActionRecordDO actionRecordDO);

    List<ActionRecordDO> query(QueryActionRecordCondition condition);
}
