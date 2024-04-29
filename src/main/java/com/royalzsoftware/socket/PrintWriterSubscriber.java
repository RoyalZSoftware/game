package com.royalzsoftware.socket;

import java.io.PrintWriter;

import com.royalzsoftware.eventstream.Event;
import com.royalzsoftware.eventstream.Subscriber;

public class PrintWriterSubscriber implements Subscriber {

    private PrintWriter writer;

    public PrintWriterSubscriber(PrintWriter writer) {
        this.writer = writer;
    }

    @Override
    public void eventReceived(Event event) {
        this.writer.println(event.getIdentifier());
    }
    
}
