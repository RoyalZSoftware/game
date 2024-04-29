package com.royalzsoftware.socket;

import java.io.IOException;
import java.net.ServerSocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.royalzsoftware.authentication.PlayerLoginAttempt;
import com.royalzsoftware.domain.Player;
import com.royalzsoftware.domain.events.PlayerJoinedEvent;
import com.royalzsoftware.eventstream.Event;
import com.royalzsoftware.eventstream.EventBroker;
import com.royalzsoftware.eventstream.Subscriber;
import com.royalzsoftware.rpc.Response;

public class EventStreamServer implements Runnable {

    private final ServerSocket serverSocket;
    private final EventBroker broker;

    private class PrintWriterSubscriber implements Subscriber {

        private PrintWriter writer;

        public PrintWriterSubscriber(PrintWriter writer) {
            this.writer = writer;
        }

        @Override
        public void eventReceived(Event event) {
            this.writer.println(event.getIdentifier());
        }

    }

    public EventStreamServer(int port, EventBroker broker) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.broker = broker;
    }

    public void listenInThread() {
        Thread acceptThread = new Thread(this);

        acceptThread.start();
    }

    public void run() {
        while (true) {
            try {
                Socket socket = this.serverSocket.accept();

                boolean loggedIn = false;
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

                while (!loggedIn) {
                    String line = reader.readLine();
                    if (line == null)
                        continue;
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
