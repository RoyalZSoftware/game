package com.adnanahmed.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import com.adnanahmed.domain.Player;
import com.adnanahmed.eventstream.Event;
import com.adnanahmed.eventstream.Subscriber;

public class User implements Subscriber {

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
    private PrintWriter writer;

    public User(Socket socket) {
        this.socket = socket;
        try {
            this.writer = new PrintWriter(this.socket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        User.users.add(this);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    @Override
    public void eventReceived(Event event) {
        if (this.writer != null) {
            this.writer.println(event.serialize());
        }
    }
}
