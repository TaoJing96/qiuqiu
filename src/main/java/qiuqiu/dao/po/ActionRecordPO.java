package qiuqiu.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Jing Tao
 * @date 2025/9/30 15:30
 */
@TableName("ActionRecord")
@Data
public class ActionRecordPO {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("actionId")
    private Integer actionId;
    private String operator;
    private String detail;
    @TableField("createdAt")
    private LocalDateTime createdAt;
    @TableField("updatedAt")
    private LocalDateTime updatedAt;
}
