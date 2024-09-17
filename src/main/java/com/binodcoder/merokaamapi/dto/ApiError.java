package com.binodcoder.merokaamapi.dto;
import java.util.Map;

public class ApiError {
    private String status;
    private String message;
    private Map<String, String> errors;

    public ApiError() {
        this.status = "error";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
