package com.royalzsoftware.gameserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.royalzsoftware.domain.Player;
import com.royalzsoftware.eventstream.Event;
import com.royalzsoftware.eventstream.EventBroker;
import com.royalzsoftware.gameserver.rpcendpoints.CreateGameCommand;
import com.royalzsoftware.gameserver.rpcendpoints.JoinGameCommand;
import com.royalzsoftware.gameserver.rpcendpoints.ListGamesCommand;
import com.royalzsoftware.gameserver.rpcendpoints.LoginCommand;
import com.royalzsoftware.rpc.Command;
import com.royalzsoftware.rpc.CommandRouter;
import com.royalzsoftware.socket.CommandServer;
import com.royalzsoftware.socket.EventStreamServer;

public class GameServer {

    class TestEvent implements Event {

        @Override
        public String getIdentifier() {
            return "test";
        }

    }

    public GameServer() {
        GameRepository gameRepository = new GameRepository();
        EventBroker eventBroker = new EventBroker();

        List<Command> commands = new ArrayList<Command>();
        commands.add(new JoinGameCommand(gameRepository));
        commands.add(new CreateGameCommand(eventBroker, gameRepository));
        commands.add(new ListGamesCommand(gameRepository));
        commands.add(new LoginCommand());

        Thread spam = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("Sending");
                    Optional<Player> player = Player.players.stream().findFirst();
                    if (player.isPresent()) {
                        eventBroker.publish(new TestEvent(), player.get().subscriber);
                    } else {

                    }
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        spam.start();

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