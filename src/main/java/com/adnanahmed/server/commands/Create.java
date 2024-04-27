package com.adnanahmed.server.commands;

import com.adnanahmed.rpc.Command;
import com.adnanahmed.rpc.InvalidRequestException;
import com.adnanahmed.rpc.Request;
import com.adnanahmed.rpc.Response;

public class Create implements Command {

    public Create() {

    }

    @Override
    public Response handle(Request request) throws InvalidRequestException {
        return new Response(0, "OK");
    }

    @Override
    public String getIdentifier() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIdentifier'");
    }
}
