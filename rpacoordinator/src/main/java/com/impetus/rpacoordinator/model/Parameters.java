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
    "time-period"
})
public class Parameters {

    @JsonProperty("capacity")
    private Integer capacity;
    @JsonProperty("time-period")
    private String time = null;
    @JsonProperty("date")
    private String date;
    //Duration class TODO
    @JsonProperty("duration")
    private String duration;
    @JsonProperty("numberInteger")
    private String numberInteger;
    @JsonProperty("time")
    private String timePeriod;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
        return time;
    }

    @JsonProperty("timePeriod")
    public void setTimePeriod(String time) {
        this.time = time;
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
