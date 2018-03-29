package com.saviorru.comsserver.cli;

import com.saviorru.comsserver.cli.command.*;
import com.saviorru.comsserver.domain.tournament.Tournament;
import com.saviorru.comsserver.domain.tournament.TournamentBuilder;
import com.saviorru.comsserver.domain.tournament.TournamentManager;

public class CommandFactory {

    public Command getCommand(TournamentManager tournamentManager, TournamentBuilder tournamentBuilder, CommandParameter commandParameter) {
        Tournament tournament = tournamentManager.getActiveTournament();
        switch (commandParameter.getNameCommand()) {
            case "help":/* return new HelpCommand(commandsMap);*/
                break;
            case "set player":
                return new SetPlayerCommand(tournamentBuilder, commandParameter);
            case "show schedule":
                return new ShowScheduleCommand(tournament);
            case "show players":
                return new ShowPlayersCommand(tournament);
            case "show locations":
                return new ShowLocationCommand(tournament);
            case "set location":
                return new SetLocationCommand(tournamentBuilder, commandParameter);
            case "start":
                return new StartTournamentCommand(tournament);
            case "finish":
                return new FinishTournamentCommand(tournament);
            case "create tournament":
                return new CreateTennisTournamentCommand(tournamentManager, tournamentBuilder);
            case "set match result":
                return new SetMatchResultCommand(tournament, commandParameter);
            case "set setting":
                return new SetSettingTournamentCommand(tournamentBuilder, commandParameter);
            case "show grid":
                return new ShowGridCommand(tournament);
            case "report":
                return new ReportCommand(tournament);
            case "choose tournament":
                return new ChooseTournamentCommand(tournamentManager, commandParameter);
        }
        return null;
    }
}
