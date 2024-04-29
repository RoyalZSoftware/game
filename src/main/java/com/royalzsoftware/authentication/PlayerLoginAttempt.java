package com.royalzsoftware.authentication;

import java.util.ArrayList;
import java.util.List;

import com.royalzsoftware.domain.Player;
import com.royalzsoftware.eventstream.Subscriber;

public class PlayerLoginAttempt {
    
    public static List<PlayerLoginAttempt> PlayerLogins = new ArrayList<>();

    public static PlayerLoginAttempt FindLoginAttempt(String username) {
        return PlayerLogins.stream().filter(t -> t.username.equalsIgnoreCase(username)).findFirst().get();
    }

    private final String username;
    private final String password;

    public PlayerLoginAttempt(String username, String password) throws UsernameAlreadyInUseException {
        
        if (PlayerLogins.stream().filter(t -> t.username == username).findFirst().isPresent()) {
            throw new UsernameAlreadyInUseException();
        }

        this.username = username;
        this.password = password;

        PlayerLogins.add(this);
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean checkPassword(String password) {
        /*
        if (password != this.password) {
            throw new InvalidCredentialsException();
        }
        */
        return true;
    }
}
