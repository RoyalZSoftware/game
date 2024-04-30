package com.royalzsoftware.uno;

public class DrawFourCardsCard extends WishColorCard implements ActionCard {

    public DrawFourCardsCard(CardColor wishedCardColor) {
        super(wishedCardColor);
    }

    @Override
    public void applyEffect(Uno uno) {
    }

    @Override
    public boolean canBePlayed(Card currentCard) {
        return true;
    }
}
