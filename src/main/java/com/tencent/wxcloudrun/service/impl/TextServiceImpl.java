package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.dto.TextRequest;
import com.tencent.wxcloudrun.service.TextService;
import org.springframework.stereotype.Service;
import com.tencent.wxcloudrun.dto.TextResponse;


/**
 * TextMessageServiceImpl class
 *
 * @author BowenWang
 * @date 2019/08/03
 */
@Service
public class TextServiceImpl implements TextService {


    @Override
    public void saveText(TextRequest requestTextMessage) {
        //todo
    }

    @Override
    public TextResponse returnText(String content) {
        TextResponse responseText = new TextResponse();
        responseText.setContent(content);
        return responseText;
    }
}
