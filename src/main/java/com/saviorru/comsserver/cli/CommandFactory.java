package com.saviorru.comsserver.cli;

import com.saviorru.comsserver.cli.command.*;
import com.saviorru.comsserver.domain.tournament.Tournament;
import com.saviorru.comsserver.domain.tournament.TournamentBuilder;
import com.saviorru.comsserver.domain.tournament.TournamentManager;

public class CommandFactory {


    public Command getCommand(CommandParameter commandParameter) {
        switch (commandParameter.getNameCommand()) {
            case "set player":
                return new SetPlayerCommand(commandParameter);
            case "show schedule": {
                return new ShowScheduleCommand();
            }
            case "show players":
                return new ShowPlayersCommand();
            case "show locations":
                return new ShowLocationCommand();
            case "set location":
                return new SetLocationCommand(commandParameter);
            case "start":
                return new StartTournamentCommand();
            case "finish":
                return new FinishTournamentCommand();
            case "create tournament":
                return new CreateTennisTournamentCommand();
            case "set match result":
                return new SetMatchResultCommand(commandParameter);
            case "set setting":
                return new SetSettingTournamentCommand(commandParameter);
            case "show grid":
                return new ShowGridCommand();
            case "report":
                return new ReportCommand();
            case "choose tournament":
                return new ChooseTournamentCommand(commandParameter);
        }
        throw new NullPointerException("Command not found");
    }
}
