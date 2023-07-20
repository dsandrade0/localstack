package info.dsandrade.info.LocalStack.converter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TopicMessage(
    @JsonProperty("MessageId")
    String messageId,
    @JsonProperty("Message")
    String body
) {
}
