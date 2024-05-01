package com.royalzsoftware.gameserver.rpcendpoints;

import com.royalzsoftware.eventstream.EventBroker;
import com.royalzsoftware.gameserver.GameRepository;
import com.royalzsoftware.rpc.Command;
import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;
import com.royalzsoftware.uno.Uno;

public class CreateGameCommand implements Command {
    private EventBroker broker;
    private GameRepository gameRepository;

    public CreateGameCommand(EventBroker broker, GameRepository gameRepository) {
        this.broker = broker;
        this.gameRepository = gameRepository;
    }

    @Override
    public Response handle(Request request) throws InvalidRequestException {
        Uno uno = new Uno(this.broker);
        this.gameRepository.registerGame(uno);
        return new Response(0, "OK");
    }

    @Override
    public String getIdentifier() {
        return "creategame";
    }
}
