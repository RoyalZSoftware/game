package com.royalzsoftware.uno;

import java.util.List;
import java.util.stream.IntStream;

public class DrawCardsQueue {
    private int cardAmount = 0;

    public void add(int cardAmount) {
        this.cardAmount += cardAmount;
    }

    public List<Card> take() {
        return IntStream.range(0, cardAmount).mapToObj(t -> CardStack.getInstance().drawCard()).toList();
    }
}
