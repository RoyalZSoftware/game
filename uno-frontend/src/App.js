import React, { useContext, useState, createContext, useEffect } from "react";
import { callApi, CreateGameRequest, JoinGameRequest, ListGamesRequest, LoginRequest } from "./api";
import { createConnection } from "./streaming";

const apiContext = createContext();

function useLogin() {
    const {setToken} = useContext(apiContext);
    return async (username) => {
        await callApi(LoginRequest(username));
        connection.login(username);

        setToken(username);
    }
}

function ApiProvider({children}) {
    const [token, setToken] = useState("");
    return <apiContext.Provider value={{token, setToken}}>{children}</apiContext.Provider>
}

function GameList() {
    const [games, setGames] = useState([]);
    const {token} = useContext(apiContext);
    
    const fetchGames = () => {
        callApi(ListGamesRequest, token).then((fetchedGames) => {
            setGames(fetchedGames.payload);
        });

    }

    useEffect(() => {
        fetchGames();
    }, []);

    const createGame = async () => {
        await callApi(CreateGameRequest, token).then(() => {
            fetchGames();
        });
    }

    const join = async (gameId) => {
        await callApi(JoinGameRequest(gameId), token).then(() => {
            fetchGames();
        })
    }

    return <>
    <button onClick={() => createGame()}>Create</button>
    <ul>
        {games.map(c => <li onClick={() => join(c.id)}>{c.id} - {JSON.stringify(c.item)}</li>)}
    </ul>
    </>
}

function Card() {

}

function Guard() {
    const [authenticated, setAuthenticated] = useState(false);
    const login = useLogin();
    const authenticate = async () => {
        const username = prompt('Username?');
        await login(username).then(() => setAuthenticated(true))
    }
    return authenticated ? 
    <GameList></GameList>
    :
    <button onClick={() => authenticate()}>Authenticate</button>
        
}

const connection = createConnection(() => {

});

export function App() {
    return <ApiProvider>
        <Guard></Guard>
    </ApiProvider>;
}