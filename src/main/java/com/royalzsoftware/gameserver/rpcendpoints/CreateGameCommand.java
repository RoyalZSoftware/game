package com.royalzsoftware.gameserver.rpcendpoints;

import com.royalzsoftware.eventstream.EventBroker;
import com.royalzsoftware.gameserver.GameRepository;
import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;
import com.royalzsoftware.uno.Uno;
import com.royalzsoftware.uno.UnoPlayer;

public class CreateGameCommand extends AuthenticatedCommandBase {
    private EventBroker broker;

    public CreateGameCommand(EventBroker broker, GameRepository gameRepository) {
        super(gameRepository);
        this.broker = broker;
    }

    @Override
    public Response handleAuthenticated(Request request, UnoPlayer player) throws InvalidRequestException {
        Uno uno = new Uno(this.broker);
        this.gameRepository.registerGame(uno);
        this.gameRepository.addPlayerToGame(player, uno);
        uno.addPlayer(player);
        return new Response(0, uno);
    }

    @Override
    public String getIdentifier() {
        return "creategame";
    }
}
