package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.cli.TournamentBuilder;

public class FinishTournamentCommand implements Command {

    private TournamentBuilder TournamentBuilder;

    public FinishTournamentCommand(TournamentBuilder TournamentBuilder) {
        if (TournamentBuilder == null) throw new NullPointerException();
        this.TournamentBuilder = TournamentBuilder;
    }

    @Override
    public Boolean execute() throws Exception {
        TournamentBuilder.getTournament().finish();
        return true;
    }
}
