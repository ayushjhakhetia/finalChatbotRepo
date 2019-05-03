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
@JsonPropertyOrder({ "languageCode", "queryText", "fulfillmentText", "action", "allRequiredParamsPresent", "parameters",
		"outputContexts", "intent", "fulfillmentMessages", "intentDetectionConfidence", "diagnosticInfo", })
//ec2-3-95-133-255.compute-1.amazonaws.com

public class QueryResult {
	@JsonProperty("languageCode")
	private String languageCode;
	@JsonProperty("queryText")
	private String queryText;
	@JsonProperty("fulfillmentText")
	private String fulfillmentText = "Default fulfillment Text";
	@JsonProperty("action")
	private String action;
	@JsonProperty("allRequiredParamsPresent")
	private Boolean allRequiredParamsPresent;
	@JsonProperty("parameters")
	private Parameters parameters;
	@JsonProperty("outputContexts")
	private List<OutputContexts> outputContexts;
	@JsonProperty("intent")
	private Intent intent;
	@JsonProperty("fulfillmentMessages")
	private List<FulfillmentMessages> fulfillmentMessages;
	@JsonProperty("intentDetectionConfidence")
	private Integer intentDetectionConfidence;
	@JsonProperty("diagnosticInfo")
	private DiagnosticInfo diagnosticInfo;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("languageCode")
	public String getLanguageCode() {
		return languageCode;
	}

	@JsonProperty("languageCode")
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	@JsonProperty("queryText")
	public String getQueryText() {
		return queryText;
	}

	@JsonProperty("queryText")
	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}

	@JsonProperty("fulfillmentText")
	public String getFulfillmentText() {
		return fulfillmentText;
	}

	@JsonProperty("fulfillmentText")
	public void setFulfillmentText(String fulfillmentText) {
		this.fulfillmentText = fulfillmentText;
	}

	@JsonProperty("action")
	public String getAction() {
		return action;
	}

	@JsonProperty("action")
	public void setAction(String action) {
		this.action = action;
	}

	@JsonProperty("allRequiredParamsPresent")
	public Boolean getAllRequiredParamsPresent() {
		return allRequiredParamsPresent;
	}

	@JsonProperty("allRequiredParamsPresent")
	public void setAllRequiredParamsPresent(Boolean allRequiredParamsPresent) {
		this.allRequiredParamsPresent = allRequiredParamsPresent;
	}

	@JsonProperty("parameters")
	public Parameters getParameters() {
		return parameters;
	}

	@JsonProperty("parameters")
	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}

	@JsonProperty("outputContexts")
	public List<OutputContexts> getOutputContexts() {
		return outputContexts;
	}

	@JsonProperty("outputContexts")
	public void setOutputContexts(List<OutputContexts> outputContexts) {
		this.outputContexts = outputContexts;
	}

	@JsonProperty("intent")
	public Intent getIntent() {
		return intent;
	}

	@JsonProperty("intent")
	public void setIntent(Intent intent) {
		this.intent = intent;
	}

	@JsonProperty("fulfillmentMessages")
	public List<FulfillmentMessages> getFulfillmentMessages() {
		return fulfillmentMessages;
	}

	@JsonProperty("fulfillmentMessages")
	public void setFulfillmentMessages(List<FulfillmentMessages> fulfillmentMessages) {
		this.fulfillmentMessages = fulfillmentMessages;
	}

	@JsonProperty("intentDetectionConfidence")
	public Integer getIntentDetectionConfidence() {
		return intentDetectionConfidence;
	}

	@JsonProperty("intentDetectionConfidence")
	public void setIntentDetectionConfidence(Integer intentDetectionConfidence) {
		this.intentDetectionConfidence = intentDetectionConfidence;
	}

	@JsonProperty("diagnosticInfo")
	public DiagnosticInfo getDiagnosticInfo() {
		return diagnosticInfo;
	}

	@JsonProperty("diagnosticInfo")
	public void setDiagnosticInfo(DiagnosticInfo diagnosticInfo) {
		this.diagnosticInfo = diagnosticInfo;
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
