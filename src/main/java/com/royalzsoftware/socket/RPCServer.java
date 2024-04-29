package com.royalzsoftware.socket;

import java.io.IOException;
import java.net.ServerSocket;

import com.royalzsoftware.rpc.CommandRouter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.royalzsoftware.rpc.CommandNotFoundException;
import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;

public class RPCServer implements Runnable {

    private ServerSocket serverSocket;
    private CommandRouter router;

    public RPCServer(int port, CommandRouter router) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.router = router;
    }

    public void listenInThread() {
        Thread acceptThread = new Thread(this);

        acceptThread.start();
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

                    out.println(response.serialize());
                } catch (InvalidRequestException e) {
                    out.println(new Response(101, "Invalid command usage.").serialize());
                    e.printStackTrace();
                } catch (CommandNotFoundException e) {
                    out.println(new Response(102, "Command not found").serialize());
                    e.printStackTrace();
                } finally {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
