package com.royalzsoftware.gameserver.rpcendpoints;

import com.royalzsoftware.gameserver.GameRepository;
import com.royalzsoftware.rpc.Command;
import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;
import com.royalzsoftware.uno.Uno;

public class JoinGameCommand implements Command {
    private final GameRepository gameRepository;

    public JoinGameCommand(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Response handle(Request request) throws InvalidRequestException{
        var gameId = request.args.get("gameId");
        if (gameId == null) {
            throw new InvalidRequestException();
        }

        Uno game = this.gameRepository.findGame(gameId);

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
