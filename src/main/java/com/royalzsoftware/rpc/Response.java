package com.royalzsoftware.rpc;

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

    public static Response deserialize(String serialized) {
        String[] parts = serialized.split(";");
        return new Response(Integer.parseInt(parts[0]), parts[1]);
    }
}
