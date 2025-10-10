package qiuqiu.model;

import lombok.Data;
import qiuqiu.enums.ActionEnum;

/**
 * @author Jing Tao
 * @date 2025/10/10 08:49
 */
@Data
public class DialogAction {

    private ActionEnum curAction;
    private int step;
    private long ts;
}
