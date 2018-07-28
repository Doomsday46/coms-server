package com.saviorru.comsserver.exceptions;

public class EmptyParameter extends IllegalArgumentException {

    public EmptyParameter(String message){
        super(message);
    }

    public EmptyParameter() {
    }
}
