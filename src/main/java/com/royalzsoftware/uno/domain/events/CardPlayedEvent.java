package com.royalzsoftware.uno.domain.events;

import com.royalzsoftware.eventstream.Event;
import com.royalzsoftware.uno.domain.cards.Card;

public class CardPlayedEvent implements Event {
    private Card card;

    public CardPlayedEvent(Card card) {
        this.card = card;
    }

    @Override
    public String getIdentifier() {
        return "cardplayed";
    }
}
