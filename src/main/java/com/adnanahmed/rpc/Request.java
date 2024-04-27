package com.adnanahmed.rpc;

public class Request {
    public final String commandIdentifier;
    public final String[] args;

    public Request(String command, String[] args) {
        this.commandIdentifier = command;
        this.args = args;
    }

    public String serialize() {
        return this.commandIdentifier + "|;|" + String.join(";", this.args);
    }

    public static Request deserialize(String stringifiedRequest) throws InvalidRequestException {
        String[] parts = stringifiedRequest.split("|;|");
        if (parts.length != 2) {
            throw new InvalidRequestException();
        }
        return new Request(parts[0], parts[1].split(";"));
    }
}
