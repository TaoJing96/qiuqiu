package qiuqiu.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Jing Tao
 * @date 2025/9/30 15:30
 */
@TableName("ActionRecordDesc")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActionRecordDescPO {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer actionRecordId;
    private String detail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
