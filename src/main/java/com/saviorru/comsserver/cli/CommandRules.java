package com.saviorru.comsserver.cli;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class  CommandRules {

    private Map<String, List<ArgumentType>> parsingRules;

    public CommandRules(){
        this.parsingRules = new HashMap<>();
    }

    public void addParsingRule(String commandName, List<ArgumentType> fieldsType) {
        if (commandName.isEmpty()) throw new IllegalArgumentException("Empty command name");
        if (fieldsType == null) throw new NullPointerException("Null argument types list");
        if (this.parsingRules.containsKey(commandName))
            throw new IllegalArgumentException("Parsing rule for this command already exists");
        this.parsingRules.put(commandName, fieldsType);
    }

    public boolean containsRule(String commandString){
        return this.parsingRules.containsKey(commandString);
    }

    public  List<ArgumentType> getArgumentType(String commandString){
        return this.parsingRules.get(commandString);
    }

}
