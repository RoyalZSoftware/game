package com.royalzsoftware.terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import com.royalzsoftware.authentication.InvalidCredentialsException;
import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;
import com.royalzsoftware.socket.RPCClient;
import com.royalzsoftware.socket.EventStreamClient;

public class TerminalClient {
    private RPCClient rpcClient;

    public TerminalClient(RPCClient rpcclient) {
        this.rpcClient = rpcclient;
        this.startGame();
    }

    private void startGame() {
        try {
            this.listenForNotifications(getUsernameFromCMD());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getUsernameFromCMD() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String username = "";
        try {
            System.out.println("Your username: ");
            username = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return username;
    }

    private void listenForNotifications(String username) throws IOException {
        Response response = this.rpcClient.Send(new Request("login", new String[]{username}, new HashMap<>()));
        if (response.status != 102) {
            throw new IOException("Server did not return 102");
        }
        String password = response.payload;
        EventStreamClient eventStreamClient = new EventStreamClient("localhost", 8001);

        Thread t = new Thread(() -> {
            try {
                eventStreamClient.connect(username, password);
            } catch (InvalidRequestException | InvalidCredentialsException e) {
                e.printStackTrace();
            }
        });
        t.start();
    }

    public static void main(String[] args) {
        RPCClient client = new RPCClient("localhost", 8000);
        new TerminalClient(client);
    }
}
