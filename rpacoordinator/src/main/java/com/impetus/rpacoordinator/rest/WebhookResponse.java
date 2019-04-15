package com.impetus.rpacoordinator.rest;

import java.util.List;

import com.impetus.rpacoordinator.model.FullfillmentMessages;
import com.impetus.rpacoordinator.model.OutputContexts;
import com.impetus.rpacoordinator.model.Text;

public class WebhookResponse {
    private String fulfillmentText;
    private List<Text> fulfillmentMessages;

    private String source = "impetus-hook";
    private List<OutputContexts> outputContexts;

    public final String getFulfillmentText() {
        return fulfillmentText;
    }

    public final void setFulfillmentText(String fulfillmentText) {
        this.fulfillmentText = fulfillmentText;
    }

    public final List<Text> getFulfillmentMessages() {
        return fulfillmentMessages;
    }

    public final void setFulfillmentMessages(List<Text> fulfillmentMessages) {
        this.fulfillmentMessages = fulfillmentMessages;
    }

    public final String getSource() {
        return source;
    }

    public final void setSource(String source) {
        this.source = source;
    }

    public final List<OutputContexts> getOutputContexts() {
        return outputContexts;
    }

    public final void setOutputContexts(List<OutputContexts> outputContexts) {
        this.outputContexts = outputContexts;
    }

    public WebhookResponse(String speech, List<Text> displayText) {
        this.fulfillmentText = speech;
        this.fulfillmentMessages = displayText;
    }

    public WebhookResponse(String fulfillmentText) {
        this.fulfillmentText = fulfillmentText;
    }
    public WebhookResponse() {
    }

}
