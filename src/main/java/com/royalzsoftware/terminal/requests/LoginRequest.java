package com.royalzsoftware.terminal.requests;

import java.util.HashMap;

import com.royalzsoftware.rpc.Request;

public class LoginRequest extends Request {

    public LoginRequest(String username) {
        super("login");
        this.args = new HashMap<String, String>();
        this.headers = new HashMap<String, String>();

        this.args.put("username", username);
    }
}
