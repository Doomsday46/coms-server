package com.saviorru.comsserver.cli;

import com.saviorru.comsserver.cli.command.*;

public class CommandFactory {

    private TournamentBuilder tournamentBuilder;

    public CommandFactory(TournamentBuilder tournamentBuilder) {
        this.tournamentBuilder = tournamentBuilder;
    }

    public Command getCommand(CommandParameter commandParameter) {
        switch (commandParameter.getNameCommand()) {
            case "help":/* return new HelpCommand(commandsMap);*/
                break;
            case "set player":
                return new SetPlayerCommand(tournamentBuilder, commandParameter);
            case "show schedule":
                return new ShowScheduleCommand(tournamentBuilder);
            case "show players":
                return new ShowPlayersCommand(tournamentBuilder);
            case "show locations":
                return new ShowLocationCommand(tournamentBuilder);
            case "set location":
                return new SetLocationCommand(tournamentBuilder, commandParameter);
            case "start":
                return new StartTournamentCommand(tournamentBuilder);
            case "finish":
                return new FinishTournamentCommand(tournamentBuilder);
            case "create tournament":
                return new CreateTennisTournamentCommand(tournamentBuilder);
            case "set match result":
                return new SetMatchResultCommand(tournamentBuilder, commandParameter);
            case "set setting":
                return new SetSettingTournamentCommand(tournamentBuilder, commandParameter);
            case "show grid":
                return new ShowGridCommand(tournamentBuilder);
            case "report":
                return new ReportCommand(tournamentBuilder);
        }
        return null;
    }
}
