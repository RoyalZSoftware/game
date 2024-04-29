package com.adnanahmed.terminal;

import java.io.IOException;

import com.adnanahmed.rpc.Request;
import com.adnanahmed.rpc.Response;
import com.adnanahmed.socket.Client;

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
