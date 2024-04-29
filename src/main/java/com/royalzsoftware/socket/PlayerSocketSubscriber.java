package com.royalzsoftware.socket;

import java.io.PrintWriter;

import com.royalzsoftware.eventstream.Event;
import com.royalzsoftware.eventstream.Subscriber;

public class PlayerSocketSubscriber implements Subscriber {

    private PrintWriter writer;

    public PlayerSocketSubscriber(PrintWriter writer) {
        this.writer = writer;
    }

    @Override
    public void eventReceived(Event event) {
        this.writer.println(event.serialize());
    }
    
}
