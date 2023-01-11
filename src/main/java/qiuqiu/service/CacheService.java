package qiuqiu.service;

import qiuqiu.dto.BaseResponseMessage;

/**
 * @author Jing Tao
 * @date 2023/1/11 21:35
 */
public interface CacheService {

    boolean putMsgResp(String msgId, BaseResponseMessage resp);

    BaseResponseMessage getMsgResp(String msgId);
}
