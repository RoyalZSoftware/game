package com.adnanahmed.gameserver.rpcendpoints;

import com.adnanahmed.domain.Game;
import com.adnanahmed.domain.usecases.CreateGame;
import com.adnanahmed.eventstream.EventBroker;
import com.adnanahmed.gameserver.GameRepository;
import com.adnanahmed.rpc.Command;
import com.adnanahmed.rpc.InvalidRequestException;
import com.adnanahmed.rpc.Request;
import com.adnanahmed.rpc.Response;

public class CreateGameCommand implements Command {
    private EventBroker broker;
    private GameRepository gameRepository;

    public CreateGameCommand(EventBroker broker, GameRepository gameRepository) {
        this.broker = broker;
        this.gameRepository = gameRepository;
    }

    @Override
    public Response handle(Request request) throws InvalidRequestException {
        Game game = new CreateGame(this.broker).execute();
        this.gameRepository.registerGame(game);
        return new Response(0, "OK");
    }

    @Override
    public String getIdentifier() {
        return "creategame";
    }
}
