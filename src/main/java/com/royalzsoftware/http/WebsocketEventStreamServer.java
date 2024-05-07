package com.royalzsoftware.http;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.royalzsoftware.eventstream.Event;
import com.royalzsoftware.eventstream.EventBroker;
import com.royalzsoftware.eventstream.Subscriber;
import com.royalzsoftware.rpc.Response;

public class WebsocketEventStreamServer extends WebSocketServer {

    class PongEvent implements Event {

        @Override
        public String getIdentifier() {
            return "pong";
        }

    }

    private EventBroker broker;

    private List<WebSocket> known = new ArrayList<>();
    private ObjectMapper mapper = new ObjectMapper();

    public WebsocketEventStreamServer(InetSocketAddress addr, EventBroker broker) {
        super(addr);
        this.broker = broker;
    }

    public void listenInThread() {
        Thread t = new Thread(() -> {
            this.run();
        });

        t.start();
        
        Thread keepAliveThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.broker.publish(new PongEvent());
            }
        });
        keepAliveThread.start();
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
    }

    @Override
    public void onMessage(WebSocket conn, String msg) {
        System.out.println(msg);
        if (msg.split(";").length == 2) {
            // is Authentication request
            Subscriber subsc = new Subscriber() {

                @Override
                public void eventReceived(Event event) {
                    try {
                        conn.send(new ObjectMapper().writeValueAsString(event));
                    } catch(JsonProcessingException ex) {
                        ex.printStackTrace();
                        conn.send("ERROR");
                    }
                }
                
            };

            this.broker.subscribe(subsc);

            try {
                conn.send(this.buildResponse(new Response(0, "OK")));
            } catch(JsonProcessingException ex) {
                conn.send("ERROR");
                ex.printStackTrace();
            }
            this.known.add(conn);
        }
            try {
                conn.send(this.buildResponse(new Response(0, "HELLO")));
            } catch(JsonProcessingException ex) {
                conn.send("ERROR");
                ex.printStackTrace();
            }
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        this.known.add(conn);
    }

    @Override
    public void onStart() {
        System.out.println("Websocket server started.");
    }

    private String buildResponse(Response response) throws JsonProcessingException {
        return this.mapper.writeValueAsString(response);
    }
    
}
