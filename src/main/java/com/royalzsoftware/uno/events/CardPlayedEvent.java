package com.royalzsoftware.uno.events;

import com.royalzsoftware.eventstream.Event;
import com.royalzsoftware.serialization.Serializable;
import com.royalzsoftware.uno.cards.Card;

public class CardPlayedEvent implements Event {
    private Card card;

    public CardPlayedEvent(Card card) {
        this.card = card;
    }

    @Override
    public String getIdentifier() {
        return "cardplayed";
    }

    public Serializable getPayload() {
        return this.card;
    }
}
