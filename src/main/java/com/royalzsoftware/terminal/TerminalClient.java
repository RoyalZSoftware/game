package com.royalzsoftware.terminal;

import java.io.IOException;

import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;
import com.royalzsoftware.socket.Client;

public class TerminalClient {
    public TerminalClient() {
        Client client = new Client("localhost", 8000);

        Request request = new Request("listgames");
        Request createGame = new Request("creategame");

        try {
            Response createGameResponse = client.Send(createGame);
            System.out.println(createGameResponse.serialize());

            Response response = client.Send(request);
            System.out.println(response.serialize());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TerminalClient();
    }
}
