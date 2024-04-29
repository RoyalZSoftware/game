package com.adnanahmed.gameserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.adnanahmed.eventstream.EventBroker;
import com.adnanahmed.gameserver.rpcendpoints.CreateGameCommand;
import com.adnanahmed.gameserver.rpcendpoints.JoinGameCommand;
import com.adnanahmed.gameserver.rpcendpoints.ListGamesCommand;
import com.adnanahmed.rpc.Command;
import com.adnanahmed.rpc.CommandRouter;
import com.adnanahmed.socket.CommandServer;
import com.adnanahmed.socket.EventStreamServer;

public class GameServer {

    public GameServer() {
        GameRepository gameRepository = new GameRepository();
        EventBroker eventBroker = new EventBroker();

        List<Command> commands = new ArrayList<Command>();
        commands.add(new JoinGameCommand(eventBroker, gameRepository));
        commands.add(new CreateGameCommand(eventBroker, gameRepository));
        commands.add(new ListGamesCommand(gameRepository));

        CommandRouter router = new CommandRouter(commands);

        try {
            CommandServer rpcServer = new CommandServer(8000, router);
            rpcServer.listenInThread();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            EventStreamServer streamServer = new EventStreamServer(8001, eventBroker);
            streamServer.listenInThread();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}