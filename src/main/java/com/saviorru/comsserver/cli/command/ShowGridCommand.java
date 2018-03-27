package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.cli.TournamentBuilder;
import com.saviorru.comsserver.domain.tournament.Tournament;

public class ShowGridCommand implements Command {

    private TournamentBuilder tournamentBuilder;

    public ShowGridCommand(TournamentBuilder tournamentBuilder) {
        this.tournamentBuilder = tournamentBuilder;
    }

    @Override
    public Boolean execute() throws Exception {
        System.out.print(tournamentBuilder.getTournament().getPlayerGrid().toString());
        return true;
    }
}
