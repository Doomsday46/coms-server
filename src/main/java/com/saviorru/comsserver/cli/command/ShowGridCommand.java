package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.domain.tournament.Tournament;

public class ShowGridCommand implements Command {

    private Tournament tournament;

    public ShowGridCommand(Tournament tournament) {
        this.tournament = tournament;
    }

    @Override
    public Boolean execute(){
        System.out.print(tournament.getPlayerGrid().toString());
        return true;
    }
}
