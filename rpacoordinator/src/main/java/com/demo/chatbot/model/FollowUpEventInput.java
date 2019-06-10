package com.demo.chatbot.model;
public class FollowUpEventInput {

    // Mandatory
    private String name;
    private Parameters parameters;
    // Mandatory
    private String languageCode;

    public FollowUpEventInput() {
    }

    public FollowUpEventInput(String name, String languageCode) {
        this.name = name;
        this.languageCode = languageCode;
    }

    public FollowUpEventInput(String name, Parameters parameters, String languageCode) {
        this.name = name;
        this.parameters = parameters;
        this.languageCode = languageCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

}
