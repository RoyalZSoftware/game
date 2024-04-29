package com.royalzsoftware.terminal;

import java.io.IOException;
import java.util.HashMap;

import com.royalzsoftware.authentication.InvalidCredentialsException;
import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;
import com.royalzsoftware.socket.Client;
import com.royalzsoftware.socket.EventStreamClient;

public class TerminalClient {
    public TerminalClient() {
        Client client = new Client("localhost", 8000);

        String username = "Alex";
        String[] params = {username};

        Request request = new Request("login", params, new HashMap<>());

        try {
            Response response = client.Send(request);
            if (response.status == 102) {
                String password = response.payload;
                EventStreamClient eventStreamClient = new EventStreamClient("localhost", 8001);
                try {
                    eventStreamClient.connect(username, password);
                } catch (InvalidRequestException | InvalidCredentialsException e) {
                    e.printStackTrace();
                }
            } else {
                throw new IOException("Server did not return 102");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TerminalClient();
    }
}
