import { Button } from "@chakra-ui/react";
import { useContext, useState } from "react";
import { login } from "../data-layer";
import { useApiProvider } from "./api-provider";
import { GameList } from "./game-list";

export function Guard() {
    const [authenticated, setAuthenticated] = useState(false);
    const { setToken } = useApiProvider();
    const authenticate = async () => {
        const username = prompt('Username?');
        login(username).then((token) => {
            setToken(token);
            setAuthenticated(true)
        })
    }
    return authenticated ? 
        <GameList></GameList>
        :
        <Button onClick={() => authenticate()}>Authenticate</Button>
            
}