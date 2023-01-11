package qiuqiu.service.impl;

import qiuqiu.dto.BaseResponseMessage;
import qiuqiu.service.CacheService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jing Tao
 * @date 2023/1/11 21:36
 */
@Service
public class CacheServiceImpl implements CacheService {

    private Map<String, Object> cache = new HashMap<>();

    @Override
    public boolean putMsgResp(String msgId, BaseResponseMessage resp) {
        cache.put(msgId, resp);
        return true;
    }

    @Override
    public BaseResponseMessage getMsgResp(String msgId) {
        return (BaseResponseMessage) cache.get(msgId);
    }
}
