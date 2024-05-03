package com.royalzsoftware.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;

public class RPCClient implements IRPCClient {

    private String host;
    private int port;

    private ObjectMapper mapper;

    public RPCClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.mapper = new ObjectMapper();
    }

    public Response Send(Request request) throws IOException {
        Socket socket = new Socket(this.host, this.port);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.println(this.mapper.writeValueAsString(request));
        String serverResponse = in.readLine();
        socket.close();
        return this.mapper.readValue(serverResponse, Response.class);
    }
}