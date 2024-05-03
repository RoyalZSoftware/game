package com.royalzsoftware.rpc;

import java.util.HashMap;

public class Request {
    public String commandIdentifier;
    public HashMap<String, String> headers;
    public HashMap<String, String> args;

    public Request() {}

    public Request(String command, HashMap<String, String> args, HashMap<String, String> headers) {
        this.commandIdentifier = command;
        this.headers = headers;
        this.args = args;
    }

    public Request(String command) {
        this.commandIdentifier = command;
        this.headers = new HashMap<>();
        this.args = new HashMap<>();
    }
}
