package com.mitrais.scrummit.util;

/**
 * Created by Andreanus_P on 6/10/2016.
 */
public class GlobalException extends Exception {
    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }
    public GlobalException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
    public GlobalException() {
        super();
    }
}
