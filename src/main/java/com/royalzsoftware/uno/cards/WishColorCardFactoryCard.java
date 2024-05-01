package com.royalzsoftware.uno.cards;

import java.util.HashMap;

import com.royalzsoftware.serialization.ObjectSerializer;
import com.royalzsoftware.serialization.Serializable;
import com.royalzsoftware.serialization.Serializer;
import com.royalzsoftware.uno.Uno;

public class WishColorCardFactoryCard implements Card, ActionCard {
    
    private CardColor cardColor;

    public void setColor(CardColor color) {
        this.cardColor = color;
    }

    @Override
    public boolean canBePlayed(Card currentCard) {
        return this.cardColor != null;
    }

    @Override
    public WishColorCardFactoryCard clone() {
        return new WishColorCardFactoryCard();
    }

    @Override
    public void applyEffect(Uno uno) {
        var card = new WishColorCard(this.cardColor);

        card.applyEffect(uno);
    }

    @Override
    public Serializer getSerializer() {
        var m = new HashMap<String, Serializable>();
        return new ObjectSerializer(m);
    }
}
