package com.saviorru.comsserver.domain.schedule;

import com.saviorru.comsserver.domain.dispatcher.DateService;
import com.saviorru.comsserver.domain.dispatcher.LocationService;
import com.saviorru.comsserver.domain.dispatcher.PlayerService;
import com.saviorru.comsserver.domain.model.Location;
import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.model.OneOnOneMatch;
import com.saviorru.comsserver.domain.schematictype.Scheme;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class ScheduleGeneratorImpl implements ScheduleGenerator {

    private PlayerService playerService;
    private LocationService locationService;
    private DateService dateService;
    private Scheme tournamentScheme;


    public ScheduleGeneratorImpl(PlayerService playerService, LocationService locationService, DateService dateService, Scheme scheme) {
        if (playerService == null || dateService == null || locationService == null || scheme == null)
            throw new NullPointerException();
        this.playerService = playerService;
        this.locationService = locationService;
        this.dateService = dateService;
        this.tournamentScheme = scheme;

    }

    @Override
    public Schedule generateSchedule(){
        Schedule newSchedule = new ScheduleImpl();
        newSchedule.addMatches(this.createMatches());
        return newSchedule;
    }

    @Override
    public Schedule updateSchedule(List<Match> matchesList, Schedule existingSchedule){
        if (matchesList == null || existingSchedule == null) throw new NullPointerException();
        for (Match match: matchesList)
        {
            if (match == null) throw new NullPointerException();
        }
        List<Integer> winnersList = new ArrayList<>();
        for (Match match: matchesList)
        {
            winnersList.add(playerService.getPlayerNumber(match.getWinner()));
        }
        this.tournamentScheme.updateScheme(winnersList);
        List<Match> newMatches = this.createMatches();
        if (newMatches.size() == 0) return existingSchedule;
        existingSchedule.addMatches(newMatches);
        return existingSchedule;
    }
    @Override
    public Schedule updateSchedule(Match match, Schedule existingSchedule){
        if (match== null || existingSchedule == null) throw new NullPointerException();
        List<Integer> winnersList = new ArrayList<>();
        winnersList.add(playerService.getPlayerNumber(match.getWinner()));
        this.tournamentScheme.updateScheme(winnersList);
        List<Match> newMatches = this.createMatches();
        if (newMatches.size() == 0) return existingSchedule;
        existingSchedule.addMatches(newMatches);
        return existingSchedule;
    }
    
    private List<Match> createMatches()
    {
        List<Match> matchesList = new ArrayList<>();
        List<Location> freeLocations = this.locationService.getAllFreeLocations();
        if (freeLocations.size() == 0)
            return matchesList;
        for (Location location: freeLocations)
        {
            Pair<Integer,Integer> playerPair = tournamentScheme.getNextNotPlayedPair();
            if (playerPair == null) break;
            locationService.reserveLocation(location);
            Match newMatch = new OneOnOneMatch(playerService.getPlayerByNumber(playerPair.getKey()),
                    playerService.getPlayerByNumber(playerPair.getValue()), location, dateService.getNextDate());
            matchesList.add(newMatch);
        }
        return matchesList;
    }

    public Scheme getScheme() {
        return tournamentScheme;
    }
}
