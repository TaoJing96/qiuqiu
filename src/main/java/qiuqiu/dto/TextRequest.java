package qiuqiu.dto;

import lombok.Data;

/**
 * RequestText class
 * 请求文本消息
 *
 * @author BowenWang
 * @date 2019/08/03
 */
@Data
public class TextRequest extends BaseRequestMessage {

    /**
     * 文本消息内容
     */
    private String content;
}
