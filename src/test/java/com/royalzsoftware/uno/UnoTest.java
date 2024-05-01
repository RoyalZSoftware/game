package com.royalzsoftware.uno;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.royalzsoftware.eventstream.EventBroker;

public class UnoTest {
    @Test
    public void TestGetNextPlayer() {
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
    @Test
    public void TestHandoutCards() {
        Uno uno = new Uno(new EventBroker());

        UnoPlayer playerOne = new UnoPlayer("Player1");

        uno.addPlayer(playerOne);

        uno.handoutCards();

        assertEquals(7, playerOne.getCards().size());
    }

    @Test
    public void TestDrawFourCards() {
        Uno uno = new Uno(new EventBroker());

        UnoPlayer playerOne = new UnoPlayer("Player1");
        UnoPlayer playerTwo = new UnoPlayer("Player2");

        uno.addPlayer(playerOne);
        uno.addPlayer(playerTwo);
        
        Card greenFour = new ValueCard(4, CardColor.GREEN);
        Card redOne = new ValueCard(1, CardColor.RED);

        Card drawFour = new DrawFourCardsCard(CardColor.GREEN);
        playerOne.addCard(drawFour);
        playerOne.addCard(redOne);

        playerTwo.addCard(greenFour);

        uno.playCard(playerOne, drawFour);

        assertEquals(5, playerTwo.getCards().size());
    }

    @Test(expected = InvalidMoveException.class)
    public void TestInvalidMove() {
        Uno uno = new Uno(new EventBroker());

        UnoPlayer playerOne = new UnoPlayer("Player1");
        UnoPlayer playerTwo = new UnoPlayer("Player2");

        uno.addPlayer(playerOne);
        uno.addPlayer(playerTwo);
        
        Card greenFour = new ValueCard(4, CardColor.GREEN);
        Card redFive = new ValueCard(5, CardColor.RED);

        Card wishGreen = new WishColorCard(CardColor.GREEN);

        playerOne.addCard(wishGreen);
        playerTwo.addCard(redFive);

        uno.playCard(playerOne, wishGreen);

        uno.playCard(playerTwo, redFive);
    }
}
