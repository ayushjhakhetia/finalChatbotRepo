package com.demo.chatbot.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "text", "postback" })

public class Buttons {

    @JsonProperty("text")
    private String text;

    @JsonProperty("postback")
    private String postback;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("text")
    public final String getText() {
        return text;
    }

    @JsonProperty("text")
    public final void setText(String text) {
        this.text = text;
    }

    @JsonProperty("postback")
    public final String getPostback() {
        return postback;
    }

    @JsonProperty("postback")
    public final void setPostback(String postback) {
        this.postback = postback;
    }

    @JsonAnyGetter
    public final Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    @JsonAnySetter
    public final void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

}
