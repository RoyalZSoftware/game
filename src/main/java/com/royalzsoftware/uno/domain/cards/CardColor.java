package com.royalzsoftware.uno.domain.cards;

import com.royalzsoftware.serialization.Serializable;
import com.royalzsoftware.serialization.Serializer;
import com.royalzsoftware.serialization.StringSerializer;

public enum CardColor implements Serializable {
    GREEN,
    BLUE,
    YELLOW,
    RED;

    @Override
    public Serializer getSerializer() {
        return new StringSerializer(this.name());
    }
}
