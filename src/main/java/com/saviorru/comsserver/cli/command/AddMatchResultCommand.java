package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.cli.CommandParameter;
import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.model.Score;
import com.saviorru.comsserver.domain.tournament.Tournament;

public class AddMatchResultCommand implements Command {

    private Score score;
    private Tournament tournament;
    private Integer matchNumber;

    public AddMatchResultCommand(Tournament tournament, CommandParameter commandParameter) throws NullPointerException,IllegalArgumentException {
        if ((Integer)commandParameter.getParameter(1) < 0 || (Integer)commandParameter.getParameter(2) < 0) throw new IllegalArgumentException("Score less than zero");
        if (tournament == null) throw new NullPointerException("Tournament not created");
        this.tournament = tournament;
        this.score = new Score((Integer)commandParameter.getParameter(1), (Integer)commandParameter.getParameter(2));
        this.matchNumber = (Integer)commandParameter.getParameter(0);
    }

    @Override
    public Boolean execute(){
        Match match = tournament.getSchedule().getAllMatches().get(matchNumber);
        tournament.finishMatch(match, score);
        return true;
    }

}
