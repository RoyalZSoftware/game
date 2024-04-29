package com.royalzsoftware.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;

public class RPCClient {

    private String host;
    private int port;

    public RPCClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Response Send(Request request) throws IOException {
        Socket socket = new Socket(this.host, this.port);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.println(request.serialize());
        String serverResponse = in.readLine();
        socket.close();
        return Response.deserialize(serverResponse);
    }
}