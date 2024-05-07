import { RPC_BASE_URL } from "./config";

function authenticated(request, token) {
    const cloned = JSON.parse(JSON.stringify(request));
    cloned.params ??= {};

    cloned.params['token'] = token;
    return cloned;
}

export async function callApi(request, token = undefined) {
    const requestToSend = token ? authenticated(request, token) : request;
    console.log(request, token);

    let queryString = "";
    if (requestToSend.params) {
        queryString = "?" + Object.entries(requestToSend.params).map(([k, v]) => (k + "=" + v)).join('&');
    }

    return await fetch(`${RPC_BASE_URL}/${request.command}${queryString}`).then(res => res.json()).catch(e => alert(e.message));
}

export const LoginRequest = (username) => ({
    command: 'login',
    params: {
        username
    }
});

export const CreateGameRequest = {
    command: 'creategame'
};

export const JoinGameRequest = (gameId) => ({
    command: 'join',
    params: {
        gameId
    }
});

export const ListGamesRequest = {
    command: 'list',
};

export const PlayCardRequest = (cardIndex) => ({
    command: 'play',
    params: {
        cardIndex,
    }
});

export const ShowHandRequest = {
    command: 'showcards'
};

export const DetailsRequest = {
    command: 'details',
};