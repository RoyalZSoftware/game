package com.adnanahmed.rpc;

import java.io.IOException;
import java.net.ServerSocket;

public class RPCServer {

    private ServerSocket serverSocket;
    private CommandRouter router;

    public RPCServer(int port, CommandRouter router) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.router = router;
    }
    
    public void listenInThread() {
        Thread acceptThread = new Thread(new ClientHandler(serverSocket, this.router));

        acceptThread.start();
    }
}
