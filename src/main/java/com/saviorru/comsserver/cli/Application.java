package com.saviorru.comsserver.cli;


import com.saviorru.comsserver.cli.*;
import com.saviorru.comsserver.domain.tournament.TournamentBuilder;
import com.saviorru.comsserver.domain.tournament.TournamentManager;

import java.time.format.DateTimeParseException;
import java.util.*;


public class Application {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String command = "";
        TournamentService tournamentService = new TournamentService(new TournamentBuilder(),new TournamentManager(),new CommandFactory());
        Interpreter controller = new Interpreter();
        while(true){

            System.out.print("Введите команду: ");
            command = scanner.nextLine();

            try {
                if(tournamentService.executeCommand(controller.parse(command))) System.out.println("Done");
            } catch(IllegalArgumentException | NullPointerException e){
               System.out.println(e.getMessage());
            }catch (DateTimeParseException e){
                System.out.println("Invalid format date and time");
            }
            if(command.equals("exit")) break;
        }
        System.out.println("Программа завершена");
    }

}
