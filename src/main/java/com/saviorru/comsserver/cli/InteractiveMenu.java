package com.saviorru.comsserver.cli;

import com.saviorru.comsserver.domain.tournament.TournamentBuilder;
import com.saviorru.comsserver.domain.tournament.TournamentManager;

import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class InteractiveMenu {
    private TournamentService tournamentService;
    private Interpreter controller;
    private Scanner scanner;
    private String commandLine;
    private int countElement;
    private int step;
    private int countTournament;
    private ResourceBundle resourceBundle;

    public InteractiveMenu(ResourceBundle resourceBundle) {
        scanner = new Scanner(System.in);
        commandLine = "";
        tournamentService = new TournamentService(new TournamentBuilder(), new TournamentManager(), new CommandFactory());
        controller = new InterpreterVitaliyStyle();
        this.resourceBundle = resourceBundle;
    }

    public void run() {
        System.out.println(resourceBundle.getObject("training.greeting"));
        step = 1;
        countElement = 0;
        countTournament = tournamentService.getTournamentManager().getTournaments().size();
        while (true) {

            if (step == 1) {
                fisrtStepMenu();
            }
            if (step == 2) {
                secondStepMenu();
            }
            if (step == 3) {
                thirdStepMenu();
            }
            if (step == 4) {
                fourthStepMenu();
            }
            if (step == 5) {
                closeTrainig();
            }
            if (step == 0) {
                System.out.print(resourceBundle.getObject("enterCommand"));
                commandLine = scanner.nextLine();

                try {
                    if (tournamentService.executeCommand(controller.parse(commandLine)))
                        System.out.println(resourceBundle.getObject("done"));
                } catch (IllegalArgumentException | NullPointerException e) {
                    System.out.println(e.getMessage());
                } catch (DateTimeParseException e) {
                    System.out.println(resourceBundle.getObject("invalidFormat"));
                }
                if (commandLine.equals("exit")) break;
            }

        }

    }

    private void closeTrainig() {
        System.out.println(resourceBundle.getObject("training.step5"));
        while (true) {
            System.out.print(resourceBundle.getObject("enterCommand"));
            commandLine = scanner.nextLine();
            executeCommand(commandLine);
            if (commandLine.equals("new tournament")) {
                step = 1;
                countElement = 0;
                break;
            } else if (commandLine.equals("current tournament")) {
                step = 0;
                countElement = 0;
                break;
            }
        }
    }

    private void fourthStepMenu() {
        System.out.println(resourceBundle.getObject("training.step4"));
        while (true) {
            System.out.print(resourceBundle.getObject("enterCommand"));
            commandLine = scanner.nextLine();
            executeCommand(commandLine);
            if (countElement == 1) {
                System.out.println(resourceBundle.getObject("tournamentC"));
                step++;
                countElement = 0;
                break;
            } else System.out.println(resourceBundle.getObject("tournamentNC"));
        }

    }

    private void thirdStepMenu() {
        System.out.println(resourceBundle.getObject("training.step3"));
        while (true) {
            System.out.print(resourceBundle.getObject("enterCommand"));
            commandLine = scanner.nextLine();
            executeCommand(commandLine);
            if (countElement == 1) {
                step++;
                countElement = 0;
                break;
            } else System.out.println(resourceBundle.getObject("notSetSetting"));
        }
    }

    private void secondStepMenu() {
        System.out.println(resourceBundle.getObject("training.step2"));
        while (true) {
            System.out.print(resourceBundle.getObject("enterCommand"));
            commandLine = scanner.nextLine();
            executeCommand(commandLine);
            if (commandLine.equals("enough")) {
                if (countElement >= 1) {
                    step++;
                    countElement = 0;
                    break;
                } else System.out.println(resourceBundle.getObject("notEnoughLocations"));
            }
        }
    }

    private void fisrtStepMenu() {
        System.out.println(resourceBundle.getObject("training.step1"));
        while (true) {
            System.out.print(resourceBundle.getObject("enterCommand"));
            commandLine = scanner.nextLine();
            executeCommand(commandLine);
            if (commandLine.equals("enough")) {
                if (countElement >= 2) {
                    step++;
                    countElement = 0;
                    break;
                } else {
                    System.out.print(resourceBundle.getObject("notEnoughPlayers"));
                    System.out.println(" " + (2 - countElement));
                }
            }
        }
    }

    private void executeCommand(String string) {
        try {
            if (tournamentService.executeCommand(controller.parse(string)))  System.out.println(resourceBundle.getObject("done"));;
            countElement++;
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println(resourceBundle.getObject("invalidFormat"));
        }
    }

}
