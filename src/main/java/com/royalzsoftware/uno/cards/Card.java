package com.royalzsoftware.uno.cards;

public interface Card {
    Card clone();
    boolean canBePlayed(Card currentCard);
}