package com.royalzsoftware.rpc;

import java.io.IOException;

public interface IRPCClient {
    Response Send(Request request) throws IOException;
}
