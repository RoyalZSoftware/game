import { RPC_BASE_URL } from "../config";
import { newGame } from '../domain';
import { CreateGameRequest, JoinGameCommandRequest, ListGamesRequest, LoginRequest } from "./dtos";

function apiClient(token = undefined) {
    return {
        send: async (request) => {
            let queryString = "";
            if (request.params || token) {
                queryString = "?" + Object.entries({...request.params, token}).map(([k, v]) => (k + "=" + v)).join('&');
            }

            return fetch(`${RPC_BASE_URL}/${request.command}${queryString}`).then(res => res.json()).catch(e => alert(e.message));
        }
    }
}

export function login(username) {
    return apiClient()
        .send(LoginRequest(username))
        .then(() => username);
}

export function createGame(token) {
    return apiClient(token)
        .send(CreateGameRequest)
        .then((response) => newGame(response.payload.id))
}

export function listGames(token) {
    return apiClient(token)
        .send(ListGamesRequest)
        .then((response) => response.payload.map(
            gameDataContract => newGame(gameDataContract.id, gameDataContract.item.players)
        ));
}

export function joinGame(token, gameId) {
    return apiClient(token)
        .send(JoinGameCommandRequest(gameId));
}