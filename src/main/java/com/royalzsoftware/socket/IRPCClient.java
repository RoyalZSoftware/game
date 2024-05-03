package com.royalzsoftware.socket;

import java.io.IOException;

import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;

public interface IRPCClient {
    Response Send(Request request) throws IOException;
}
