package com.adnanahmed.rpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;

public class Client {
    private URI uri;

    public Client(URI uri) {
        this.uri = uri;
    }

    public String Send(Request request) throws IOException {
        Socket socket = new Socket(this.uri.getHost(), this.uri.getPort());
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.write(request.toString());
        String serverResponse;
        while((serverResponse = in.readLine()) != null) {
            return serverResponse;
        }
        throw new IOException("No result received.");
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