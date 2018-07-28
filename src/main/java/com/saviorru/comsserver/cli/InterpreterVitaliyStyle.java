package com.saviorru.comsserver.cli;

import com.saviorru.comsserver.cli.command.CommandInfo;
import com.saviorru.comsserver.localization.SingletonResourceBundle;

import java.io.InputStream;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

public class InterpreterVitaliyStyle implements Interpreter {
    private CommandParser parser;
    private Map<String, CommandInfo> commandsMap;
    private CommandRules commandRules;
    private SingletonResourceBundle resourceBundle;

    public InterpreterVitaliyStyle(){
        String RESOURCE = "programText";
        resourceBundle = new SingletonResourceBundle(RESOURCE);
        parser = new CommandParser();
    }

    @Override
    public void parse(InputStream in) {
        Scanner scanner = new Scanner(in);
        String command;

        ResourceBundle resourceBundle = SingletonResourceBundle.getResourceBundle();
        System.out.println(resourceBundle.getObject("startApp"));

        while (true) {
            System.out.println(resourceBundle.getObject("enterCommand"));
            command = scanner.nextLine().trim();
            if (command.equals("no")) break;
            else {
                if (command.equals("yes")) {
                    new InteractiveMenu(resourceBundle).run();
                    return;
                } else {
                    System.out.println(resourceBundle.getObject("chooseYesNo"));
                }
            }
        }

        while(true){

            System.out.print(resourceBundle.getObject("enterCommand"));
            command = scanner.nextLine();
            try {
                if (parser.parse(command).execute()) System.out.println(resourceBundle.getObject("done"));
            } catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }catch (NullPointerException e){
                if (e.getMessage() == null) System.out.println(resourceBundle.getObject("unDone"));
                else System.out.println(e.getMessage());
            }catch (DateTimeParseException e){
                System.out.println(resourceBundle.getObject("invalidFormat"));
            }
            if(command.equals("exit")) break;
        }
        System.out.print(resourceBundle.getObject("closeApp"));

    }
}
