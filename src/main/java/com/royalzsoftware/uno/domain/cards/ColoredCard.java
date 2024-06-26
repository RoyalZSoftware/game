package com.royalzsoftware.uno.domain.cards;

public abstract class ColoredCard implements Card {

    public abstract ColoredCard clone();

    private CardColor cardColor;

    public ColoredCard() { }

    public ColoredCard(CardColor cardColor) {
        this.cardColor = cardColor;
    }

    public CardColor getCardColor() {
        return this.cardColor;
    }

    public boolean canBePlayed(Card currentCard) {
        return currentCard instanceof ColoredCard && ((ColoredCard) currentCard).cardColor.equals(this.cardColor);
    }
}
