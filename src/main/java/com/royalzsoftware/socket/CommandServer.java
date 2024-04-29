package com.royalzsoftware.socket;

import java.io.IOException;
import java.net.ServerSocket;

import com.royalzsoftware.rpc.CommandRouter;

public class CommandServer {

    private ServerSocket serverSocket;
    private CommandRouter router;

    public CommandServer(int port, CommandRouter router) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.router = router;
    }
    
    public void listenInThread() {
        Thread acceptThread = new Thread(new AcceptRPCRequest(serverSocket, this.router));

        acceptThread.start();
    }
}
