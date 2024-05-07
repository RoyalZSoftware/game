package com.royalzsoftware.uno.rpcendpoints;

import com.royalzsoftware.rpc.Command;
import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;

public class ServeCommand implements Command {
    @Override
    public Response handle(Request request) throws InvalidRequestException {
        return new Response(0, "Hello World");
    }

    @Override
    public String getIdentifier() {
        return "app";
    }
    
}
