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
        System.out.println("Для того что бы создать турнир необходимо: ");
        System.out.println("ввести игроков, добавить места проведения и установить настройки.");
        System.out.println("Если хотите что бы на каждом этапе создания турнира появлялись подсказки введите: yes");
        System.out.println("Если вы знаете все команды этой программы и как с ними работать или же рядом сидит програмиист, который это делал, введите: no");
        while(true) {
            System.out.print("Введите команду: ");
            command = scanner.nextLine();
            if(command.equals("no")) break;
            else {
                if(command.equals("yes")) {
                    new InteractiveMenu().run();
                    return;
                }else{
                    System.out.println("Необходимо ввести yes или no");
                }
            }
        }
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
