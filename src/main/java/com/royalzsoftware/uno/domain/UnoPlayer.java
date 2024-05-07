package com.royalzsoftware.uno.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.royalzsoftware.uno.domain.cards.Card;

public class UnoPlayer {

    public final String username;

    public Optional<Uno> uno;

    private List<Card> cards = new ArrayList<>();

    public UnoPlayer(String username) {
        this.username = username;
    }

    public void addCard(Card card) {
        this.cards.add(card.clone());
    }

    public void removeCard(Card card) {
        this.cards.remove(card);
    }

    public List<Card> getCards() {
        return this.cards;
    }
}
