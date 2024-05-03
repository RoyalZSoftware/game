package com.royalzsoftware.gameserver.rpcendpoints;

import com.royalzsoftware.gameserver.GameRepository;
import com.royalzsoftware.identification.Identifiable;
import com.royalzsoftware.rpc.Command;
import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;
import com.royalzsoftware.uno.UnoPlayer;

public abstract class AuthenticatedCommandBase implements Command {
    protected GameRepository gameRepository;

    public AuthenticatedCommandBase(GameRepository repository) {
        this.gameRepository = repository;
    }

    public Response handle(Request request) throws InvalidRequestException {
        UnoPlayer player;
        try {
            player = this.getPlayer(request);
        } catch(UnauthenticatedException ex) {
            return new Response(69, "Unauthenticated");
        }

        return this.handleAuthenticated(request, player);
    }

    protected abstract Response handleAuthenticated(Request request, UnoPlayer player) throws InvalidRequestException ;

    @Override
    public abstract String getIdentifier();

    private UnoPlayer getPlayer(Request request) throws UnauthenticatedException {
        var headers = request.headers;
        var token = headers.get("token");

        if (token == null) {
            throw new UnauthenticatedException();
        }

        Identifiable player = Identifiable.Find(token);

        return (UnoPlayer) player;
    }
}
