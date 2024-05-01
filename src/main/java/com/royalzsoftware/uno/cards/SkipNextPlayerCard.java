package com.royalzsoftware.uno.cards;

import com.royalzsoftware.uno.Uno;

public class SkipNextPlayerCard extends ColoredCard implements ActionCard {
    public SkipNextPlayerCard(CardColor cardColor) {
        super(cardColor);
    }

    @Override
    public void applyEffect(Uno uno) {
        throw new UnsupportedOperationException("Unimplemented method 'applyEffect'");
    }

    @Override
    public ColoredCard clone() {
        return new SkipNextPlayerCard(getCardColor());
    }
    
}
