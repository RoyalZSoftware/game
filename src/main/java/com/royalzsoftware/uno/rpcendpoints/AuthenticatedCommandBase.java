package com.royalzsoftware.uno.rpcendpoints;

import java.util.Optional;

import com.royalzsoftware.rpc.Command;
import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;
import com.royalzsoftware.uno.domain.UnoPlayer;

public abstract class AuthenticatedCommandBase implements Command {
    protected UserRepository userRepository;

    public AuthenticatedCommandBase(UserRepository repository) {
        this.userRepository = repository;
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
        var headers = request.args;
        var token = headers.get("token");
        System.out.println(token);

        if (token == null) {
            throw new UnauthenticatedException();
        }
        Optional<UnoPlayer> player = this.userRepository.getPlayer(token);

        if (!player.isPresent()) {
            throw new UnauthenticatedException();
        }

        return player.get();
    }
}
