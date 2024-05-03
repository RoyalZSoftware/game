package com.royalzsoftware.gameserver;

public class Trackable<T> {

    private T item;
    private String id;

    public Trackable(String id, T item) {
        this.item = item;
        this.id = id;
    }    

    public String getId() {
        return this.id;
    }

    public T getItem() {
        return this.item;
    }
}
