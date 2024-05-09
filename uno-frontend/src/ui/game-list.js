import { Button, Card, CardBody, CardHeader, Heading, List, ListItem, OrderedList, UnorderedList } from "@chakra-ui/react";
import { useEffect, useState } from "react";
import { createGame, listGames } from "../data-layer";
import { useApiProvider } from "./api-provider";

export function GameList() {
    const [games, setGames] = useState([]);
    const {token} = useApiProvider();
    
    const fetchGames = async () => {
        await listGames(token).then((fetchedGames) => {
            setGames(fetchedGames);
        });
    }

    useEffect(() => {
        fetchGames();
    }, []);

    return <>
        <Card m={3} variant={'elevated'}>
            <CardHeader>
                <Heading size={'md'}>Games</Heading>
                <Button onClick={() => createGame(token).then(() => fetchGames())}>Create</Button>
            </CardHeader>
            <CardBody>
                <UnorderedList>
                    {games.map(c => 
                        <ListItem onClick={() => joinGame(token, c.id)}>
                            {c.id} - {JSON.stringify(c)}
                        </ListItem>
                    )}
                </UnorderedList>
            </CardBody>
        </Card>
    </>
}