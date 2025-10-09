package qiuqiu.service;

import qiuqiu.dto.TextResponse;

/**
 * @author Jing Tao
 * @date 2025/10/9 16:55
 */
public interface ImageService {

    TextResponse replyImage(String mediaId, String toUserName);
}
