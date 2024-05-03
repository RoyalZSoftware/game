package com.royalzsoftware.gameserver.rpcendpoints;

import com.royalzsoftware.identification.AuthenticationRequest;
import com.royalzsoftware.identification.UsernameAlreadyInUseException;
import com.royalzsoftware.rpc.Command;
import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;
import com.royalzsoftware.uno.UnoPlayer;

public class LoginCommand implements Command {

    public LoginCommand() { }

    @Override
    public Response handle(Request request) throws InvalidRequestException {
        var username = request.args.get("username");
        if (username == null) {
            throw new InvalidRequestException();
        }

        String password = Double.toString(Math.random() * 100);
        UnoPlayer player = new UnoPlayer(username);

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
