package com.royalzsoftware.uno;

public class WishColorCardFactoryCard implements Card, ActionCard {
    
    private CardColor cardColor;

    public void setColor(CardColor color) {
        this.cardColor = color;
    }

    @Override
    public boolean canBePlayed(Card currentCard) {
        return true;
    }

    @Override
    public WishColorCardFactoryCard clone() {
        return new WishColorCardFactoryCard();
    }

    @Override
    public void applyEffect(Uno uno) {
        var card = new WishColorCard(this.cardColor);

        card.applyEffect(uno);
    }
}
