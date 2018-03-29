package com.saviorru.comsserver.cli;

import com.saviorru.comsserver.cli.command.CommandInfo;
import javafx.util.Pair;

import java.util.*;

public class Interpreter {
    private CommandParser parser;
    private Map<String, CommandInfo> commandsMap;
    private CommandRules commandRules;


    public Interpreter() throws Exception {
        initCommandsMap();
        commandRules = new CommandRules();
        for (Map.Entry<String, CommandInfo> entry : this.commandsMap.entrySet()) {
            commandRules.addParsingRule(entry.getKey(), entry.getValue().getArgumentsList());
        }
        parser = new CommandParser(commandRules);
    }


    public CommandParameter parse(String rawString){
        return parser.parse(rawString);
    }

    private void initCommandsMap() throws Exception {
        commandsMap = new HashMap<>();
        commandsMap.put("undo", new CommandInfo("undo", new ArrayList<>(), "command"));
        commandsMap.put("start", new CommandInfo("start", new ArrayList<>(), "command"));
        commandsMap.put("finish", new CommandInfo("finish", new ArrayList<>(), "command"));
        commandsMap.put("show grid", new CommandInfo("show grid", new ArrayList<>(), "command"));
        commandsMap.put("show schedule", new CommandInfo("show schedule", new ArrayList<>(), "command"));
        commandsMap.put("show players", new CommandInfo("show players", new ArrayList<>(), "command"));
        commandsMap.put("show locations", new CommandInfo("show locations", new ArrayList<>(), "command"));
        commandsMap.put("set match result", new CommandInfo("set match result", Arrays.asList(ArgumentType.DIGIT, ArgumentType.DIGIT, ArgumentType.DIGIT), "command: matchNumber, number, number"));
        commandsMap.put("set player", new CommandInfo("set player", Arrays.asList(ArgumentType.ALPHA_DIGIT, ArgumentType.ALPHA_DIGIT, ArgumentType.DATE), "command: first name, second name, yyyy-mm-dd"));
        commandsMap.put("set location", new CommandInfo("set location", Arrays.asList(ArgumentType.ALPHA_DIGIT, ArgumentType.ALPHA_DIGIT), "command: name location, description"));
        commandsMap.put("set setting", new CommandInfo("set setting",Arrays.asList(ArgumentType.ALPHA_DIGIT, ArgumentType.SCHEME, ArgumentType.DATE_TIME),"command: tournament name, type scheme (olympic/round ...), date start (yyyy-mm-dd-hh-mm)"));
        commandsMap.put("create tournament", new CommandInfo("create tournament",new ArrayList<>(),"command"));
        commandsMap.put("help", new CommandInfo("help",new ArrayList<>(),"command"));
        commandsMap.put("report", new CommandInfo("report",new ArrayList<>(),"command"));
        commandsMap.put("choose tournament", new CommandInfo("choose tournament",Arrays.asList(ArgumentType.ALPHA_DIGIT),"command"));
    }

}
