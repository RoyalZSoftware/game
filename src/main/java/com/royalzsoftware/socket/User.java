package com.royalzsoftware.socket;

import java.net.Socket;
import java.util.ArrayList;

import com.royalzsoftware.domain.Player;

public class User {

    private static ArrayList<User> users = new ArrayList<>();

    public static User FindUser(Socket socket) {
        for (User user : users) {
            if (user.socket != null && user.socket == socket) {
                return user;
            }
        }
        return null;
    }

    public static User FindUser(Player player) {
        for (User user : users) {
            if (user.socket != null && user.player == player) {
                return user;
            }
        }
        return null;
    }

    private Player player;
    private Socket socket;

    public User(Player player, Socket socket) {
        this.player = player;
        this.socket = socket;
        users.add(this);
    }
}
