package com.royalzsoftware.uno;

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
