package com.royalzsoftware.gameserver.rpcendpoints;

import com.royalzsoftware.domain.Game;
import com.royalzsoftware.eventstream.EventBroker;
import com.royalzsoftware.gameserver.GameRepository;
import com.royalzsoftware.rpc.Command;
import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;

public class JoinGameCommand implements Command {
    private final EventBroker broker;
    private final GameRepository gameRepository;

    public JoinGameCommand(EventBroker eventBroker, GameRepository gameRepository) {
        this.broker = eventBroker;
        this.gameRepository = gameRepository;
    }

    @Override
    public Response handle(Request request) throws InvalidRequestException{
        if (request.args.length != 1) {
            throw new InvalidRequestException();
        }

        Game game = this.gameRepository.findGame(request.args[0]);

        if (game == null) {
            return new Response(1, "Game not found.");
        }
        return new Response(0, "OK");
    }

    @Override
    public String getIdentifier() {
        return "join";
    }
    
}
