package qiuqiu.util;

import lombok.extern.slf4j.Slf4j;
import qiuqiu.enums.ActionEnum;
import qiuqiu.model.DialogAction;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jing Tao
 * @date 2025/10/9 18:45
 */
@Slf4j
public class DialogUtil {

    private static final Map<String, DialogAction> USER_ID_TO_ACTION_AND_STEP = new ConcurrentHashMap<>();

    public static DialogAction getCurDialogStep(String userId) {
        DialogAction dialogAction = USER_ID_TO_ACTION_AND_STEP.get(userId);
        log.info("userId:{}, dialogAction:{}", userId, dialogAction);
        return dialogAction;
    }

    public static void incrementDialogStep(String userId) {
        DialogAction dialogAction = USER_ID_TO_ACTION_AND_STEP.get(userId);
        dialogAction.setStep(dialogAction.getStep() + 1);
    }

    public static void initDialog(String userId, ActionEnum actionEnum) {
        DialogAction dialogAction = new DialogAction();
        dialogAction.setStep(1);
        dialogAction.setTs(System.currentTimeMillis());
        dialogAction.setCurAction(actionEnum);
        USER_ID_TO_ACTION_AND_STEP.put(userId, dialogAction);
    }

    public static void terminateDialog(String userId) {
        USER_ID_TO_ACTION_AND_STEP.remove(userId);
    }
}
