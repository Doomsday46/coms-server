package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.cli.TournamentBuilder;
import com.saviorru.comsserver.domain.tournament.Tournament;

import java.util.Scanner;

public class StartTournamentCommand implements Command {

    private TournamentBuilder tournamentBuilder;

    public StartTournamentCommand(TournamentBuilder tournamentBuilder) {
        this.tournamentBuilder = tournamentBuilder;
    }

    @Override
    public Boolean execute() throws Exception {
        tournamentBuilder.getTournament().start();
        return true;
    }


}
