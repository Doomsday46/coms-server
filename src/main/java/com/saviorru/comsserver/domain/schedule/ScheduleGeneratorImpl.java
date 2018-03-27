package com.saviorru.comsserver.domain.schedule;

import com.saviorru.comsserver.domain.dispatcher.DateDispatcher;
import com.saviorru.comsserver.domain.dispatcher.LocationDispatcher;
import com.saviorru.comsserver.domain.dispatcher.PlayerDispatcher;
import com.saviorru.comsserver.domain.model.Location;
import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.model.OneOnOneMatch;
import com.saviorru.comsserver.domain.schematictype.Scheme;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class ScheduleGeneratorImpl implements ScheduleGenerator {

    private PlayerDispatcher playerDispatcher;
    private LocationDispatcher locationDispatcher;
    private DateDispatcher dateDispatcher;
    private Scheme tournamentScheme;


    public ScheduleGeneratorImpl(PlayerDispatcher playerDispatcher, LocationDispatcher locationDispatcher, DateDispatcher dateDispatcher, Scheme scheme)
    throws Exception {
        if (playerDispatcher == null || dateDispatcher == null || locationDispatcher == null || scheme == null)
            throw new NullPointerException();
        this.playerDispatcher = playerDispatcher;
        this.locationDispatcher = locationDispatcher;
        this.dateDispatcher = dateDispatcher;
        this.tournamentScheme = scheme;

    }

    @Override
    public Schedule generateSchedule() throws Exception {
        Schedule newSchedule = new ScheduleImpl();
        newSchedule.addMatches(this.createMatches());
        return newSchedule;
    }

    @Override
    public Schedule updateSchedule(List<Match> matchesList, Schedule existingSchedule) throws Exception {
        if (matchesList == null || existingSchedule == null) throw new NullPointerException();
        for (Match match: matchesList)
        {
            if (match == null) throw new NullPointerException();
        }
        List<Integer> winnersList = new ArrayList<>();
        for (Match match: matchesList)
        {
            winnersList.add(playerDispatcher.getPlayerNumber(match.getWinner()));
        }
        this.tournamentScheme.updateScheme(winnersList);
        List<Match> newMatches = this.createMatches();
        if (newMatches.size() == 0) return existingSchedule;
        existingSchedule.addMatches(newMatches);
        return existingSchedule;
    }
    @Override
    public Schedule updateSchedule(Match match, Schedule existingSchedule) throws Exception{
        if (match== null || existingSchedule == null) throw new NullPointerException();
        List<Integer> winnersList = new ArrayList<>();
        winnersList.add(playerDispatcher.getPlayerNumber(match.getWinner()));
        this.tournamentScheme.updateScheme(winnersList);
        List<Match> newMatches = this.createMatches();
        if (newMatches.size() == 0) return existingSchedule;
        existingSchedule.addMatches(newMatches);
        return existingSchedule;
    }
    
    private List<Match> createMatches() throws Exception
    {
        List<Match> matchesList = new ArrayList<>();
        List<Location> freeLocations = this.locationDispatcher.getAllFreeLocations();
        if (freeLocations.size() == 0)
            return matchesList;
        for (Location location: freeLocations)
        {
            Pair<Integer,Integer> playerPair = tournamentScheme.getNextNotPlayedPair();
            if (playerPair == null) break;
            locationDispatcher.reserveLocation(location);
            Match newMatch = new OneOnOneMatch(playerDispatcher.getPlayerByNumber(playerPair.getKey()),
                    playerDispatcher.getPlayerByNumber(playerPair.getValue()), location, dateDispatcher.getNextDate());
            matchesList.add(newMatch);
        }
        return matchesList;
    }

    public Scheme getScheme() {
        return tournamentScheme;
    }
}
