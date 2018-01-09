package com.pavoindus.authentication.response;


import java.io.Serializable;

public abstract class APIResponse implements Serializable {

    private int statusCode;
    private String status;

    public APIResponse(ResponseStatus.Status status) {
        this.status = status.name();
        this.statusCode = status.getStatusCode();

    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
