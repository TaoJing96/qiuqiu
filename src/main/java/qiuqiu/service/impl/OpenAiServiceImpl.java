package qiuqiu.service.impl;

import qiuqiu.dto.OpenAiCompletionReq;
import qiuqiu.dto.OpenAiCompletionResp;
import qiuqiu.service.OpenAiService;
import qiuqiu.util.HttpUtil;
import qiuqiu.util.JsonUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jing Tao
 * @date 2023/1/11 22:01
 */
@Service
public class OpenAiServiceImpl implements OpenAiService {

    private String openAiCompletionUrl = "https://api.openai.com/v1/completions";

    @Override
    public String complete(String word) {
        OpenAiCompletionReq openAiCompletionReq = buildOpenAiCompletionReq(word);
        Map<String, String> header = buildOpenAiHeader();
        OpenAiCompletionResp openAiCompletionResp = HttpUtil.post(OpenAiCompletionResp.class, JsonUtil.toJson(openAiCompletionReq), openAiCompletionUrl, header);
        return openAiCompletionResp.getChoices().get(0).getText();
    }

    private Map<String, String> buildOpenAiHeader() {
        Map<String, String> map = new HashMap<>();
        map.put("Content-Type", "application/json");
        map.put("Authorization", "Bearer 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");
        return map;
    }

    private OpenAiCompletionReq buildOpenAiCompletionReq(String word) {
        return new OpenAiCompletionReq()
                .setModel("text-davinci-003")
                .setPrompt(word)
                .setMaxTokens(800)
                .setTemperature(0.6)
                .setTopP(1)
                .setFrequencyPenalty(0.0)
                .setPresencePenalty(0.6);
    }
}
