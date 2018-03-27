package com.saviorru.comsserver.cli;

import java.util.List;
import java.util.Map;

public class  CommandRules {

    private Map<String, List<ArgumentType>> parsingRules;

    public void addParsingRule(String commandName, List<ArgumentType> fieldsType) throws Exception {
        if (commandName.isEmpty()) throw new Exception("Empty command name");
        if (fieldsType == null) throw new NullPointerException("Null argument types list");
        if (this.parsingRules.containsKey(commandName))
            throw new Exception("Parsing rule for this command already exists");
        this.parsingRules.put(commandName, fieldsType);
    }

    public boolean containsRule(String commandString){
        return this.parsingRules.containsKey(commandString);
    }

    public  List<ArgumentType> getArgumentType(String commandString){
        return this.parsingRules.get(commandString);
    }

}
