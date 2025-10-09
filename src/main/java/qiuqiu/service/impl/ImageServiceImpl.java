package qiuqiu.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import qiuqiu.dto.TextResponse;
import qiuqiu.service.ImageService;
import qiuqiu.util.HttpUtil;
import qiuqiu.util.WxUtil;

import java.io.ByteArrayInputStream;

/**
 * @author Jing Tao
 * @date 2025/10/9 17:00
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public TextResponse replyImage(String mediaUrl, String toUserName) {
        byte[] bytes = HttpUtil.downloadFile(mediaUrl);
        WxUtil.uploadInputStream(new ByteArrayInputStream(bytes), "replyImage", "image/jpeg");
        TextResponse textResponse = new TextResponse();
        textResponse.setContent("保存成功");
        return textResponse;
    }
}
