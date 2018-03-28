package com.saviorru.comsserver.domain.schedule;

import com.saviorru.comsserver.domain.MatchState;
import com.saviorru.comsserver.domain.model.Location;
import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.model.Player;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface Schedule {
    public List<Match> getAllMatches();
    public void addMatch(Match match);
    public void addMatches(List<Match> matches);
    public List<Match> getMatchesByState(MatchState state);
    public List<Match> getMatchesByPlayer(Player player);
    public List<Match> getMatchesByDate(LocalDateTime date);
    public List<Match> getMatchesByLocation(Location location);

}
