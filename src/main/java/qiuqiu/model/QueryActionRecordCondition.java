package qiuqiu.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Jing Tao
 * @date 2025/10/11 11:20
 */
@Data
@Accessors(chain = true)
public class QueryActionRecordCondition {

    private String operator;
    private Integer actionId;
    private int limit = 1;
}
