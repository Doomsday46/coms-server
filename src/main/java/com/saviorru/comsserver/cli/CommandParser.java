package com.saviorru.comsserver.cli;

import com.saviorru.comsserver.cli.command.Command;
import com.saviorru.comsserver.cli.command.CommandInfo;
import com.saviorru.comsserver.domain.schematictype.SchemeType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CommandParser {
    private CommandRules commandRules;
    private List<String> rawArguments;
    private String nameCommand;
    private Map<String, CommandInfo> commandMap;


    public CommandParser(){
        initCommandsMap();
        commandRules = new CommandRules();
        for (Map.Entry<String, CommandInfo> entry : this.commandMap.entrySet()) {
            commandRules.addParsingRule(entry.getKey(), entry.getValue().getArgumentsList());
        }
    }

    public Command parse(String commandLine){
        if (commandLine.isEmpty()) return null;
        rawArguments = new ArrayList<>();
        List<ArgumentType> argumentTypes = parseString(commandLine);
        if (rawArguments.size() != argumentTypes.size())
            throw new IllegalArgumentException("Invalid arguments count");
        return new CommandFactory().getCommand(getCommandParameters(argumentTypes,commandLine));
    }




    private CommandParameter getCommandParameters(List<ArgumentType> argumentTypes,String commandLine){
        List<Object> parsedArguments = new ArrayList<>();
        for (int i = 0; i < argumentTypes.size(); i++) {
            switch (argumentTypes.get(i)) {
                case ALPHA:
                    parsedArguments.add(parseAlpha(rawArguments.get(i).trim()));
                    break;
                case DIGIT:
                    parsedArguments.add(parseDigit(rawArguments.get(i).trim()));
                    break;
                case DATE_TIME:
                    parsedArguments.add(parseDateTime(rawArguments.get(i).trim()));
                    break;
                case DATE:
                    parsedArguments.add(parseDate(rawArguments.get(i).trim()));
                    break;
                case ALPHA_DIGIT:
                    parsedArguments.add(parseAlphaDigit(rawArguments.get(i).trim()));
                    break;
                case SCHEME:
                    parsedArguments.add(parseScheme(rawArguments.get(i).trim()));
                    break;
            }
        }
        return new CommandParameter(nameCommand,parsedArguments);
    }

    private List<ArgumentType> parseString(String commandLine){
        List<String> parts = new ArrayList<String>(Arrays.asList(commandLine.split(":",2)));
        nameCommand = parts.get(0);
        nameCommand = nameCommand.toLowerCase();
        nameCommand = nameCommand.trim();
        if (parts.size() > 1)
            rawArguments = new ArrayList<String>(Arrays.asList(parts.get(1).split(",")));
        if (!(commandRules.containsRule(nameCommand)))
            throw new IllegalArgumentException("Invalid command");
        return commandRules.getArgumentType(nameCommand);
    }

    private Integer parseDigit(String rawField){
        if (rawField == null || rawField.isEmpty()) throw new NullPointerException("String cannot be empty or null");
        if (!(rawField.matches("[0-9]+")))
            throw new IllegalArgumentException("Invalid format, expected number");
        return Integer.parseInt(rawField);
    }

    private String parseAlpha(String rawField){
        if (rawField == null || rawField.isEmpty()) throw new NullPointerException("String cannot be empty or null");
        if (!(rawField.matches("[A-Za-zА-Яа-я]+")))
            throw new IllegalArgumentException("Invalid format, expected string");
        return rawField;
    }

    private LocalDateTime parseDateTime(String rawField) throws NullPointerException{
        if(rawField == null) throw new NullPointerException();
        return LocalDateTime.parse(rawField, DateTimeFormatter.ofPattern("dd.LL.yyyy HH:mm"));
    }

    private LocalDate parseDate(String rawField) throws NullPointerException {
        if(rawField == null) throw new NullPointerException();
        return  LocalDate.parse(rawField, DateTimeFormatter.ofPattern("dd.LL.yyyy"));
    }
    private String parseAlphaDigit(String rawField)
    {
        if (rawField == null || rawField.isEmpty()) throw new NullPointerException("String cannot be empty or null");
        if (!(rawField.matches("[A-Za-zА-Яа-я0-9 ]+")))
            throw new IllegalArgumentException("Invalid format, expected string or number");
        return rawField;
    }

    private SchemeType parseScheme(String rawField){
        if(rawField.equals("olympic")) return SchemeType.OLYMPIC;
        if(rawField.equals("round")) return SchemeType.ROUND;
        throw new IllegalArgumentException("expected type scheme");
    }

    private  void initCommandsMap() {
        commandMap = new HashMap<>();
        commandMap.put("undo", new CommandInfo("undo", new ArrayList<>(), "command"));
        commandMap.put("start", new CommandInfo("start", new ArrayList<>(), "command"));
        commandMap.put("finish", new CommandInfo("finish", new ArrayList<>(), "command"));
        commandMap.put("show grid", new CommandInfo("show grid", new ArrayList<>(), "command"));
        commandMap.put("show schedule", new CommandInfo("show schedule", new ArrayList<>(), "command"));
        commandMap.put("show players", new CommandInfo("show players", new ArrayList<>(), "command"));
        commandMap.put("show locations", new CommandInfo("show locations", new ArrayList<>(), "command"));
        commandMap.put("set match result", new CommandInfo("set match result", Arrays.asList(ArgumentType.DIGIT, ArgumentType.DIGIT, ArgumentType.DIGIT), "command: matchNumber, number, number"));
        commandMap.put("set player", new CommandInfo("set player", Arrays.asList(ArgumentType.ALPHA_DIGIT, ArgumentType.ALPHA_DIGIT, ArgumentType.DATE), "command: first name, second name, yyyy-mm-dd"));
        commandMap.put("set location", new CommandInfo("set location", Arrays.asList(ArgumentType.ALPHA_DIGIT, ArgumentType.ALPHA_DIGIT), "command: name location, description"));
        commandMap.put("set setting", new CommandInfo("set setting",Arrays.asList(ArgumentType.ALPHA_DIGIT, ArgumentType.SCHEME, ArgumentType.DATE_TIME),"command: tournament name, type scheme (olympic/round ...), date start (yyyy-mm-dd-hh-mm)"));
        commandMap.put("create tournament", new CommandInfo("create tournament",new ArrayList<>(),"command"));
        commandMap.put("help", new CommandInfo("help",new ArrayList<>(),"command"));
        commandMap.put("report", new CommandInfo("report",new ArrayList<>(),"command"));
        commandMap.put("choose tournament", new CommandInfo("choose tournament", Collections.singletonList(ArgumentType.ALPHA_DIGIT),"command"));
    }
}





