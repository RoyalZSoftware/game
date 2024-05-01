package com.royalzsoftware.uno.cards;

import java.util.HashMap;

import com.royalzsoftware.serialization.ObjectSerializer;
import com.royalzsoftware.serialization.Serializable;
import com.royalzsoftware.serialization.Serializer;

public abstract class ColoredCard implements Card {

    public abstract ColoredCard clone();

    private CardColor cardColor;

    public ColoredCard(CardColor cardColor) {
        this.cardColor = cardColor;
    }

    public CardColor getCardColor() {
        return this.cardColor;
    }

    public boolean canBePlayed(Card currentCard) {
        return currentCard instanceof ColoredCard && ((ColoredCard) currentCard).cardColor.equals(this.cardColor);
    }

    @Override
    public Serializer getSerializer() {
        var m = new HashMap<String, Serializable>();

        m.put("color", this.cardColor);

        return new ObjectSerializer(m);
    }
}
