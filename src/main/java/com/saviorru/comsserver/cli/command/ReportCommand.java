package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.cli.TournamentBuilder;
import com.saviorru.comsserver.domain.tournament.Tournament;
import com.saviorru.comsserver.domain.tournament.TournamentReport;

public class ReportCommand implements Command {
    private TournamentBuilder tournamentBuilder;

    public ReportCommand(TournamentBuilder tournamentBuilder) {
        this.tournamentBuilder = tournamentBuilder;
    }

    @Override
    public Boolean execute() throws Exception {
        TournamentReport report = new TournamentReport(tournamentBuilder.getTournament());
        System.out.print(report);
        return true;
    }
}
