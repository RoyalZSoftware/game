package com.royalzsoftware.uno.domain;

import java.util.List;
import java.util.stream.IntStream;

import com.royalzsoftware.uno.domain.cards.Card;
import com.royalzsoftware.uno.domain.cards.CardStack;

public class DrawCardsQueue {
    private int cardAmount = 0;

    public void add(int cardAmount) {
        this.cardAmount += cardAmount;
    }
    
    public int getSize() {
        return this.cardAmount;
    }

    public List<Card> take() {
        var x = IntStream.range(0, cardAmount).mapToObj(t -> CardStack.getInstance().drawCard()).toList();

        this.cardAmount = 0;

        return x;
    }
}
