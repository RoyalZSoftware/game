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
import com.royalzsoftware.identification.AuthenticationRequest;
import com.royalzsoftware.identification.Identifiable;
import com.royalzsoftware.rpc.Response;

public class WebsocketEventStreamServer extends WebSocketServer {

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
            System.out.println("halsdjkl");
            // is Authentication request
            String[] parts = msg.split(";");
            AuthenticationRequest authRequest = AuthenticationRequest.FindLoginAttempt(parts[0]);
            if (authRequest == null) {
                try {
                    conn.send(this.buildResponse(new Response(404, "Not found.")));
                } catch (JsonProcessingException e) {
                    conn.send("ERROR");
                    e.printStackTrace();
                }
                return;
            }
            boolean isValid = authRequest.checkPassword(parts[1]);
            if (!isValid) {
                try {

                conn.send(this.buildResponse(new Response(1, "Invalid")));
                } catch(JsonProcessingException ex) {
                    ex.printStackTrace();
                    conn.send("ERROR");
                }
                return;
            }
            
            Identifiable identifiable = authRequest.getAuthenticatable();
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

            identifiable.setSubscriber(subsc);
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
