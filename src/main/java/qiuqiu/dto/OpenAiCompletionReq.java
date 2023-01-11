package qiuqiu.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Jing Tao
 * @date 2023/1/11 22:00
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class OpenAiCompletionReq {

    @SerializedName("model")
    private String model;
    @SerializedName("prompt")
    private String prompt;
    @SerializedName("max_tokens")
    private Integer maxTokens;
    @SerializedName("temperature")
    private Double temperature;
    @SerializedName("top_p")
    private Integer topP;
    @SerializedName("frequency_penalty")
    private Double frequencyPenalty;
    @SerializedName("presence_penalty")
    private Double presencePenalty;
}
