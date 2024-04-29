package com.royalzsoftware.authentication;

import java.util.ArrayList;
import java.util.List;

import com.royalzsoftware.eventstream.Subscriber;

public class AuthenticationRequest {
    
    public static List<AuthenticationRequest> PlayerLogins = new ArrayList<>();

    public static AuthenticationRequest FindLoginAttempt(String username) {
        return PlayerLogins.stream().filter(t -> t.username.equalsIgnoreCase(username)).findFirst().get();
    }

    private final String username;
    private final String password;
    private final INotifiablePlayerFactory factory;

    public AuthenticationRequest(String username, String password, INotifiablePlayerFactory factory) throws UsernameAlreadyInUseException {
        if (PlayerLogins.stream().filter(t -> t.username == username).findFirst().isPresent()) {
            throw new UsernameAlreadyInUseException();
        }

        this.factory = factory;
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

    public INotifiablePlayer buildNotifablePlayer(Subscriber subscriber) {
        return this.factory.create(this.username, subscriber);
    }
}
