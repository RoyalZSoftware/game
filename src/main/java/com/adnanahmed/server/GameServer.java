package com.adnanahmed.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.adnanahmed.eventstream.EventBroker;
import com.adnanahmed.eventstream.EventStreamServer;
import com.adnanahmed.rpc.Command;
import com.adnanahmed.rpc.CommandRouter;
import com.adnanahmed.rpc.RPCServer;
import com.adnanahmed.server.commands.JoinCommand;

public class GameServer {

    public GameServer() {
        EventBroker eventBroker = new EventBroker();

        List<Command> commands = new ArrayList<Command>();
        commands.add(new JoinCommand(eventBroker));

        CommandRouter router = new CommandRouter(commands);

        try {
            RPCServer rpcServer = new RPCServer(8000, router);
            rpcServer.listenInThread();
        } catch (IOException ex) {

        }

        try {
            EventStreamServer streamServer = new EventStreamServer(8001, eventBroker);
            streamServer.listenInThread();
        } catch (IOException ex) {
        }
    }
}