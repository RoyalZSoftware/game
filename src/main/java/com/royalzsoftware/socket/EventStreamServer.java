package com.royalzsoftware.socket;

import java.io.IOException;
import java.net.ServerSocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.royalzsoftware.eventstream.Event;
import com.royalzsoftware.eventstream.EventBroker;
import com.royalzsoftware.eventstream.Subscriber;
import com.royalzsoftware.identification.Identifiable;
import com.royalzsoftware.identification.AuthenticationRequest;
import com.royalzsoftware.rpc.Response;
import com.royalzsoftware.uno.UnoPlayer;
import com.royalzsoftware.uno.events.PlayerJoinedEvent;

public class EventStreamServer implements Runnable {

    private final ServerSocket serverSocket;
    private final EventBroker broker;

    private class PrintWriterSubscriber implements Subscriber {

        private PrintWriter writer;
        private ObjectMapper mapper;

        public PrintWriterSubscriber(PrintWriter writer) {
            this.writer = writer;
            this.mapper = new ObjectMapper();
        }

        @Override
        public void eventReceived(Event event) {
            try {
                this.writer.println(this.mapper.writeValueAsString(new Response(0, event)));
            } catch (JsonProcessingException e) {
                this.writer.println(event.getIdentifier());
                e.printStackTrace();
            }
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

                ObjectMapper mapper = new ObjectMapper();

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
                        writer.println(mapper.writeValueAsString(new Response(1, "Invalid login attempt")));
                        continue;
                    }

                    AuthenticationRequest loginRequest = AuthenticationRequest.FindLoginAttempt(parts[0]);

                    if (loginRequest == null) {
                        writer.println(mapper.writeValueAsString(new Response(106, "Login attempt not found.")));
                        continue;
                    }

                    if (!loginRequest.checkPassword(parts[1])) {
                        writer.println(mapper.writeValueAsString(new Response(107, "Invalid credentials")));
                        continue;
                    }

                    Identifiable authenticatable = loginRequest.getAuthenticatable();
                    Subscriber subscriber = new PrintWriterSubscriber(writer);

                    authenticatable.setSubscriber(subscriber);

                    broker.subscribe(subscriber);

                    broker.publish(new PlayerJoinedEvent((UnoPlayer) authenticatable));
                    
                    writer.println(mapper.writeValueAsString(new Response(0, "OK")));

                    loggedIn = true;
                    continue;
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
