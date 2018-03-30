package com.saviorru.comsserver.cli;

import com.saviorru.comsserver.domain.schematictype.SchemeType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandParser {
    private CommandRules commandRules;
    private List<String> rawArguments;
    private String nameCommand;
    public CommandParser(CommandRules commandRules){
        this.commandRules = commandRules;
    }

    public CommandParameter parse(String commandLine){
        if (commandLine.isEmpty()) return null;
        rawArguments = new ArrayList<>();
        List<ArgumentType> argumentTypes = parseString(commandLine);
        if (rawArguments.size() != argumentTypes.size())
            throw new IllegalArgumentException("Invalid arguments count");
        return getCommandParameters(argumentTypes,commandLine);
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
}





