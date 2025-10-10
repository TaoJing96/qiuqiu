package qiuqiu.model;

import lombok.Data;
import qiuqiu.enums.ActionEnum;

/**
 * @author Jing Tao
 * @date 2025/10/10 08:52
 */
@Data
public class ActionContext {

    private String content;
    private String toUserName;
    private String picUrl;
    private ActionEnum lastAction;

    private String resp;
}
