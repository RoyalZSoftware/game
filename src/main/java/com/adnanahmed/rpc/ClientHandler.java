package com.adnanahmed.rpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final ServerSocket serverSocket;
    private final CommandRouter router;

    public ClientHandler(ServerSocket serverSocket, CommandRouter router) {
        this.serverSocket = serverSocket;
        this.router = router;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket client = this.serverSocket.accept();
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

                try {

                    String stringifiedRequest = in.readLine();

                    Request request = Request.deserialize(stringifiedRequest);

                    Response response = this.router.handle(request);
                    
                    out.println(response.status);
                } catch (InvalidRequestException e) {
                    out.println("Invalid request");
                } catch (CommandNotFoundException e) {
                    out.println("Invalid command");
                } finally {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
