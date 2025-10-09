package qiuqiu.enums;

import lombok.Getter;

/**
 * @author Jing Tao
 * @date 2025/9/28 15:46
 */
@Getter
public enum ActionEnum {

    ADD_COMMEMORATION_DAY(1, "添加纪念日"),
    ADD_DAILY_IMAGE_FOR_QQ(2, "给qq添加每日照片"),
    ADD_RECIPE(3, "添加食谱"),
    ;

    private String cnCode;
    private int num;


    private ActionEnum(int num, String cnCode) {
        this.cnCode = cnCode;
        this.num = num;
    }
}
