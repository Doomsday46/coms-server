package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.cli.TournamentBuilder;

public class CreateTennisTournamentCommand implements Command {

    private TournamentBuilder tournamentBuilder;

    public CreateTennisTournamentCommand(TournamentBuilder tournamentBuilder) {
        this.tournamentBuilder = tournamentBuilder;
    }

    @Override
    public Boolean execute() throws Exception {
        tournamentBuilder.build();
        return true;
    }

}
