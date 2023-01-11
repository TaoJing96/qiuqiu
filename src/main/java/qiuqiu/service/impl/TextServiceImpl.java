package qiuqiu.service.impl;

import qiuqiu.dto.TextRequest;
import qiuqiu.service.OpenAiService;
import qiuqiu.service.TextService;
import org.springframework.stereotype.Service;
import qiuqiu.dto.TextResponse;

import javax.annotation.Resource;


/**
 * TextMessageServiceImpl class
 *
 * @author BowenWang
 * @date 2019/08/03
 */
@Service
public class TextServiceImpl implements TextService {

    @Resource
    private OpenAiService openAiService;

    @Override
    public void saveText(TextRequest requestTextMessage) {
        //todo
    }

    @Override
    public TextResponse returnText(String content) {
        TextResponse responseText = new TextResponse();
        String complete = openAiService.complete(content);
        responseText.setContent(complete);
        return responseText;
    }
}
