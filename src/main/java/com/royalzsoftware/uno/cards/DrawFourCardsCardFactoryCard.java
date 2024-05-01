package com.royalzsoftware.uno.cards;

import com.royalzsoftware.uno.Uno;

public class DrawFourCardsCardFactoryCard implements Card, ActionCard {
    
    private CardColor cardColor;

    public void setColor(CardColor color) {
        this.cardColor = color;
    }

    @Override
    public boolean canBePlayed(Card currentCard) {
        return this.cardColor != null;
    }

    @Override
    public WishColorCardFactoryCard clone() {
        return new WishColorCardFactoryCard();
    }

    @Override
    public void applyEffect(Uno uno) {
        var card = new DrawFourCardsCard(this.cardColor);

        card.applyEffect(uno);
    }
}
