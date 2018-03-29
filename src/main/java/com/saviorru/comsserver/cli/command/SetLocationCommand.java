package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.cli.CommandParameter;
import com.saviorru.comsserver.domain.tournament.TournamentBuilder;
import com.saviorru.comsserver.domain.model.Location;

public class SetLocationCommand implements Command {

    private TournamentBuilder tournamentBuilder;
    private CommandParameter commandParameter;

    public SetLocationCommand(TournamentBuilder tournamentBuilder, CommandParameter commandParameter) {
        this.tournamentBuilder = tournamentBuilder;
        this.commandParameter = commandParameter;
    }

    @Override
    public Boolean execute(){
        tournamentBuilder.getLocationDispatcher().addLocation(new Location((String)commandParameter.getParameter(0),(String)commandParameter.getParameter(1)));
        return true;
    }

}
