package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.domain.tournament.TournamentBuilder;
import com.saviorru.comsserver.domain.tournament.TournamentManager;

public class CreateTennisTournamentCommand implements Command {

    private TournamentBuilder tournamentBuilder;
    private TournamentManager tournamentManager;

    public CreateTennisTournamentCommand(TournamentManager tournamentManager, TournamentBuilder tournamentBuilder) {
        this.tournamentBuilder = tournamentBuilder;
        this.tournamentManager = tournamentManager;
    }

    @Override
    public Boolean execute(){
        tournamentManager.addTournament(tournamentBuilder.build());
        this.tournamentBuilder = this.tournamentBuilder.clearBuilder();
        return true;
    }

}
