package qiuqiu.enums;

import lombok.Getter;

/**
 * @author Jing Tao
 * @date 2025/9/28 15:46
 */
@Getter
public enum ActionEnum {

    ADD_COMMEMORATION_DAY("添加纪念日"),
    ;

    private String cnCode;


    ActionEnum(String cnCode) {
        this.cnCode = cnCode;
    }
}
