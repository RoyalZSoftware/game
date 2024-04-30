package com.royalzsoftware.uno;

public class WishColorCard extends ColoredCard {
    public WishColorCard(CardColor wishedCardColor) {
        super(wishedCardColor);
    }

    @Override
    public boolean canBePlayed(Card currentCard) {
        return true;
    }
}
