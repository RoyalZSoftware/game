package com.royalzsoftware.gameserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.royalzsoftware.eventstream.EventBroker;
import com.royalzsoftware.gameserver.rpcendpoints.CreateGameCommand;
import com.royalzsoftware.gameserver.rpcendpoints.JoinGameCommand;
import com.royalzsoftware.gameserver.rpcendpoints.ListGamesCommand;
import com.royalzsoftware.gameserver.rpcendpoints.LoginCommand;
import com.royalzsoftware.rpc.Command;
import com.royalzsoftware.rpc.CommandRouter;
import com.royalzsoftware.socket.RPCServer;
import com.royalzsoftware.socket.EventStreamServer;

public class GameServer {


    public GameServer() {
        GameRepository gameRepository = new GameRepository();
        EventBroker eventBroker = new EventBroker();

        List<Command> commands = new ArrayList<Command>();
        commands.add(new JoinGameCommand(gameRepository));
        commands.add(new CreateGameCommand(eventBroker, gameRepository));
        commands.add(new ListGamesCommand(gameRepository));
        commands.add(new LoginCommand());

        CommandRouter router = new CommandRouter(commands);

        try {
            RPCServer rpcServer = new RPCServer(8000, router);
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