package com.demo.chatbot.rest;

import java.util.List;

import com.demo.chatbot.model.FollowUpEventInput;
import com.demo.chatbot.model.FulfillmentMessages;
import com.demo.chatbot.model.OutputContexts;
import com.demo.chatbot.model.Text;

public class WebhookResponse {
    private String fulfillmentText= " Default fulfillment text";
    //private List<TextResponse> fulfillmentMessages;

    private String source = "impetus-hook(Default source)";
    private List<OutputContexts> outputContexts;
    private List<FulfillmentMessages> fulfillmentMessages;
    private FollowUpEventInput followUpEventInput;

    public List<FulfillmentMessages> getFulfillmentMessages() {
		return fulfillmentMessages;
	}

	public void setFulfillmentMessages(List<FulfillmentMessages> fulfillmentMessages) {
		this.fulfillmentMessages = fulfillmentMessages;
	}

	public FollowUpEventInput getFollowUpEventInput() {
		return followUpEventInput;
	}

	public void setFollowUpEventInput(FollowUpEventInput followUpEventInput) {
		this.followUpEventInput = followUpEventInput;
	}

	public final String getFulfillmentText() {
        return fulfillmentText;
    }

    public final void setFulfillmentText(String fulfillmentText) {
        this.fulfillmentText = fulfillmentText;
    }

 /*   public final List<TextResponse> getFulfillmentMessages() {

    public final void setFulfillmentMessages(List<Text> fulfillmentMessages) {
        this.fulfillmentMessages = fulfillmentMessages;
    }*/

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
//        this.fulfillmentMessages = displayText;
    }

    public WebhookResponse(String fulfillmentText) {
        this.fulfillmentText = fulfillmentText;
    }
    public WebhookResponse() {
        this.fulfillmentText = " I didnt get it, could you repeat(Default Text)";
    }

}