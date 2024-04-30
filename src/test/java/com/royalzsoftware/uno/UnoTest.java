package com.royalzsoftware.uno;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.royalzsoftware.eventstream.EventBroker;

public class UnoTest {
    @Test
    public void testGetNextPlayer() {
        Uno uno = new Uno(new EventBroker());

        uno.addPlayer(new UnoPlayer("Player1"));
        uno.addPlayer(new UnoPlayer("Player2"));
        uno.addPlayer(new UnoPlayer("Player3"));

        UnoPlayer retrieved = uno.getNextPlayer();
        assertEquals("Player2", retrieved.username);

        uno.nextTurn();
        retrieved = uno.getNextPlayer();
        assertEquals("Player3", retrieved.username);

        uno.nextTurn();
        retrieved = uno.getNextPlayer();
        assertEquals("Player1", retrieved.username);

        uno.nextTurn();
        uno.changeDirection();
        retrieved = uno.getNextPlayer();

        assertEquals("Player3", retrieved.username);
    }
}
