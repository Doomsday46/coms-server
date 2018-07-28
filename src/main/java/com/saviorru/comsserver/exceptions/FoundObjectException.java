package com.saviorru.comsserver.exceptions;

public class FoundObjectException extends NullPointerException {
    public FoundObjectException(String message){
        super(message);
    }

    public FoundObjectException() {
    }
}
