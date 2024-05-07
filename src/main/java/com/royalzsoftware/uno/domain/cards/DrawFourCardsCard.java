package com.royalzsoftware.uno.domain.cards;

import com.royalzsoftware.uno.domain.Uno;

public class DrawFourCardsCard extends WishColorCard {

    public DrawFourCardsCard(CardColor cardColor) {
        super(cardColor);
    }

    @Override
    public void applyEffect(Uno uno) {
        uno.drawCardsQueue.add(4);
        super.applyEffect(uno);
    }

    @Override
    public boolean canBePlayed(Card currentCard) {
        return true;
    }

    @Override
    public DrawFourCardsCard clone() {
        return new DrawFourCardsCard(getCardColor());
    }
}
