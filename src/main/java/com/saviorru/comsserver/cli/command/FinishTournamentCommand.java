package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.domain.tournament.Tournament;

public class FinishTournamentCommand implements Command {

    private Tournament tournament;

    public FinishTournamentCommand(Tournament tournament) {
        if (tournament == null) throw new NullPointerException("Tournament not created");
        this.tournament = tournament;
    }

    @Override
    public Boolean execute(){
        tournament.finish();
        return true;
    }
}
