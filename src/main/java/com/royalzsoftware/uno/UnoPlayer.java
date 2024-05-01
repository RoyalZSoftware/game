package com.royalzsoftware.uno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.royalzsoftware.eventstream.Subscriber;
import com.royalzsoftware.identification.Identifiable;
import com.royalzsoftware.serialization.ArraySerializable;
import com.royalzsoftware.serialization.ObjectSerializer;
import com.royalzsoftware.serialization.Serializable;
import com.royalzsoftware.serialization.Serializer;
import com.royalzsoftware.serialization.StringSerializable;
import com.royalzsoftware.uno.cards.Card;

public class UnoPlayer implements Identifiable, Serializable {

    public final String username;

    private Subscriber subscriber;

    private List<Card> cards = new ArrayList<>();

    public UnoPlayer(String username) {
        this.username = username;
        Identifiable.Register(this);
    }
    @Override
    public String getIdentifier() {
        return this.username;
    }

    @Override
    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public Subscriber getSubscriber() {
        return this.subscriber;
    }

    public void addCard(Card card) {
        this.cards.add(card.clone());
    }

    public void removeCard(Card card) {
        this.cards.remove(card);
    }

    public List<Card> getCards() {
        return this.cards;
    }
    @Override
    public Serializer getSerializer() {
        var m = new HashMap<String, Serializable>();
        m.put("cards", new ArraySerializable(this.getCards().stream().map(t -> (Serializable) t).toList()));
        m.put("username", new StringSerializable(this.username));
        return new ObjectSerializer(m);
    }
}
