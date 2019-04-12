package com.impetus.rpacoordinator.rest;

public class WebhookResponse {
    private final String fulfillmentText;
    private final String displayText;

    private final String source = "impetus-hook";

    public WebhookResponse(String speech, String displayText) {
        this.fulfillmentText = speech;
        this.displayText = displayText;
    }

    public String getSpeech() {
        return fulfillmentText;
    }

    public String getDisplayText() {
        return displayText;
    }

    public String getSource() {
        return source;
    }
}
