package com.royalzsoftware.rpc;

public class InvalidRequestException extends Exception {

    private String cause;

    public InvalidRequestException() {}
    public InvalidRequestException(String cause) {
        this.cause = cause;
    }

    public String getReason() {
        return this.cause;
    }
}
