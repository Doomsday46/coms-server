package com.saviorru.comsserver.domain.winnerindetifier;

import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.model.Player;

import java.util.Collections;
import java.util.List;

public class OlympicWinnerIndentifier implements WinnerIdentifier {


    public OlympicWinnerIndentifier() {

    }

    @Override
    public List<Player> identifyWinners(List<Match> finishedMatches){
        if (finishedMatches == null) throw new NullPointerException();
        if(finishedMatches.isEmpty()) throw new IllegalArgumentException("List winners is empty!");
        Match match = finishedMatches.get(0);
        for (int i = 1; i < finishedMatches.size(); i++) {
            if (finishedMatches.get(i).getDate().isAfter(match.getDate())) {
                match = finishedMatches.get(i);
            }
        }
        return Collections.singletonList(match.getWinner());
    }
}
