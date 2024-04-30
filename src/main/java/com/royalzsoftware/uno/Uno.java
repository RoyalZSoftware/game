package com.royalzsoftware.uno;

import java.util.ArrayList;
import java.util.List;

import com.royalzsoftware.eventstream.Event;
import com.royalzsoftware.eventstream.EventBroker;
import com.royalzsoftware.uno.events.GameStartedEvent;
import com.royalzsoftware.uno.events.PlayerJoinedEvent;
import com.royalzsoftware.uno.events.TurnChangedEvent;

public class Uno {

    private enum TurnDirection {
        ASC(1),
        DESC(-1);

        private int modifier;

        private TurnDirection(int modifier) {
            this.modifier = modifier;
        }

        public int getModifier() {
            return this.modifier;
        }
    }

    private EventBroker broker;
    private TurnDirection turnDirection = TurnDirection.ASC;

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
        this.turnDirection = this.turnDirection == TurnDirection.ASC ? TurnDirection.DESC : TurnDirection.ASC;
    }

    public UnoPlayer getCurrentPlayer() {
        return this.players.get(this.currentTurn);
    }

    private int calculateNextTurn() {
        return Math.floorMod((this.turnDirection.getModifier() + this.currentTurn), this.players.size());
    }

    public UnoPlayer getNextPlayer() {
        return this.players.get(this.calculateNextTurn());
    }

    public void nextTurn() {
        this.currentTurn = this.calculateNextTurn();

        this.publishForAllParticipants(new TurnChangedEvent());
    }

    private boolean isPlayersTurn(UnoPlayer player) {
        return true; // TODO
    }

    private void publishForAllParticipants(Event event) {
        this.broker.publish(event, this.players.stream().map(t -> t.getSubscriber()).filter(f -> f != null).toList());
    }
}
