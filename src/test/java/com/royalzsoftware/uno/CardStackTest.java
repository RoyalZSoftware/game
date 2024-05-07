package com.royalzsoftware.uno;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.royalzsoftware.uno.domain.cards.CardStack;
import com.royalzsoftware.uno.domain.DrawCardsQueue;

public class CardStackTest {

    @Test
    public void TestDrawCard() {
        assertNotNull(CardStack.getInstance().drawCard());

        DrawCardsQueue queue = new DrawCardsQueue();
        queue.add(500);
        assertEquals(500, queue.take().size());
    }
    
}
