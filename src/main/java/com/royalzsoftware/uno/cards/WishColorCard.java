package com.royalzsoftware.uno.cards;

import com.royalzsoftware.uno.Uno;

public class WishColorCard extends ColoredCard implements ActionCard {

    public WishColorCard(CardColor cardColor) {
        super(cardColor);
    }

    @Override
    public void applyEffect(Uno uno) {
    }

    @Override
    public boolean canBePlayed(Card currentCard) {
        return true;
    }

    @Override
    public WishColorCard clone() {
        return new WishColorCard(getCardColor());
    }
    
}
