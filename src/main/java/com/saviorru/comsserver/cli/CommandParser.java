package com.saviorru.comsserver.cli;

import com.saviorru.comsserver.cli.command.Command;
import com.saviorru.comsserver.domain.schematictype.SchemeType;
import javafx.util.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CommandParser {
    private CommandParameter commandParameter;
    private Map<String, List<ArgumentType>> parsingRules;

    public CommandParser(){
        parsingRules = new HashMap<>();
    }

    public void addParsingRule(String commandName, List<ArgumentType> fieldsType) throws Exception {
        if (commandName.isEmpty()) throw new Exception("Empty command name");
        if (fieldsType == null) throw new NullPointerException("Null argument types list");
        if (this.parsingRules.containsKey(commandName))
            throw new Exception("Parsing rule for this command already exists");
        this.parsingRules.put(commandName, fieldsType);
    }


    public CommandParameter parse(String commandLine) throws Exception {
        if (commandLine.isEmpty()) return null;
        List<String> parts = new ArrayList<String>(Arrays.asList(commandLine.split(":")));
        String commandString = parts.get(0);
        commandString = commandString.toLowerCase();
        commandString = commandString.trim();
        List<String> rawArguments = new ArrayList<>();
        if (parts.size() > 1)
            rawArguments = new ArrayList<String>(Arrays.asList(parts.get(1).split(",")));
        if (!(this.parsingRules.containsKey(commandString)))
            throw new Exception("Invalid command");
        List<ArgumentType> argumentTypes = this.parsingRules.get(commandString);
        List<Object> parsedArguments = new ArrayList<>();
        if (rawArguments.size() != argumentTypes.size())
            throw new Exception("Invalid arguments count");
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
        return new CommandParameter(commandString,parsedArguments);
    }

    private Integer parseDigit(String rawField) throws IllegalArgumentException,NullPointerException {
        if (rawField == null || rawField.isEmpty()) throw new NullPointerException("String cannot be empty or null");
        if (!(rawField.matches("[0-9]+")))
            throw new IllegalArgumentException("Неверный формат аргумента");
        return Integer.parseInt(rawField);
    }

    private String parseAlpha(String rawField) throws IllegalArgumentException,NullPointerException {
        if (rawField == null || rawField.isEmpty()) throw new NullPointerException("String cannot be empty or null");
        if (!(rawField.matches("[A-Za-zА-Яа-я]+")))
            throw new IllegalArgumentException("Неверный формат аргумента");
        return rawField;
    }

    private LocalDateTime parseDateTime(String rawField) throws NullPointerException{
        if(rawField == null) throw new NullPointerException();
        return  LocalDateTime.parse(rawField, DateTimeFormatter.ofPattern("dd-LL-yyyy HH-mm"));
    }

    private LocalDate parseDate(String rawField) throws NullPointerException {
        if(rawField == null) throw new NullPointerException();
        return  LocalDate.parse(rawField, DateTimeFormatter.ofPattern("dd-LL-yyyy"));
    }
    private String parseAlphaDigit(String rawField) throws NullPointerException,IllegalArgumentException
    {
        if (rawField == null || rawField.isEmpty()) throw new NullPointerException("String cannot be empty or null");
        if (!(rawField.matches("[A-Za-zА-Яа-я0-9 ]+")))
            throw new IllegalArgumentException("Неверный формат аргумента");
        return rawField;
    }
    private SchemeType parseScheme(String rawField){
        if(rawField.equals("olympic")) return SchemeType.OLYMPIC;
        if(rawField.equals("round")) return SchemeType.ROUND;
        throw new IllegalArgumentException("scheme");
    }
}





