export const LoginRequest = (username) => ({
    command: 'login',
    params: {
        username
    }
});

export const CreateGameRequest = {
    command: 'creategame'
};

export const ListGamesRequest = {
    command: 'list',
};

export const JoinGameCommandRequest = (gameId) => ({
    command: 'join',
    params: {
        gameId,
    }
});
