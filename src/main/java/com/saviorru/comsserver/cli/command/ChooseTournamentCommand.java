package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.cli.CommandParameter;
import com.saviorru.comsserver.domain.tournament.TournamentManager;

public class ChooseTournamentCommand implements Command {

    private TournamentManager tournamentManager;
    private CommandParameter commandParameter;

    public ChooseTournamentCommand(TournamentManager tournamentManager,CommandParameter commandParameter) {
        this.tournamentManager = tournamentManager;
        this.commandParameter = commandParameter;
    }

    @Override
    public Boolean execute(){
        if(commandParameter.getParameter(0) instanceof Integer) tournamentManager.setActiveTournament((Integer)commandParameter.getParameter(0));
        else tournamentManager.setActiveTournament((String)commandParameter.getParameter(0));
        return true;
    }
}
