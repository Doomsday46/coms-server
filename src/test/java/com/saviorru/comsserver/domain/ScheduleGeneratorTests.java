package com.saviorru.comsserver.domain;

import com.saviorru.comsserver.domain.dispatcher.DateService;
import com.saviorru.comsserver.domain.dispatcher.LocationService;
import com.saviorru.comsserver.domain.dispatcher.PlayerService;
import com.saviorru.comsserver.domain.schedule.Schedule;
import com.saviorru.comsserver.domain.schedule.ScheduleGenerator;
import com.saviorru.comsserver.domain.schedule.ScheduleGeneratorImpl;
import com.saviorru.comsserver.domain.schematictype.OlympicScheme;
import com.saviorru.comsserver.domain.model.Location;
import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.model.Player;
import com.saviorru.comsserver.domain.schematictype.RoundScheme;
import com.saviorru.comsserver.domain.schematictype.Scheme;
import org.junit.Before;
import static org.mockito.Mockito.*;
import org.junit.Test;

import java.time.LocalDateTime;

import static junit.framework.TestCase.*;

public class ScheduleGeneratorTests {
    private ScheduleGenerator testSubject;
    private PlayerService playerService;
    private LocationService locationService;
    private DateService dateService;
    private Scheme schemeType;
    private Location loc1;
    private Location loc2;
    private Location loc3;
    @Before
    public void initTest() throws Exception
    {
        playerService = new PlayerService();
        playerService.addPlayer(mock(Player.class));
        playerService.addPlayer(mock(Player.class));
        playerService.addPlayer(mock(Player.class));
        playerService.addPlayer(mock(Player.class));
        playerService.addPlayer(mock(Player.class));
        playerService.addPlayer(mock(Player.class));
        playerService.addPlayer(mock(Player.class));
        playerService.addPlayer(mock(Player.class));
        locationService = new LocationService();
        loc1 = new Location("1", "");
        loc2 = new Location("2", "");
        loc3 = new Location("3", "");
        locationService.addLocation(loc1);
        locationService.addLocation(loc2);
        locationService.addLocation(loc3);
        dateService = new DateService(LocalDateTime.now(), new TimeSettings(10, 18, 12));


    }

    //round scheme tests

    @Test()
    public void genRoundGenerateTest() throws Exception
    {
        schemeType = new RoundScheme(playerService.getAllPlayers().size());
        testSubject = new ScheduleGeneratorImpl(playerService, locationService, dateService, schemeType);
       Schedule schedule =  testSubject.generateSchedule();
       assertEquals(3, schedule.getAllMatches().size());
    }
    @Test()
    public void genRoundUpdateTest() throws Exception
    {
        schemeType = new RoundScheme(playerService.getAllPlayers().size());
        testSubject = new ScheduleGeneratorImpl(playerService, locationService, dateService, schemeType);
        Schedule schedule =  testSubject.generateSchedule();
        Match match1 = schedule.getMatchesByState(MatchState.NOTPLAYED).get(0);
        Match match2 = schedule.getMatchesByState(MatchState.NOTPLAYED).get(1);
        match1.setPoints(1,0);
        match2.setPoints(1,0);
        match1.setMatchState(MatchState.PLAYED);
        match2.setMatchState(MatchState.PLAYED);
        locationService.freeLocation(loc1);
        schedule = testSubject.updateSchedule(match1, schedule);
        locationService.freeLocation(loc2);
        schedule = testSubject.updateSchedule(match2, schedule);
        assertEquals(5, schedule.getAllMatches().size());
    }
    @Test()
    public void genRoundUpdateLoopTest() throws Exception
    {
        schemeType = new RoundScheme(playerService.getAllPlayers().size());
        testSubject = new ScheduleGeneratorImpl(playerService, locationService, dateService, schemeType);
        Schedule schedule =  testSubject.generateSchedule();
        while (schedule.getAllMatches().size() < 10) {
            Match match1 = schedule.getMatchesByState(MatchState.NOTPLAYED).get(0);
            match1.setPoints(1, 0);
            match1.setMatchState(MatchState.PLAYED);
            locationService.freeLocation(loc1);
            schedule = testSubject.updateSchedule(match1, schedule);
        }
        assertEquals(10, schedule.getAllMatches().size());
    }

    //olympic scheme tests
    @Test()
    public void genOlympGenerateTest() throws Exception
    {
        schemeType = new OlympicScheme(playerService.getAllPlayers().size());
        testSubject = new ScheduleGeneratorImpl(playerService, locationService, dateService, schemeType);
        Schedule schedule =  testSubject.generateSchedule();
        assertEquals(3, schedule.getAllMatches().size());
    }
}
