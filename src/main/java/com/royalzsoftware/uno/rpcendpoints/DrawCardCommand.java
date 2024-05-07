package com.royalzsoftware.uno.rpcendpoints;

import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;
import com.royalzsoftware.uno.domain.cards.CardStack;
import com.royalzsoftware.uno.domain.UnoPlayer;

public class DrawCardCommand extends AuthenticatedCommandBase {

    private UnoPlayer player;

    public DrawCardCommand(UnoPlayer player, UserRepository repository) {
        super(repository);
        this.player = player;
    }

    @Override
    protected Response handleAuthenticated(Request request, UnoPlayer player) throws InvalidRequestException {
        this.player.addCard(CardStack.getInstance().drawCard());
        return Response.Ok;
    }

    @Override
    public String getIdentifier() {
        return "draw";
    }
    
}
