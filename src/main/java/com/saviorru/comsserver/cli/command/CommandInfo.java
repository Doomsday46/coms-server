package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.cli.ArgumentType;

import java.util.List;

public class CommandInfo {

    private String command;
    private List<ArgumentType> argumentsList;
    private String help;


    public CommandInfo(String command, List<ArgumentType> argumentsList, String help)
            throws Exception
    {
        if (command.isEmpty()) throw new Exception("Empty command string");
        if (argumentsList == null) throw  new NullPointerException();
        this.command = command;
        this.argumentsList = argumentsList;
        this.help = help;
    }

    public String getCommand() {
        return command;
    }

    public List<ArgumentType> getArgumentsList() {
        return argumentsList;
    }

    public String getHelp() {
        return help;
    }
}
