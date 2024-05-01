package com.royalzsoftware.serialization;

public class StringSerializer implements Serializer {
    private String value;

    public StringSerializer(String value) {
        this.value = value;
    }

    @Override
    public String serialize() {
        return this.value;
    }
}
