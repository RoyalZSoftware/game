package com.royalzsoftware.uno;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.royalzsoftware.uno.domain.cards.Card;
import com.royalzsoftware.uno.domain.cards.CardColor;
import com.royalzsoftware.uno.domain.cards.ValueCard;

public class ValueCardTest {
    @Test
    public void CanBePlayedTest() {
        Card current = new ValueCard(3, CardColor.RED);
        Card toLay = new ValueCard(3, CardColor.GREEN);

        assertTrue("Should have been possible.", toLay.canBePlayed(current));
    }

    @Test
    public void CantBePlayedTest() {
        Card current = new ValueCard(3, CardColor.RED);
        Card toLay = new ValueCard(4, CardColor.GREEN);

        assertFalse("Should not have been possible.", toLay.canBePlayed(current));
    }
}
