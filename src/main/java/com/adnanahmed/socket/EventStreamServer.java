package com.adnanahmed.socket;

import java.io.IOException;
import java.net.ServerSocket;

import com.adnanahmed.eventstream.EventBroker;

public class EventStreamServer {

    private final ServerSocket serverSocket;
    private final EventBroker broker;

    public EventStreamServer(int port, EventBroker broker) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.broker = broker;
    }

    public void listenInThread() {
        Thread acceptThread = new Thread(new AcceptSubscribers(serverSocket, broker));

        acceptThread.start();
    }
}
