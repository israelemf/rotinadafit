package com.bytematrix.rotinadafit.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class JsonErrorStructure {
    @JsonFormat(pattern = "dd/mm/yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private Integer code;
    private String status;
    private String details;
    private Object message;

    public JsonErrorStructure(LocalDateTime timestamp, Integer code, String status, Object message) {
        this.timestamp = timestamp;
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public JsonErrorStructure(LocalDateTime timestamp, Integer code, String status, String details, Object message) {
        this.timestamp = timestamp;
        this.code = code;
        this.status = status;
        this.details = details;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getDetails() {
        return details;
    }

    public Object getMessage() {
        return message;
    }
}
