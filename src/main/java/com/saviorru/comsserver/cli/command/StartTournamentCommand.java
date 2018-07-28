package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.domain.tournament.Tournament;

public class StartTournamentCommand implements Command {

    private Tournament tournament;

    public StartTournamentCommand(Tournament tournament) {
        if (tournament == null) throw new NullPointerException("Tournament not created");
        this.tournament = tournament;
    }

    @Override
    public Boolean execute(){
        tournament.start();
        return true;
    }


}
