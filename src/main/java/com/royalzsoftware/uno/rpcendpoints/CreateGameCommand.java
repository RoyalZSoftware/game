package com.royalzsoftware.uno.rpcendpoints;

import com.royalzsoftware.eventstream.EventBroker;
import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;
import com.royalzsoftware.uno.domain.Uno;
import com.royalzsoftware.uno.domain.UnoPlayer;

public class CreateGameCommand extends AuthenticatedCommandBase {
    private GameRepository gameRepository;
    private EventBroker broker;

    public CreateGameCommand(EventBroker broker, GameRepository gameRepository, UserRepository playerRepository) {
        super(playerRepository);
        this.broker = broker;
        this.gameRepository = gameRepository;
    }

    @Override
    public String getIdentifier() {
        return "creategame";
    }

    @Override
    protected Response handleAuthenticated(Request request, UnoPlayer player) throws InvalidRequestException {
        this.gameRepository.addGame(new Uno(this.broker));

        return Response.Ok;
    }
}
