package com.adnanahmed.rpc;

import java.util.HashMap;

public class Request {
    public final String commandIdentifier;
    public final HashMap<String, String> headers;
    public final String[] args;

    public Request(String command, String[] args, HashMap<String, String> headers) {
        this.commandIdentifier = command;
        this.headers = headers;
        this.args = args;
    }

    public Request(String command) {
        this.commandIdentifier = command;
        this.headers = new HashMap<>();
        this.args = new String[0];
    }

    public String serialize() {
        return this.commandIdentifier + ";" + String.join(",", this.args);
    }

    public static Request deserialize(String stringifiedRequest) throws InvalidRequestException {
        String[] parts = stringifiedRequest.split(";");
        if (parts.length == 1) {
            return new Request(parts[0], new String[0], new HashMap<String, String>());
        }
        return new Request(parts[0], parts[1].split(";"), new HashMap<String, String>());
    }
}
