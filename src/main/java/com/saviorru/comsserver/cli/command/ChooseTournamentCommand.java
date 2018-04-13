package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.cli.CommandParameter;
import com.saviorru.comsserver.domain.tournament.TournamentManager;

public class ChooseTournamentCommand implements Command {

    private TournamentManager tournamentManager;
    private CommandParameter commandParameter;

    public ChooseTournamentCommand(CommandParameter commandParameter) {
        this.tournamentManager = new TournamentManager();
        this.commandParameter = commandParameter;
    }

    @Override
    public Boolean execute(){
        tournamentManager.setActiveTournament(Integer.parseInt(commandParameter.getParameter(0).toString()));
        return true;
    }
}
