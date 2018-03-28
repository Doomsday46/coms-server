package com.saviorru.comsserver.cli;


import com.saviorru.comsserver.cli.*;

import java.time.format.DateTimeParseException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String command = "";
        Interpreter controller = new Interpreter();
        while(true){

            System.out.print("Введите команду: ");
            command = scanner.nextLine();

            try {
                String output = controller.parse(command);
                System.out.println(output);
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
