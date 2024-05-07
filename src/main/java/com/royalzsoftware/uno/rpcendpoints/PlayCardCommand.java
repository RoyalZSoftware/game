package com.royalzsoftware.uno.rpcendpoints;

import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;
import com.royalzsoftware.uno.domain.UnoPlayer;
import com.royalzsoftware.uno.domain.cards.Card;

public class PlayCardCommand extends AuthenticatedCommandBase {
    private GameRepository gameRepository;

    public PlayCardCommand(UserRepository repository, GameRepository gameRepository) {
        super(repository);
        this.gameRepository = gameRepository;
    }

    @Override
    protected Response handleAuthenticated(Request request, UnoPlayer player) throws InvalidRequestException {
        var game = this.gameRepository.getGameForPlayer(player);
        if (game == null) {
            throw new InvalidRequestException("Not in game.");
        }

        if (game.getItem().getCurrentPlayer() != player) {
            throw new InvalidRequestException("Not your turn.");
        }
        if (request.args.get("cardIndex") == null) throw new InvalidRequestException();

        var cardIndex = Integer.parseInt(request.args.get("cardIndex"));
        Card card = null;
        try {
            card = player.getCards().get(cardIndex);
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidRequestException("Card not found.");
        }

        game.getItem().playCard(player, card);
        return Response.Ok;
    }

    @Override
    public String getIdentifier() {
        return "play";
    }
    
}
