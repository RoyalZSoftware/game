package com.royalzsoftware.uno;

public interface Card {
    Card clone();
    boolean canBePlayed(Card currentCard);
}