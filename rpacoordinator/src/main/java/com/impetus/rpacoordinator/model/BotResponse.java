package com.impetus.rpacoordinator.model;

import java.util.List;

public class BotResponse {
	String speech = "This is server response";
	List<Text> displayText;

	public String getSpeech() {
		return speech;
	}

	public void setSpeech(String speech) {
		this.speech = speech;
	}

	public List<Text> getDisplayText() {
		return displayText;
	}

	public void setDisplayText(List<Text> displayText) {
		this.displayText = displayText;
	}
}
