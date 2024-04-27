package com.adnanahmed.eventstream;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class AcceptSubscribers implements Runnable, Subscriber {
    private ServerSocket serverSocket;

    private ArrayList<PrintWriter> outputChannels = new ArrayList<>();

    public AcceptSubscribers(ServerSocket serverSocket, EventBroker broker) {
        this.serverSocket = serverSocket;
        broker.subscribe(this);
    }

    @Override
    public void run() {
        while(true) {
            try {
                Socket socket = this.serverSocket.accept();
                this.outputChannels.add(new PrintWriter(socket.getOutputStream()));
            } catch (IOException exception) {
            }
        }
    }

    @Override
    public void eventReceived(Event event) {
        for (PrintWriter writer: this.outputChannels) {
            writer.println(event.getIdentifier() + ";" + event.getIdentifier());
        }
    }
}
