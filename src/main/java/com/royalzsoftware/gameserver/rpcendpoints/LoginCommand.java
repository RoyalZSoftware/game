package com.royalzsoftware.gameserver.rpcendpoints;

import com.royalzsoftware.domain.Player;
import com.royalzsoftware.identification.AuthenticationRequest;
import com.royalzsoftware.identification.UsernameAlreadyInUseException;
import com.royalzsoftware.rpc.Command;
import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;

public class LoginCommand implements Command {

    public LoginCommand() { }

    @Override
    public Response handle(Request request) throws InvalidRequestException {
        if (request.args.length != 1) {
            throw new InvalidRequestException();
        }
        String username = request.args[0];

        String password = Double.toString(Math.random() * 100);
        Player player = new Player(username);

        try {
            new AuthenticationRequest(player, password);
        } catch (UsernameAlreadyInUseException ex) {
            return new Response(1, "Username already in use.");
        }

        return new Response(102, password);
    }

    @Override
    public String getIdentifier() {
        return "login";
    }    
}
