package qiuqiu.service.actors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import qiuqiu.enums.ActionEnum;
import qiuqiu.model.ActionContext;
import qiuqiu.model.ActionRecordDO;
import qiuqiu.model.DialogAction;
import qiuqiu.util.DateUtil;
import qiuqiu.util.DialogUtil;
import qiuqiu.util.HttpUtil;
import qiuqiu.util.WxUtil;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;

/**
 * @author Jing Tao
 * @date 2025/10/11 22:13
 */
@Component
public class AddRecipe  extends CommonActor {

    @Override
    public void act(ActionContext context) {
        DialogAction dialogAction = DialogUtil.getCurDialogStep(context.getToUserName());
        int step = dialogAction.getStep();
        if (step == 1) {
            context.setResp("请输入菜名");
            DialogUtil.incrementDialogStep(context.getToUserName());
        } else if (step == 2) {
            String picUrl = context.getPicUrl();
            if (StringUtils.isBlank(picUrl)) {
                context.setResp("请上传照片");
                return;
            }
            byte[] bytes = HttpUtil.downloadFile(picUrl);
            String cosKey = DateUtil.getToday() + "/" + System.currentTimeMillis() + ".jpg";
            WxUtil.uploadInputStream(new ByteArrayInputStream(bytes), cosKey, "image/jpeg");
            DialogUtil.incrementDialogStep(context.getToUserName());

            ActionRecordDO actionRecordDO = buildActionRecord(context);
            actionRecordDO.getActionRecordDetailDO().setCosKey(cosKey);
            actionRecordService.save(actionRecordDO);

            context.setResp("请详细步骤");
        } else if (step == 3) {
            ActionRecordDO latestActionRecord = getLatestActionRecord(context);
            latestActionRecord.getActionRecordDetailDO().setNotes(context.getContent());
            latestActionRecord.setUpdatedAt(LocalDateTime.now());
            actionRecordService.save(latestActionRecord);

            context.setResp("添加成功");
            DialogUtil.terminateDialog(context.getToUserName());
        }
    }

    @Override
    public ActionEnum getActionType() {
        return ActionEnum.ADD_RECIPE;
    }
}
