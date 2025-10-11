package qiuqiu.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import qiuqiu.dao.po.ActionRecordPO;
import qiuqiu.dao.repo.ActionRecordRepository;
import qiuqiu.model.ActionRecordDO;
import qiuqiu.model.QueryActionRecordCondition;
import qiuqiu.service.ActionRecordService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Jing Tao
 * @date 2025/10/11 11:17
 */
@Service
public class ActionRecordServiceImpl implements ActionRecordService {

    @Resource
    private ActionRecordRepository actionRecordRepository;

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public void save(ActionRecordDO actionRecordDO) {
        Map<String, ActionRecordRepository> beansOfType = applicationContext.getBeansOfType(ActionRecordRepository.class);
        ActionRecordPO actionRecordPO = ActionRecordDO.toPO(actionRecordDO);
        actionRecordRepository.save(actionRecordPO);
        actionRecordDO.setId(actionRecordPO.getId());
    }

    @Override
    public List<ActionRecordDO> query(QueryActionRecordCondition condition) {
        List<ActionRecordPO> actionRecordPOS = actionRecordRepository.query(condition);
        if (CollectionUtils.isEmpty(actionRecordPOS)) {
            return null;
        }
        List<ActionRecordDO> actionRecordDOS = new ArrayList<>();
        for (ActionRecordPO po : actionRecordPOS) {
            ActionRecordDO actionRecordDO = ActionRecordDO.fromPO(po);
            actionRecordDOS.add(actionRecordDO);
        }
        return actionRecordDOS;
    }
}
