package com.tomasfonta.heroes.error;

import java.time.Instant;

public class ExceptionResponse {

    private String description;
    private Instant timeStamp = Instant.now();
    private ValidationType type;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }

    public ValidationType getType() {
        return type;
    }

    public void setType(ValidationType type) {
        this.type = type;
    }

}
