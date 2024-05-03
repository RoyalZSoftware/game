package com.royalzsoftware.rpc;

import java.io.IOException;

public class AuthenticatedRPCClient implements IRPCClient {

    private IRPCClient delegate;
    private String token;

    public AuthenticatedRPCClient(IRPCClient delegate, String token) {
        this.delegate = delegate;
        this.token = token;
    }

    public Response Send(Request request) throws IOException {
        request.headers.put("token", this.token);
        return this.delegate.Send(request);
    }
}
