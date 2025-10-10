package qiuqiu.service.impl.actors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import qiuqiu.enums.ActionEnum;
import qiuqiu.model.ActionContext;
import qiuqiu.model.DialogAction;
import qiuqiu.util.DateUtil;
import qiuqiu.util.DialogUtil;
import qiuqiu.util.HttpUtil;
import qiuqiu.util.WxUtil;

import java.io.ByteArrayInputStream;

/**
 * @author Jing Tao
 * @date 2025/9/28 16:48
 */
@Component
public class AddDailyImgActor implements Actor {

    @Override
    public void act(ActionContext context) {
        DialogAction dialogAction = DialogUtil.getCurDialogStep(context.getToUserName());
        int step = dialogAction.getStep();
        if (step == 1) {
            context.setResp("请上传照片");
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
            context.setResp("请添加备注");
        } else if (step == 3) {
            context.setResp("添加成功");
            DialogUtil.terminateDialog(context.getToUserName());
        }
    }

    @Override
    public ActionEnum getActionType() {
        return ActionEnum.ADD_DAILY_IMAGE;
    }
}
