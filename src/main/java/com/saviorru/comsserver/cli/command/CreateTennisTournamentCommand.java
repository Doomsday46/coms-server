package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.domain.tournament.TournamentBuilder;
import com.saviorru.comsserver.domain.tournament.TournamentManager;

public class CreateTennisTournamentCommand implements Command {

    private TournamentBuilder tournamentBuilder;
    private TournamentManager tournamentManager;

    public CreateTennisTournamentCommand() {
        this.tournamentBuilder = new TournamentBuilder();
        this.tournamentManager = new TournamentManager();
    }

    @Override
    public Boolean execute(){

        tournamentManager.addTournament(tournamentBuilder.build());
        this.tournamentBuilder = this.tournamentBuilder.clearBuilder();
        return true;
    }

}
