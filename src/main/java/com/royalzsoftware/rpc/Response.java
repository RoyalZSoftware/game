package com.royalzsoftware.rpc;

public class Response {

    public static Response Ok = new Response(0, "OK");
    public int status;
    public Object payload;

    public Response() {

    }

    public Response(int status, Object payload) {
        this.status = status;
        this.payload = payload;
    }
}
