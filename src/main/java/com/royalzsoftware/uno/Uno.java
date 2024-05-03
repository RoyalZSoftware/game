package com.royalzsoftware.uno;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.royalzsoftware.eventstream.Event;
import com.royalzsoftware.eventstream.EventBroker;
import com.royalzsoftware.uno.cards.ActionCard;
import com.royalzsoftware.uno.cards.Card;
import com.royalzsoftware.uno.cards.CardColor;
import com.royalzsoftware.uno.cards.CardStack;
import com.royalzsoftware.uno.cards.ValueCard;
import com.royalzsoftware.uno.events.GameStartedEvent;
import com.royalzsoftware.uno.events.PlayerJoinedEvent;
import com.royalzsoftware.uno.events.TurnChangedEvent;
import com.royalzsoftware.uno.exceptions.InvalidMoveException;
import com.royalzsoftware.uno.exceptions.NotYourTurnException;

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

    @JsonIgnore
    private EventBroker broker;
    private TurnDirection turnDirection = TurnDirection.ASC;

    private int currentTurn = 0;
    public List<UnoPlayer> players = new ArrayList<UnoPlayer>();
    public DrawCardsQueue drawCardsQueue = new DrawCardsQueue();

    private Card current = CardStack.getInstance().drawCard();

    @JsonIgnore
    public Card getCurrentCard() {
        return this.current;
    }

    public Uno() {
    }

    public Uno(EventBroker broker) {
        this.broker = broker;
    }

    public void addPlayer(UnoPlayer player) {
        this.players.add(player);
        this.publishForAllParticipants(new PlayerJoinedEvent(player));
    }

    public void handoutCards() {
        this.players.stream().forEach(p -> {
            IntStream.range(0, 7)
                .mapToObj(t -> CardStack.getInstance().drawCard())
                .forEach(c -> {
                    p.addCard(c);
                });
        });

        this.publishForAllParticipants(new GameStartedEvent());
    }

    public void drawCard(UnoPlayer player) {
        player.addCard(new ValueCard(3, CardColor.BLUE));
    }

    public boolean playCard(UnoPlayer player, Card card) throws NotYourTurnException {
        if (!isPlayersTurn(player))
            throw new NotYourTurnException();
        if (!card.canBePlayed(this.current))
            throw new InvalidMoveException();

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

    @JsonIgnore
    public UnoPlayer getCurrentPlayer() {
        return this.players.get(this.currentTurn);
    }

    private int calculateNextTurn() {
        return Math.floorMod((this.turnDirection.getModifier() + this.currentTurn), this.players.size());
    }

    @JsonIgnore
    public UnoPlayer getNextPlayer() {
        return this.players.get(this.calculateNextTurn());
    }

    @JsonIgnore
    public void nextTurn() {
        this.currentTurn = this.calculateNextTurn();

        List<Card> cards = this.drawCardsQueue.take();

        cards.forEach(c -> {
            this.getCurrentPlayer().addCard(c);
        });

        this.publishForAllParticipants(new TurnChangedEvent());
    }

    private boolean isPlayersTurn(UnoPlayer player) {
        return this.getCurrentPlayer() == player;
    }

    private void publishForAllParticipants(Event event) {
        this.players.stream().forEach(p -> {
            p.notifyAboutEvent(event);
        });
    }

}
