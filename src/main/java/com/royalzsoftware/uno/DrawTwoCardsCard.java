package com.royalzsoftware.uno;

public class DrawTwoCardsCard extends ColoredCard implements ActionCard {
    public DrawTwoCardsCard(CardColor color) {
        super(color);
    }

    @Override
    public void applyEffect(Uno uno) {
        uno.drawCardsQueue.add(2);
    }

    @Override
    public ColoredCard clone() {
        return new DrawTwoCardsCard(getCardColor());
    }
}
