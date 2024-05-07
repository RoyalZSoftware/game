package com.royalzsoftware.uno.domain.cards;

public interface Card {
    Card clone();
    boolean canBePlayed(Card currentCard);
}