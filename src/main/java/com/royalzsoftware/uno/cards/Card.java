package com.royalzsoftware.uno.cards;

import com.royalzsoftware.serialization.Serializable;

public interface Card extends Serializable {
    Card clone();
    boolean canBePlayed(Card currentCard);
}