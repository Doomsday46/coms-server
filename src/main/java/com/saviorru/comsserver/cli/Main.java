package com.saviorru.comsserver.cli;


import com.saviorru.comsserver.cli.*;

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
                System.out.print("\n");
                System.out.print(output);
                System.out.print("\n");
            } catch(Exception e){
               System.out.println(Arrays.toString(e.getStackTrace()));
            }
            if(command.equals("exit")) break;
        }
        System.out.println("Программа завершена");
    }

}
