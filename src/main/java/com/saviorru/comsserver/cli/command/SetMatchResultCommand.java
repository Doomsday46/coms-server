package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.cli.CommandParameter;
import com.saviorru.comsserver.cli.TournamentBuilder;
import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.model.Score;
import com.saviorru.comsserver.domain.tournament.Tournament;

public class SetMatchResultCommand implements Command {

    private Score score;
    private TournamentBuilder tournamentBuilder;
    private Integer matchNumber;

    public SetMatchResultCommand(TournamentBuilder tournamentBuilder, CommandParameter commandParameter) throws NullPointerException,IllegalArgumentException {
        if ((Integer)commandParameter.getParameter(1) < 0 || (Integer)commandParameter.getParameter(2) < 0 || tournamentBuilder == null) throw new NullPointerException();
        this.tournamentBuilder = tournamentBuilder;
        this.score = new Score((Integer)commandParameter.getParameter(1), (Integer)commandParameter.getParameter(2));
        this.matchNumber = (Integer)commandParameter.getParameter(0);
    }

    @Override
    public Boolean execute() throws Exception {
        Match match = tournamentBuilder.getTournament().getSchedule().getAllMatches().get(matchNumber);
        tournamentBuilder.getTournament().finishMatch(match, score);
        return true;
    }

}
