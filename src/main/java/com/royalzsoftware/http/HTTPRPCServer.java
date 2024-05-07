package com.royalzsoftware.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.royalzsoftware.rpc.CommandNotFoundException;
import com.royalzsoftware.rpc.CommandRouter;
import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HTTPRPCServer {

    private int port;
    private CommandRouter router;

    public HTTPRPCServer(int port, CommandRouter router) {
        this.port = port;
        this.router = router;
    }

    public void listenInThread() throws IOException {
        var server = HttpServer.create(new InetSocketAddress(this.port), 0);
        Thread t = new Thread(() -> {
            server.createContext("/", new RouteHandler(this.router));
            server.setExecutor(null);
            server.start();
        });
        t.start();
    }

    private class RouteHandler implements HttpHandler {
        private CommandRouter router;

        public RouteHandler(CommandRouter router) {
            this.router = router;
        }

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            var path = httpExchange.getRequestURI().getPath();
            var commandString = path.split("/");
            if (commandString.length != 2) {
                sendResponse(httpExchange, new Response(1, "Invalid path"));
                return;
            }

            HashMap<String, String> params = getQueryParamMap(httpExchange.getRequestURI().getQuery());

            Request request = new Request(commandString[1], params, new HashMap<>());

            try {
                Response res = this.router.handle(request);
                sendResponse(httpExchange, res);
            } catch (CommandNotFoundException e) {
                sendResponse(httpExchange, new Response(1, "Command not found."));
            } catch (InvalidRequestException e) {
                sendResponse(httpExchange, new Response(1, e.getReason()));
            }
        }

        private void sendResponse(HttpExchange exchange, Response response) throws IOException {
            var httpStatus = response.status != 0 ? 400 : 200;
            String responseContent = new ObjectMapper().writeValueAsString(response);
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.sendResponseHeaders(httpStatus, responseContent.length());
            OutputStream stream = exchange.getResponseBody();
            stream.write(responseContent.getBytes());
            stream.close();
        }

        private HashMap<String, String> getQueryParamMap(String query) {
            HashMap<String, String> queryParamMap = new HashMap<>();
            if (query == null) {
                return queryParamMap;
            }
            String[] kvPairs = query.split("&");

            for (String kvPair: kvPairs) {
                String[] parts = kvPair.split("=");
                if (parts.length != 2) {
                    continue;
                }
                queryParamMap.put(parts[0], parts[1]);
            }

            return queryParamMap;
        }
    }
}
