package com.royalzsoftware.uno;

public class ChangeDirectionCard extends ColoredCard implements ActionCard {
    public ChangeDirectionCard(CardColor cardColor) {
        super(cardColor);
    }

    @Override
    public void applyEffect(Uno uno) {
        uno.changeDirection();
    }
}
