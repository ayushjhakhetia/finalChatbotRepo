package com.impetus.rpacoordinator.model;

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
    "capacity",
    "date",
    "duration",
    "numberInteger",
    "time-period",
    //
    "credential",
    "Username",
    "Password",
    "uid",
    "pswd"
})
public class Parameters {

    @JsonProperty("capacity")
    private Integer capacity;
    @JsonProperty("date")
    private String date;
    @JsonProperty("duration")
    private String duration;
    @JsonProperty("numberInteger")
    private String numberInteger;
    @JsonProperty("timePeriod")
    private String timePeriod;
    
    @JsonProperty("credential")
    private String credential;
    @JsonProperty("Username")
    private String username;
    @JsonProperty("Password")
    private String password;
    @JsonProperty("uid")
    private String uid;
    @JsonProperty("pswd")
    private String pswd;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    
    @JsonProperty("credential")
    public String getCredential() {
        return credential;
    }

    @JsonProperty("credential")
    public void setCredential(String credential) {
        this.credential = credential;
    }

    @JsonProperty("Username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("Username")
    public void setUsername(String time) {
        this.username = time;
    }

    @JsonProperty("uid")
    public String getUid() {
        return uid;
    }

    @JsonProperty("uid")
    public void setUid(String date) {
        this.uid = date;
    }
    

    @JsonProperty("pswd")
    public String getPswd() {
        return pswd;
    }

    @JsonProperty("pswd")
    public void setPswd(String duration) {
        this.pswd = duration;
    }
    
    @JsonProperty("capacity")
    public Integer getCapacity() {
        return capacity;
    }

    @JsonProperty("capacity")
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @JsonProperty("timePeriod")
    public String getTimePeriod() {
        return timePeriod;
    }

    @JsonProperty("timePeriod")
    public void setTimePeriod(String time) {
        this.timePeriod = time;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("duration")
    public String getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    @JsonProperty("numberInteger")
    public String getNumberInteger() {
        return numberInteger;
    }

    @JsonProperty("numberInteger")
    public void setNumberInteger(String param) {
        this.numberInteger = param;
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
