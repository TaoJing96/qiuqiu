package qiuqiu.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Jing Tao
 * @date 2025/9/30 15:30
 */
@Data
@TableName("Action")
public class Action {

    private Integer id;

    private String type;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
