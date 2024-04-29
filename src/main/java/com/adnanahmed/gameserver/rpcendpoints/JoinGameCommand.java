package com.adnanahmed.gameserver.rpcendpoints;

import com.adnanahmed.domain.Game;
import com.adnanahmed.eventstream.EventBroker;
import com.adnanahmed.gameserver.GameRepository;
import com.adnanahmed.rpc.Command;
import com.adnanahmed.rpc.InvalidRequestException;
import com.adnanahmed.rpc.Request;
import com.adnanahmed.rpc.Response;

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
