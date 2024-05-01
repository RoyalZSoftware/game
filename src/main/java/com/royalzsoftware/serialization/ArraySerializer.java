package com.royalzsoftware.serialization;

import java.util.List;

public class ArraySerializer implements Serializer {

    private List<Serializable> serializables;

    public ArraySerializer(List<Serializable> serializables) {
        this.serializables = serializables;
    }

    @Override
    public Object serialize() {
        return this.serializables;
    }
}
