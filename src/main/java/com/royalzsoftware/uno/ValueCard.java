package com.royalzsoftware.uno;

public class ValueCard extends ColoredCard {
    private int value;

    public ValueCard(int value, CardColor color) {
        super(color);
        if (value < 0 || value > 9) {
            throw new IndexOutOfBoundsException();
        }
        this.value = value;
    }
    @Override
    public boolean canBePlayed(Card currentCard) {
        if (currentCard instanceof ValueCard) {
            return super.canBePlayed(currentCard) || ((ValueCard) currentCard).value == this.value;
        }
        return super.canBePlayed(currentCard);
    }
    @Override
    public ColoredCard clone() {
        return new ValueCard(value, getCardColor());
    }
}
