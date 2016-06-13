package com.mitrais.scrummit.model;

/**
 * Created by Andreanus_P on 6/10/2016.
 */
public class ErrorResponse {
    private int errorCode;
    private String message;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
