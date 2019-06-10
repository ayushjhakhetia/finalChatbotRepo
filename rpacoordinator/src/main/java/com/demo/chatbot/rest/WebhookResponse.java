package com.demo.chatbot.rest;

import java.util.List;

import com.demo.chatbot.model.FollowUpEventInput;
import com.demo.chatbot.model.FulfillmentMessages;
import com.demo.chatbot.model.OutputContexts;
import com.demo.chatbot.model.Text;

public class WebhookResponse {
    private String fulfillmentText = " Default fulfillment text";
    private String source = "impetus-hook(Default source)";
    private List<OutputContexts> outputContexts;
    private List<FulfillmentMessages> fulfillmentMessages;
    private FollowUpEventInput followupEventInput;

    public List<FulfillmentMessages> getFulfillmentMessages() {
        return fulfillmentMessages;
    }

    public void setFulfillmentMessages(List<FulfillmentMessages> fulfillmentMessages) {
        this.fulfillmentMessages = fulfillmentMessages;
    }

    public FollowUpEventInput getFollowupEventInput() {
        return followupEventInput;
    }

    public void setFollowupEventInput(FollowUpEventInput followupEventInput) {
        this.followupEventInput = followupEventInput;
    }

    public final String getFulfillmentText() {
        return fulfillmentText;
    }

    public final void setFulfillmentText(String fulfillmentText) {
        this.fulfillmentText = fulfillmentText;
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
    }

    public WebhookResponse(String fulfillmentText) {
        this.fulfillmentText = fulfillmentText;
    }

    public WebhookResponse() {
    }

}