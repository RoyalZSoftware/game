package com.royalzsoftware.serialization;

public class NumberSerializer implements Serializer {
    private int number;

    public NumberSerializer(int number) {
        this.number = number;
    }

    @Override
    public Integer serialize() {
        return number;
    }
    
}
