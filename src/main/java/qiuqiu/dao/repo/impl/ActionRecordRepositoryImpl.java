package qiuqiu.dao.repo.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import qiuqiu.dao.mapper.ActionRecordMapper;
import qiuqiu.dao.po.ActionRecordPO;
import qiuqiu.dao.repo.ActionRecordRepository;
import qiuqiu.model.QueryActionRecordCondition;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Jing Tao
 * @date 2025/10/11 11:25
 */
@Repository
@Primary
public class ActionRecordRepositoryImpl implements ActionRecordRepository {

    @Resource
    private ActionRecordMapper actionRecordMapper;

    @Override
    public void save(ActionRecordPO actionRecordPO) {
        if (actionRecordPO.getId() == null) {
            actionRecordMapper.insert(actionRecordPO);
        } else {
            actionRecordMapper.updateById(actionRecordPO);
        }
    }

    @Override
    public List<ActionRecordPO> query(QueryActionRecordCondition condition) {
        LambdaQueryWrapper<ActionRecordPO> queryWrapper = new LambdaQueryWrapper<>();
        if (condition.getActionId() != null) {
            queryWrapper.eq(ActionRecordPO::getActionId, condition.getActionId());
        }
        if (StringUtils.isNotBlank(condition.getOperator())) {
            queryWrapper.eq(ActionRecordPO::getOperator, condition.getOperator());
        }
        queryWrapper.orderBy(true, false, ActionRecordPO::getCreatedAt);
        Page<ActionRecordPO> page = new Page<>(1, condition.getLimit());
        return actionRecordMapper.selectPage(page, queryWrapper).getRecords();
    }
}
