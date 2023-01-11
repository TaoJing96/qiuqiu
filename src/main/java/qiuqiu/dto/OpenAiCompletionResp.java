package qiuqiu.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Jing Tao
 * @date 2023/1/11 21:59
 */
@NoArgsConstructor
@Data
public class OpenAiCompletionResp {

    @SerializedName("id")
    private String id;
    @SerializedName("object")
    private String object;
    @SerializedName("created")
    private Integer created;
    @SerializedName("model")
    private String model;
    @SerializedName("choices")
    private List<Choices> choices;
    @SerializedName("usage")
    private Usage usage;

    @NoArgsConstructor
    @Data
    public static class Usage {
        @SerializedName("prompt_tokens")
        private Integer promptTokens;
        @SerializedName("completion_tokens")
        private Integer completionTokens;
        @SerializedName("total_tokens")
        private Integer totalTokens;
    }

    @NoArgsConstructor
    @Data
    public static class Choices {
        @SerializedName("text")
        private String text;
        @SerializedName("index")
        private Integer index;
        @SerializedName("logprobs")
        private Object logprobs;
        @SerializedName("finish_reason")
        private String finishReason;
    }
}
