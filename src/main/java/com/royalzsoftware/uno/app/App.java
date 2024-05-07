package com.royalzsoftware.uno.app;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import com.royalzsoftware.eventstream.EventBroker;
import com.royalzsoftware.http.HTTPRPCServer;
import com.royalzsoftware.http.WebsocketEventStreamServer;
import com.royalzsoftware.rpc.Command;
import com.royalzsoftware.rpc.CommandRouter;
import com.royalzsoftware.socket.EventStreamServer;
import com.royalzsoftware.socket.RPCServer;
import com.royalzsoftware.uno.rpcendpoints.CreateGameCommand;
import com.royalzsoftware.uno.rpcendpoints.JoinGameCommand;
import com.royalzsoftware.uno.rpcendpoints.ListGamesCommand;
import com.royalzsoftware.uno.rpcendpoints.LoginCommand;
import com.royalzsoftware.uno.rpcendpoints.ServeCommand;
import com.royalzsoftware.uno.rpcendpoints.ShowGameInfoCommand;

public class App {

    public App() {
        UnoGameRepository gameRepository = new UnoGameRepository();
        InMemoryUserRepository userRepository = new InMemoryUserRepository();
        EventBroker eventBroker = new EventBroker();

        List<Command> commands = new ArrayList<Command>();
        commands.add(new LoginCommand(userRepository));
        commands.add(new JoinGameCommand(userRepository, gameRepository));
        commands.add(new ListGamesCommand(userRepository, gameRepository));
        commands.add(new ShowGameInfoCommand(userRepository, gameRepository));
        commands.add(new CreateGameCommand(eventBroker, gameRepository, userRepository));
        commands.add(new ServeCommand());

        CommandRouter router = new CommandRouter(commands);

        try {
            var rpcServer = new HTTPRPCServer(8000, router);
            rpcServer.listenInThread();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            EventStreamServer streamServer = new EventStreamServer(8001, eventBroker, userRepository);
            streamServer.listenInThread();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        WebsocketEventStreamServer wssstreamServer = new WebsocketEventStreamServer(new InetSocketAddress(8004),
                eventBroker);
        wssstreamServer.listenInThread();

        try {
            var rpcServer = new RPCServer(8002, router);
            rpcServer.listenInThread();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new App();
    }
}