package com.royalzsoftware.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.royalzsoftware.authentication.PlayerLoginAttempt;
import com.royalzsoftware.domain.Player;
import com.royalzsoftware.domain.events.PlayerJoinedEvent;
import com.royalzsoftware.eventstream.EventBroker;
import com.royalzsoftware.rpc.Response;

public class AcceptSubscribers implements Runnable {
    private ServerSocket serverSocket;
    private EventBroker broker;

    public AcceptSubscribers(ServerSocket serverSocket, EventBroker broker) {
        this.serverSocket = serverSocket;
        this.broker = broker;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = this.serverSocket.accept();

                boolean loggedIn = false;
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

                while (!loggedIn) {
                    String line = reader.readLine();
                    if (line == null) continue;
                    System.out.println(line);

                    String[] parts = line.split(";");

                    if (parts.length != 2) {
                        writer.println(new Response(1, "Invalid login attempt").serialize());
                        continue;
                    }

                    PlayerLoginAttempt attempt = PlayerLoginAttempt.FindLoginAttempt(parts[0]);

                    if (attempt == null) {
                        writer.println(new Response(106, "Login attempt not found.").serialize());
                        continue;
                    }

                    if (!attempt.checkPassword(parts[1])) {
                        writer.println(new Response(107, "Invalid credentials").serialize());
                        continue;
                    }

                    Player player = new Player(parts[0], new PrintWriterSubscriber(writer));

                    broker.subscribe(player.subscriber);

                    writer.println(new Response(0, "OK").serialize());
                    broker.publish(new PlayerJoinedEvent(player));
                    loggedIn = true;
                    continue;
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
