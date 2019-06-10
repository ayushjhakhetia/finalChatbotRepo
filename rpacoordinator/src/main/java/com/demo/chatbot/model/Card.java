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
@JsonPropertyOrder({ "title", "subtitle", "imageUri", "buttons" })

public class Card {
    @JsonProperty("title")
    private String title;

    @JsonProperty("subtitle")
    private String subtitle;

    @JsonProperty("imageUri")
    private String imageUri;

    @JsonProperty("buttons")
    private List<Buttons> buttons;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    
    @JsonProperty("title")
    public final String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public final void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("subtitle")
    public final String getSubtitle() {
        return subtitle;
    }

    @JsonProperty("subtitle")
    public final void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @JsonProperty("imageUri")
    public final String getImageUri() {
        return imageUri;
    }

    @JsonProperty("imageUri")
    public final void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    @JsonProperty("buttons")
    public final List<Buttons> getButtons() {
        return buttons;
    }

    @JsonProperty("buttons")
    public final void setButtons(List<Buttons> buttons) {
        this.buttons = buttons;
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
