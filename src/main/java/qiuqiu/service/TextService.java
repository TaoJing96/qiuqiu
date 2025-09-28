package qiuqiu.service;

import qiuqiu.dto.TextRequest;
import qiuqiu.dto.TextResponse;

/**
 * TextMessageService class
 *
 * @author BowenWang
 * @date 2019/08/03
 */

public interface TextService {

    /**
     * 保存文本消息
     *
     * @param requestTextMessage
     */
    void saveText(TextRequest requestTextMessage);

    /**
     * 返回文本消息
     *
     * @param content
     * @param toUserName
     * @return
     */
    TextResponse returnText(String content, String toUserName);
}
