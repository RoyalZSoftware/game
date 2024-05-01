package com.royalzsoftware.serialization;

import java.util.List;

public class ArraySerializable implements Serializable {

    private List<Serializable> serializables;

    public ArraySerializable(List<Serializable> serializables) {
        this.serializables = serializables;
    }

    @Override
    public Serializer getSerializer() {
        return new ArraySerializer(serializables);
    }
}
