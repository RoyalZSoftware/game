package com.royalzsoftware.uno;

public class SkipNextPlayerCard extends ColoredCard implements ActionCard {
    public SkipNextPlayerCard(CardColor cardColor) {
        super(cardColor);
    }

    @Override
    public void applyEffect(Uno uno) {
        throw new UnsupportedOperationException("Unimplemented method 'applyEffect'");
    }
    
}
