package com.royalzsoftware.uno.rpcendpoints;

import com.royalzsoftware.rpc.InvalidRequestException;
import com.royalzsoftware.rpc.Request;
import com.royalzsoftware.rpc.Response;
import com.royalzsoftware.uno.domain.Uno;
import com.royalzsoftware.uno.domain.UnoPlayer;

public class JoinGameCommand extends AuthenticatedCommandBase {
    private GameRepository gameRepository;

    public JoinGameCommand(UserRepository repository, GameRepository gameRepository) {
        super(repository);
        this.gameRepository = gameRepository;
    }

    @Override
    protected Response handleAuthenticated(Request request, UnoPlayer player) throws InvalidRequestException {
        String gameId = request.args.get("gameId");

        if (gameId == null) {
            throw new InvalidRequestException();
        }

        System.out.println(gameId);

        Trackable<Uno> game = this.gameRepository.getGameById(gameId);
        System.out.println(game);
        if (game == null) {
            throw new InvalidRequestException();
        }

        game.getItem().addPlayer(player);
        gameRepository.addPlayerToGame(player, game);
        var subscriber = this.userRepository.getEventChannelForPlayer(player);
        if (subscriber != null)
            game.getItem().subscribe(subscriber);
        
        return new Response(0, "Ok");
    }

    @Override
    public String getIdentifier() {
        return "join";
    }
}