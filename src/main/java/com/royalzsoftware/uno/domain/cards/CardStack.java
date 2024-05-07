package com.royalzsoftware.uno.domain.cards;

import java.util.ArrayList;
import java.util.List;

public class CardStack {

    private static CardStack instance;

    public List<Card> possibleCards = new ArrayList<>();

    private CardStack() {

        for (int k = 0; k != 2; k++) {
            possibleCards.add(new DrawFourCardsCardFactoryCard());

            for (CardColor color : CardColor.values()) {
                possibleCards.add(new DrawTwoCardsCard(color));
                possibleCards.add(new ChangeDirectionCard(color));
                possibleCards.add(new SkipNextPlayerCard(color));
                possibleCards.add(new WishColorCardFactoryCard());

                for (int i = 0; i != 9; i++) {
                    possibleCards.add(new ValueCard(i, color));
                }
            }
        }

        instance = this;
    }

    public Card drawCard() {
        int index = ((int) Math.random() * possibleCards.size());
        return possibleCards.get(index).clone();
    }

    public Card drawValueCard() {
        Card c = null;
        while (!(c instanceof ValueCard)) {
            c = drawCard();
        }

        return c;
    }

    public static CardStack getInstance() {
        if (instance != null)
            return instance;
        return new CardStack();
    }

}
