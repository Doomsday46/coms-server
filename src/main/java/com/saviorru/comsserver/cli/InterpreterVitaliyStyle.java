package com.saviorru.comsserver.cli;

import com.saviorru.comsserver.cli.command.Command;
import com.saviorru.comsserver.cli.command.CommandInfo;
import com.saviorru.comsserver.localization.SingletonResourceBundle;

import java.io.InputStream;
import java.time.format.DateTimeParseException;
import java.util.*;

public class InterpreterVitaliyStyle implements Interpreter {
    private CommandParser parser;
    private Map<String, CommandInfo> commandsMap;
    private CommandRules commandRules;
    private SingletonResourceBundle resourceBundle;
    private final String RESOURCE = "programText";

    public InterpreterVitaliyStyle(){
        resourceBundle = new SingletonResourceBundle(RESOURCE);
        parser = new CommandParser();
    }
    @Override
    public Command parse(InputStream in) {
        Scanner scanner = new Scanner(in);
        String command;
        while(true){

            System.out.print(SingletonResourceBundle.getResourceBundle().getObject("enterCommand"));
            command = scanner.nextLine();
            try {
                if(tournamentService.executeCommand(controller.parse(command))) System.out.println(SingletonResourceBundle.getResourceBundle().getObject("done"));
            } catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }catch (NullPointerException e){
                if(e.getMessage() == null) System.out.println(SingletonResourceBundle.getResourceBundle().getObject("unDone"));
                else System.out.println(e.getMessage());
            }catch (DateTimeParseException e){
                System.out.println(SingletonResourceBundle.getResourceBundle().getObject("invalidFormat"));
            }
            if(command.equals("exit")) break;
        }
        return null;
    }
}
