package com.royalzsoftware.uno.rpcendpoints;

import com.royalzsoftware.rpc.Command;
import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;
import com.royalzsoftware.uno.domain.UnoPlayer;

public class LoginCommand implements Command {

    private UserRepository repository;

    public LoginCommand(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Response handle(Request request) throws InvalidRequestException {
        var username = request.args.get("username");
        System.out.println("username " + username);
        this.repository.register(new UnoPlayer(username));
        return new Response(0, "OK");
    }

    @Override
    public String getIdentifier() {
        return "login";
    }

}
