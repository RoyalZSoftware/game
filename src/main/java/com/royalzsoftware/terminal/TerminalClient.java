package com.royalzsoftware.terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.royalzsoftware.rpc.AuthenticatedRPCClient;
import com.royalzsoftware.rpc.IRPCClient;
import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;
import com.royalzsoftware.socket.RPCClient;
import com.royalzsoftware.socket.EventStreamClient;

public class TerminalClient {
    private IRPCClient rpcClient;

    public TerminalClient(IRPCClient rpcclient) {
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

    private void listenForNotifications(String password) throws IOException {
        /*
        Response response = this.rpcClient.Send(new LoginRequest(username));
        if (response.status != 102) {
            throw new IOException("Server did not return 102");
        }
        String password = (String) response.payload;
        */
        this.rpcClient = new AuthenticatedRPCClient(this.rpcClient, "Alex");
        EventStreamClient eventStreamClient = new EventStreamClient("localhost", 8001);

        Thread t = new Thread(() -> {
            try {
                eventStreamClient.connect("Alex", password);
            } catch (InvalidRequestException e) {
                e.printStackTrace();
            }
        });
        t.start();
        Response res = this.rpcClient.Send(new Request("creategame"));

        System.out.println(res.payload);
        res = this.rpcClient.Send(new Request("details"));

        System.out.println(res.payload);
    }

    public static void main(String[] args) {
        RPCClient client = new RPCClient("localhost", 8001);
        new TerminalClient(client);
    }
}
