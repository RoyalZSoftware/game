import { WSS_URL } from "./config";

export function createConnection(onGameStateChange) {
    const socket = new WebSocket(WSS_URL);

    socket.addEventListener('message', (msg) => {
        console.log(msg);
        onGameStateChange();
    });

    return {
        login: (username) => {
            socket.send(username + ";" + "password")
        }
    }
}