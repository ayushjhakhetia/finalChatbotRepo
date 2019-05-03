
package com.demo.chatbot.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "fulfillmentText",
    "source",
    "displayText",
    "messages"
})
public class Fulfillment {

    @JsonProperty("fulfillmentText")
    private String speech;
    @JsonProperty("source")
    private String source;
    @JsonProperty("displayText")
    private String displayText;
    @JsonProperty("messages")
    private List<Message> messages = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("fulfillmentText")
    public String getSpeech() {
        return speech;
    }

    @JsonProperty("fulfillmentText")
    public void setSpeech(String speech) {
        this.speech = speech;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("displayText")
    public String getDisplayText() {
        return displayText;
    }

    @JsonProperty("displayText")
    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    @JsonProperty("messages")
    public List<Message> getMessages() {
        return messages;
    }

    @JsonProperty("messages")
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
