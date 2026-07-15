package com.vika.airesumeanalyzer.model;

public class ApiResponse {

    private String message;
    private String status;
    private String version;

    public ApiResponse(String message, String status, String version) {
        this.message = message;
        this.status = status;
        this.version = version;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public String getVersion() {
        return version;
    }
}