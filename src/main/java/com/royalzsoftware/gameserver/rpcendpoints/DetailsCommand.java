package com.royalzsoftware.gameserver.rpcendpoints;

import com.royalzsoftware.gameserver.GameRepository;
import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;
import com.royalzsoftware.uno.UnoPlayer;

public class DetailsCommand extends AuthenticatedCommandBase {

    public DetailsCommand(GameRepository repository) {
        super(repository);
    }

    @Override
    protected Response handleAuthenticated(Request request, UnoPlayer player) throws InvalidRequestException {
        return new Response(
            0,
            this.gameRepository.getGameId(this.gameRepository.findGameFor(player))
        );
    }

    @Override
    public String getIdentifier() {
        return "details";
    }
    
}
