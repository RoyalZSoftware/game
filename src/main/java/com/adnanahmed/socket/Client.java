package com.adnanahmed.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.adnanahmed.rpc.Request;
import com.adnanahmed.rpc.Response;

public class Client {

    private String host;
    private int port;

    public Client(String host, int port) {
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


// Client client = new Client(serverURl);
// Response response = client.Send(new JoinRequest('gameId'))

/*
 * Client:
 *   neue Property: sentCommands
 * sendCommand() {
 * }
 * awaitOnResponse(id) {
 * }
 * Client: onMessageReceive(receivedMessageId) {
 *      this.sentCommands.filter(c => c.id === receivedMessageId)
 * }
 */