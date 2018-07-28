package com.saviorru.comsserver.domain.winnerindetifier;

import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.model.Player;

import java.util.List;

public interface WinnerIdentifier {
    public List<Player> identifyWinners (List<Match> finishedMatches);
}
