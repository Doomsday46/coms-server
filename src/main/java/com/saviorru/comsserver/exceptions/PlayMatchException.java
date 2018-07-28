package com.saviorru.comsserver.exceptions;

public class PlayMatchException extends IllegalArgumentException {
    public PlayMatchException(String message){
        super(message);
    }

    public PlayMatchException() {
    }
}
