package com.adnanahmed.rpc;

public interface Command {
    public Response handle(Request request) throws InvalidRequestException;

    public String getIdentifier();
}
