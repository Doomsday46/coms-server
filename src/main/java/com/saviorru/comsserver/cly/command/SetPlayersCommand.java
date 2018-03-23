package com.saviorru.comsserver.cly.command;

import com.saviorru.comsserver.cly.CommandParser;
import com.saviorru.comsserver.domain.dispatcher.PlayerDispatcher;
import com.saviorru.comsserver.domain.tournament.Tournament;
import javafx.util.Pair;

import java.util.List;
import java.util.Scanner;

public class SetPlayersCommand implements Command {

    private Integer countPlayers;
    private CommandParser commandParser;
    private PlayerDispatcher playerDispatcher;

    public SetPlayersCommand(CommandParser commandParser, Integer countPlayers, PlayerDispatcher playerDispatcher) throws Exception {
        if (countPlayers == null || countPlayers < 0) throw new Exception("Not correct value");
        this.countPlayers = countPlayers;
        this.commandParser = commandParser;
        this.playerDispatcher = playerDispatcher;
    }

    @Override
    public void backup() {

    }

    @Override
    public Boolean execute() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String command = "";
        while (countPlayers > 0) {
            System.out.print("Введите данные игрока");
            command = scanner.nextLine();
            Pair<String, List<String>> arguments = commandParser.parse(command);
            if (arguments.getKey().equals("set player")) {
                if (new SetPlayerCommand(playerDispatcher, arguments).execute())
                    countPlayers--;
                else System.out.println("Игрок не добавлен!");
            }
        }
        return true;
    }

    @Override
    public String nameCommand() {
        return "set players";
    }

    @Override
    public String commandFormat() {
        return "command: count players";
    }
}