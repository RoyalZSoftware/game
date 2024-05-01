package com.royalzsoftware.serialization;

public class StringSerializable implements Serializable {
    
    private String value;

    public StringSerializable(String value) {
        this.value = value;
    }

    @Override
    public Serializer getSerializer() {
        return new StringSerializer(this.value);
    }
}
