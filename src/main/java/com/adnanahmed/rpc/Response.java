package com.adnanahmed.rpc;

public class Response {
    public final int status;
    public final String payload;

    public Response(int status, String payload) {
        this.status = status;
        this.payload = payload;
    }

    public String serialize() {
        return this.status + ";" + this.payload;
    }
}
