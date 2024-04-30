package com.royalzsoftware.identification;

import java.util.ArrayList;
import java.util.List;

public class AuthenticationRequest {
    
    public static List<AuthenticationRequest> AuthenticationRequests = new ArrayList<>();

    public static AuthenticationRequest FindLoginAttempt(String identifier) {
        return AuthenticationRequests.stream().filter(t -> t.authenticatable.getIdentifier().equalsIgnoreCase(identifier)).findFirst().get();
    }

    private final Identifiable authenticatable;

    public AuthenticationRequest(Identifiable authenticatable, String password) throws UsernameAlreadyInUseException {
        if (AuthenticationRequests.stream().filter(t -> t.authenticatable.getIdentifier() == authenticatable.getIdentifier()).findFirst().isPresent()) {
            throw new UsernameAlreadyInUseException();
        }

        this.authenticatable = authenticatable;

        AuthenticationRequests.add(this);
    }

    public Identifiable getAuthenticatable() {
        return this.authenticatable;
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
