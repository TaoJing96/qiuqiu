package qiuqiu.dao.repo;

import qiuqiu.dao.po.ActionRecordPO;
import qiuqiu.model.QueryActionRecordCondition;

import java.util.List;

/**
 * @author Jing Tao
 * @date 2025/10/11 11:15
 */
public interface ActionRecordRepository {

    void save(ActionRecordPO actionRecordPO);

    List<ActionRecordPO> query(QueryActionRecordCondition condition);
}
