package com.royalzsoftware.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.royalzsoftware.identification.InvalidCredentialsException;
import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Response;

public class EventStreamClient {

    private final String host;
    private final int port;

    private ObjectMapper mapper;

    public EventStreamClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.mapper = new ObjectMapper();
    }

    public void connect(String username, String password) throws InvalidRequestException, InvalidCredentialsException {
        try {
            Socket socket = new Socket(this.host, this.port);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(username + ";" + password+'\n');

            String res = in.readLine();

            System.out.println(res);

            Response response = this.mapper.readValue(res, Response.class);

            if (response.status == 1) {
                socket.close();
                throw new InvalidRequestException();
            }
            if (response.status == 106) {
                socket.close();
                throw new InvalidCredentialsException();
            }
            if (response.status == 107) {
                socket.close();
                throw new InvalidCredentialsException();
            }

            System.out.println("Login successful!");

            String serverLine;
            while ((serverLine = in.readLine()) != null) {
                System.out.println(serverLine);
            }
            socket.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
