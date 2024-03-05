package com.bytematrix.rotinadafit.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonErrorStructure {
    @JsonFormat(pattern = "dd/mm/yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private Integer code;
    private String status;
    private String details;
    private Object message;
    private Map<String, String> fieldErrors = new HashMap<>();

    public JsonErrorStructure(LocalDateTime timestamp, Integer code, String status) {
        this.timestamp = timestamp;
        this.code = code;
        this.status = status;
    }

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

    public Map<String, String> getFieldErrors() {
        if (!fieldErrors.isEmpty()) {
            return fieldErrors;
        } else {
            return null;
        }
    }

    public void addFieldError(String field, String message) {
        fieldErrors.put(field, message);
    }
}
