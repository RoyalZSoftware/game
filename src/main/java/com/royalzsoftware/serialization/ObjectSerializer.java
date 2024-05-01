package com.royalzsoftware.serialization;

import java.util.Map;

public class ObjectSerializer implements Serializer {
    
    private Map<String, Serializable> kvMap;

    public ObjectSerializer(Map<String, Serializable> kvMap) {
        this.kvMap = kvMap;
    }

    @Override
    public Object serialize() {
        return this.kvMap;
    }
}
