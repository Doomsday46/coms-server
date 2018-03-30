package com.saviorru.comsserver.cli;

import com.saviorru.comsserver.domain.tournament.TournamentBuilder;
import com.saviorru.comsserver.domain.tournament.TournamentManager;

import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ConsoleInterface {

    public void run(){
        Scanner scanner = new Scanner(System.in);
        String command = "";
        TournamentService tournamentService = new TournamentService(new TournamentBuilder(),new TournamentManager(),new CommandFactory());
        Interpreter controller = new Interpreter();
        new TextProgram("programText",new Locale("en","UK"));
        ResourceBundle resourceBundle = TextProgram.getResourceBundle();
        System.out.println(resourceBundle.getObject("startApp"));
        while(true) {
            System.out.println(resourceBundle.getObject("enterCommand"));
            command = scanner.nextLine().trim();
            if(command.equals("no")) break;
            else {
                if(command.equals("yes")) {
                    new InteractiveMenu(resourceBundle).run();
                    return;
                }else{
                    System.out.println(resourceBundle.getObject("chooseYesNo"));
                }
            }
        }
        while(true){

            System.out.print(resourceBundle.getObject("enterCommand"));
            command = scanner.nextLine();
            try {
                if(tournamentService.executeCommand(controller.parse(command))) System.out.println(resourceBundle.getObject("done"));
            } catch(IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }catch (NullPointerException e){
                if(e.getMessage() == null) System.out.println(resourceBundle.getObject("unDone"));
                else System.out.println(e.getMessage());
            }catch (DateTimeParseException e){
                System.out.println(resourceBundle.getObject("invalidFormat"));
            }
            if(command.equals("exit")) break;
        }
        System.out.print(resourceBundle.getObject("closeApp"));
    }
}
