package com.royalzsoftware.uno;

import java.util.ArrayList;
import java.util.List;

import com.royalzsoftware.eventstream.Event;
import com.royalzsoftware.eventstream.EventBroker;
import com.royalzsoftware.uno.events.GameStartedEvent;
import com.royalzsoftware.uno.events.PlayerJoinedEvent;
import com.royalzsoftware.uno.events.TurnChangedEvent;

public class Uno {

    private EventBroker broker;

    private int round = 0;
    private int currentTurn = 0;
    private List<UnoPlayer> players = new ArrayList<>();
    private List<Card> cardStack = new ArrayList<>();

    private Card current;

    public Uno(EventBroker broker) {
        this.broker = broker;
    }

    public void addPlayer(UnoPlayer player) {
        this.players.add(player);
        this.publishForAllParticipants(new PlayerJoinedEvent(player));
    }

    public void start() {
        this.round = 0;

        this.players.stream().forEach(p -> {
            p.addCard(new ValueCard(3, CardColor.BLUE));
            p.addCard(new ValueCard(3, CardColor.BLUE));
            p.addCard(new ValueCard(3, CardColor.BLUE));
            p.addCard(new ValueCard(3, CardColor.BLUE));
            p.addCard(new ValueCard(3, CardColor.BLUE));
            p.addCard(new ValueCard(3, CardColor.BLUE));
            p.addCard(new ValueCard(3, CardColor.BLUE));
        });

        this.publishForAllParticipants(new GameStartedEvent());
    }

    public void drawCard(UnoPlayer player) {
        player.addCard(new ValueCard(3, CardColor.BLUE));
    }

    public boolean playCard(UnoPlayer player, Card card) {
        if (!isPlayersTurn(player))
            return false;
        if (!card.canBePlayed(this.current))
            return false;

        if (card instanceof ActionCard) {
            ((ActionCard) card).applyEffect(this);
        }

        player.removeCard(card);

        this.current = card;

        nextTurn();
        return true;
    }

    public boolean hasPossibleMove(UnoPlayer player) {
        return player.getCards().stream().filter(t -> t.canBePlayed(this.current)).toList().size() > 0;
    }

    public void changeDirection() {
    }

    private void nextTurn() {
        this.currentTurn++;

        if (this.currentTurn == this.players.size()) {
            this.round++;
            this.currentTurn = 0;
        }

        // this.current.canBePlayed(current)(this);

        this.publishForAllParticipants(new TurnChangedEvent());
    }

    private boolean isPlayersTurn(UnoPlayer player) {
        return true; // TODO
    }

    private void publishForAllParticipants(Event event) {
        this.broker.publish(event, this.players.stream().map(t -> t.getSubscriber()).toList());
    }
}
