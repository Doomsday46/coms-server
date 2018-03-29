package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.cli.CommandParameter;
import com.saviorru.comsserver.domain.tournament.TournamentBuilder;
import com.saviorru.comsserver.domain.model.Player;

import java.time.LocalDate;

public class SetPlayerCommand implements Command {

    private TournamentBuilder tournamentBuilder;
    private CommandParameter commandParameter;

    public SetPlayerCommand(TournamentBuilder tournamentBuilder, CommandParameter commandParameter) {
        this.tournamentBuilder = tournamentBuilder;
        this.commandParameter = commandParameter;
    }

    @Override
    public Boolean execute(){
        tournamentBuilder.getPlayerDispatcher().addPlayer(new Player((String) commandParameter.getParameter(0),
                                                                     (String) commandParameter.getParameter(1),
                                                                     (LocalDate) commandParameter.getParameter(2)));
        return true;
    }

}
