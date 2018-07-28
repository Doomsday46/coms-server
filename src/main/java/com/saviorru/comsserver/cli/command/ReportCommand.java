package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.domain.tournament.Tournament;
import com.saviorru.comsserver.domain.tournament.TournamentReport;

public class ReportCommand implements Command {
    private Tournament tournament;

    public ReportCommand(Tournament tournament) {
        if (tournament == null) throw new NullPointerException("Tournament not created");
        this.tournament = tournament;
    }

    @Override
    public Boolean execute(){
        TournamentReport report = new TournamentReport(tournament);
        System.out.print(report);
        return true;
    }
}
