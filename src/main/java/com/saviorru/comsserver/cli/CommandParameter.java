package com.saviorru.comsserver.cli;

import java.util.List;

public class CommandParameter {

    private String nameCommand;
    private List<Object> parameters;

    public CommandParameter(String nameCommand, List<Object> parameters) {
        this.nameCommand = nameCommand;
        this.parameters = parameters;
    }

    public String getNameCommand() {
        return nameCommand;
    }

    public Object getParameter(int index) {
        return parameters.get(index);
    }

}
