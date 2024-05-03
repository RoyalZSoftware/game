package com.royalzsoftware.gameserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import com.royalzsoftware.eventstream.EventBroker;
import com.royalzsoftware.gameserver.rpcendpoints.CreateGameCommand;
import com.royalzsoftware.gameserver.rpcendpoints.DetailsCommand;
import com.royalzsoftware.gameserver.rpcendpoints.JoinGameCommand;
import com.royalzsoftware.gameserver.rpcendpoints.ListGamesCommand;
import com.royalzsoftware.gameserver.rpcendpoints.LoginCommand;
import com.royalzsoftware.http.HTTPRPCServer;
import com.royalzsoftware.http.WebsocketEventStreamServer;
import com.royalzsoftware.rpc.Command;
import com.royalzsoftware.rpc.CommandRouter;
import com.royalzsoftware.socket.EventStreamServer;
import com.royalzsoftware.socket.RPCServer;

public class GameServer {


    public GameServer() {
        GameRepository gameRepository = new GameRepository();
        EventBroker eventBroker = new EventBroker();

        List<Command> commands = new ArrayList<Command>();
        commands.add(new LoginCommand());
        commands.add(new JoinGameCommand(gameRepository));
        commands.add(new ListGamesCommand(gameRepository));
        commands.add(new DetailsCommand(gameRepository));
        commands.add(new CreateGameCommand(eventBroker, gameRepository));

        CommandRouter router = new CommandRouter(commands);

        try {
            var rpcServer = new HTTPRPCServer(8000, router);
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

        WebsocketEventStreamServer wssstreamServer = new WebsocketEventStreamServer(new InetSocketAddress(8004), eventBroker);
        wssstreamServer.listenInThread();

        try {
            var rpcServer = new RPCServer(8002, router);
            rpcServer.listenInThread();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}