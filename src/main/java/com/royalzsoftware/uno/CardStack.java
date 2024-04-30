package com.royalzsoftware.uno;

import java.util.ArrayList;
import java.util.List;

public class CardStack {

    private static CardStack instance;

    public List<Card> possibleCards = new ArrayList<>();

    private CardStack() {

        for (int k = 0; k != 2; k++) {

            for (CardColor color : CardColor.values()) {
                for (int i = 0; i != 9; i++) {
                    possibleCards.add(new ValueCard(i, color));
                }
            }
        }

        instance = this;
    }

    public Card drawCard() {
        int index = ((int) Math.random() * possibleCards.size());
        return possibleCards.get(index);
    }

    public static CardStack getInstance() {
        if (instance != null)
            return instance;
        return new CardStack();
    }

}
