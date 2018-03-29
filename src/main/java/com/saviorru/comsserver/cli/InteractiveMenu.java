package com.saviorru.comsserver.cli;

import com.saviorru.comsserver.domain.tournament.TournamentBuilder;
import com.saviorru.comsserver.domain.tournament.TournamentManager;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InteractiveMenu {
    private TournamentService tournamentService;
    private Interpreter controller;
    private Scanner scanner;
    private String commandLine;
    private int countElement;
    private int step;
    private int countTournament;

    public InteractiveMenu() {
        scanner = new Scanner(System.in);
        commandLine = "";
        tournamentService = new TournamentService(new TournamentBuilder(), new TournamentManager(), new CommandFactory());
        controller = new Interpreter();
    }

    public void run() {
        System.out.println("Добро пожаловать, приготовтесь сейчас будем создавать турнир");
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
                System.out.print("Введите команду: ");
                commandLine = scanner.nextLine();

                try {
                    if (tournamentService.executeCommand(controller.parse(commandLine))) System.out.println("Done");
                } catch (IllegalArgumentException | NullPointerException e) {
                    System.out.println(e.getMessage());
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid format date and time");
                }
                if (commandLine.equals("exit")) break;
            }

        }

    }

    private void closeTrainig() {
        System.out.println("Теперь вы можете начать турнир командой start");
        System.out.println("Посмотреть игроков: show players");
        System.out.println("Посмотреть места проведения матчей: show location");
        System.out.println("Посмотреть расписание: show schedule");
        System.out.println("Посмотреть сетку игроков: show grid");
        System.out.println("Установить результат матча : set result match");
        System.out.println("Формат  set result match: number match, score first players, score second players");
        System.out.println("Пример  set result match: 1, 10, 11");
        System.out.println("Закончить турнир finish");
        System.out.println("Важно, нельзя закончить турнир, если не сыграны все матчи, это не спортивно!");
        System.out.println("Если хотите создать еще турнир введите new tournament");
        System.out.println("Иначе введите current tournament и можете вводить все выше перечисленные команды");
        while (true) {
            System.out.print("Введите команду: ");
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
        System.out.println("Теперь вы можете создать турнир командой create tournament");
        System.out.println("Формат  команда");
        System.out.println("Пример  create tournament");
        while (true) {
            System.out.print("Введите команду: ");
            commandLine = scanner.nextLine();
            executeCommand(commandLine);
            if (countElement == 1) {
                System.out.println("Турнир создан!");
                step++;
                countElement = 0;
                break;
            } else System.out.println("Вы не создали турнир!");
        }

    }

    private void thirdStepMenu() {
        System.out.println("Установите настройки турнира set setting");
        System.out.println("Формат  set setting: name tournament, type scheme, date and time(dd-mm-yyyy HH-MM)");
        System.out.println("Пример   set setting: testTournament, olympic, 10-05-2018 10-00");
        System.out.println("Важно: нельзя устанавливать прошедшую дату");
        System.out.println("Для выхода введите enough");
        while (true) {
            System.out.print("Введите команду: ");
            commandLine = scanner.nextLine();
            executeCommand(commandLine);
            if (countElement == 1) {
                step++;
                countElement = 0;
                break;
            } else System.out.println("Вы не установили настройки!");
        }
    }

    private void secondStepMenu() {
        System.out.println("Введите данные о месте проведение матчей с помощью команды set location");
        System.out.println("Формат  set location: name place, description");
        System.out.println("Пример  set location: Marta, table");
        System.out.println("Для выхода введите enough");
        while (true) {
            System.out.print("Введите команду: ");
            commandLine = scanner.nextLine();
            executeCommand(commandLine);
            if (commandLine.equals("enough")) {
                if (countElement >= 1) {
                    step++;
                    countElement = 0;
                    break;
                } else System.out.println("Не достаточно мест для игр, необходимо хотя бы одно!");
            }
        }
    }

    private void fisrtStepMenu() {
        System.out.println("Введите данные игрока с помощью команды set player");
        System.out.println("Формат  set player: first name,second name, year birth (dd-mm-yyyy)");
        System.out.println("Пример  set player: Artem, Neobakov, 10-05-1995");
        System.out.println("Важно: возраст игрока должен быть не моложе 7 лет");
        System.out.println("Для выхода введите enough");
        while (true) {
            System.out.print("Введите команду: ");
            commandLine = scanner.nextLine();
            executeCommand(commandLine);
            if (commandLine.equals("enough")) {
                if (countElement >= 2) {
                    step++;
                    countElement = 0;
                    break;
                } else System.out.println("Не достаточно игроков, нужно на " + (2 - countElement) + "больше");
            }
        }
    }

    private void executeCommand(String string) {
        try {
            if (tournamentService.executeCommand(controller.parse(string))) System.out.println("Done");
            countElement++;
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid format date and time");
        }
    }

}
