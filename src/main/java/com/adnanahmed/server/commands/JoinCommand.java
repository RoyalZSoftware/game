package com.adnanahmed.server.commands;

import com.adnanahmed.eventstream.EventBroker;
import com.adnanahmed.rpc.Command;
import com.adnanahmed.rpc.InvalidRequestException;
import com.adnanahmed.rpc.Request;
import com.adnanahmed.rpc.Response;

public class JoinCommand implements Command {
    private final EventBroker broker;

    public JoinCommand(EventBroker eventBroker) {
        this.broker = eventBroker;
    }

    @Override
    public Response handle(Request request) throws InvalidRequestException{
        if (request.args.length != 1) {
            throw new InvalidRequestException();
        }
        this.broker.publish(null);
        return new Response(0, "OK");
    }

    @Override
    public String getIdentifier() {
        return "join";
    }
    
}
