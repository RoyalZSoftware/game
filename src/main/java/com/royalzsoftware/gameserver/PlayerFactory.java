package com.royalzsoftware.gameserver;

import com.royalzsoftware.authentication.INotifiablePlayer;
import com.royalzsoftware.authentication.INotifiablePlayerFactory;
import com.royalzsoftware.domain.Player;
import com.royalzsoftware.eventstream.Subscriber;

public class PlayerFactory implements INotifiablePlayerFactory {

    @Override
    public INotifiablePlayer create(String username, Subscriber subscriber) {
        return new Player(null, subscriber);
    }
    
}
