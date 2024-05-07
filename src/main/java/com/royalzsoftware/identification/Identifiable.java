package com.royalzsoftware.identification;

public class Identifiable<Payload> {
    private Payload payload;

    public Identifiable(Payload payload){
        this.payload = payload;
    }

    public void setPayload() {

    }
    public Payload getPayload() {
        return this.payload;
    }
}
