package com.royalzsoftware.uno.domain.cards;

import com.royalzsoftware.uno.domain.Uno;

public class ChangeDirectionCard extends ColoredCard implements ActionCard {
    public ChangeDirectionCard(CardColor cardColor) {
        super(cardColor);
    }

    @Override
    public void applyEffect(Uno uno) {
        uno.changeDirection();
    }

    @Override
    public ColoredCard clone() {
        return new ChangeDirectionCard(getCardColor());
    }
}
