package com.saviorru.comsserver.cli;

import com.saviorru.comsserver.cli.command.*;

public class CommandFactory {

    private TournamentBuilder tournamentBuilder;

    public CommandFactory(TournamentBuilder tournamentBuilder){
        this.tournamentBuilder = tournamentBuilder;
    }

    public Command getCommand(CommandParameter commandParameter){
        if (commandParameter.getNameCommand().equals("help")) {
            //return new HelpCommand(commandsMap);
        }
        if (commandParameter.getNameCommand().equals("set player")) {
            return new SetPlayerCommand(tournamentBuilder, commandParameter);
        }
        if (commandParameter.getNameCommand().equals("show schedule")) {
            return new ShowScheduleCommand(tournamentBuilder);
        }
        if (commandParameter.getNameCommand().equals("show players")) {
            return new ShowPlayersCommand(tournamentBuilder);
        }
        if (commandParameter.getNameCommand().equals("show locations")) {
            return new ShowLocationCommand(tournamentBuilder);
        }
        if (commandParameter.getNameCommand().equals("set location")) {
            return new SetLocationCommand(tournamentBuilder, commandParameter);
        }
        if (commandParameter.getNameCommand().equals("start")) {
            return new StartTournamentCommand(tournamentBuilder);
        }
        if (commandParameter.getNameCommand().equals("finish")) {
            return new FinishTournamentCommand(tournamentBuilder);
        }
        if (commandParameter.getNameCommand().equals("create tournament")) {
            return new CreateTennisTournamentCommand(tournamentBuilder);
        }
        if (commandParameter.getNameCommand().equals("set match result")) {
            return new SetMatchResultCommand(tournamentBuilder, commandParameter);
        }
        if (commandParameter.getNameCommand().equals("set setting")) {
            return new SetSettingTournamentCommand(tournamentBuilder,commandParameter);
        }
        if (commandParameter.getNameCommand().equals("show grid")) {
           return new ShowGridCommand(tournamentBuilder);
        }
        if (commandParameter.getNameCommand().equals("report")) {
            return new ReportCommand(tournamentBuilder);
        }
        return null;
    }
}
