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
    "time",
    "date",
    "duration",
    "param",
    "time-period"
})
public class Parameters {

    @JsonProperty("capacity")
    private Integer capacity;
    @JsonProperty("time")
    private List<String> time = null;
    @JsonProperty("date")
    private String date;
    @JsonProperty("duration")
    private Duration duration;
    @JsonProperty("param")
    private String param;
    @JsonProperty("time-period")
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

    @JsonProperty("time")
    public List<String> getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(List<String> time) {
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
    public Duration getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(Duration duration) {
        this.duration = duration;
    }
    
    @JsonProperty("param")
    public String getParam() {
        return param;
    }

    @JsonProperty("param")
    public void setParam(String param) {
        this.param = param;
    }

    @JsonProperty("time-period")
    public String getTimePeriod() {
        return timePeriod;
    }

    @JsonProperty("time-period")
    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
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
