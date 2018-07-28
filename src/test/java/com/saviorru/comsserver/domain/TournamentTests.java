package com.saviorru.comsserver.domain;

import com.saviorru.comsserver.domain.dispatcher.DateService;
import com.saviorru.comsserver.domain.dispatcher.PlayerService;
import com.saviorru.comsserver.domain.dispatcher.LocationService;
import com.saviorru.comsserver.domain.model.Location;
import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.model.Score;
import com.saviorru.comsserver.domain.model.Player;
import com.saviorru.comsserver.domain.schedule.Schedule;
import com.saviorru.comsserver.domain.schedule.ScheduleImpl;
import com.saviorru.comsserver.domain.schematictype.SchemeType;
import com.saviorru.comsserver.domain.tournament.TennisTournament;
import com.saviorru.comsserver.domain.tournament.Tournament;

import com.saviorru.comsserver.domain.tournament.TournamentSettings;
import com.saviorru.comsserver.domain.tournament.TournamentSettingsImpl;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;

public class TournamentTests {

    private Tournament tournament;
    private final int countPlayers = 8;
    private List<Player> playerList;
    private List<Location> locationList;
    private LocationService locationService;
    private Schedule schedule;
    private PlayerService playerService;
    private DateService dateService;
    private SchemeType schemeType;
    private TournamentSettings settings;
    private TimeSettings timeSettings;

    @Before
    public void init() throws Exception {
        playerList = new ArrayList<>();
        locationList = new ArrayList<>();
        locationList.add(new Location("table1", "1"));
        locationList.add(new Location("table2", "2"));
        locationList.add(new Location("table3", "3"));
        locationService = new LocationService();
        playerService = new PlayerService();
        dateService = new DateService(LocalDateTime.now(),new TimeSettings( 10, 18, 1));
        schedule = new ScheduleImpl();
        locationService.addAllLocation(locationList);
        for (int i = 0; i < countPlayers; i++) {
            playerList.add(mock(Player.class));
        }
        playerService.addPlayers(playerList);
        timeSettings = new TimeSettings(10, 18, 1);
        schemeType = SchemeType.OLYMPIC;
        settings = new TournamentSettingsImpl("tournament1", schemeType, LocalDateTime.now(),timeSettings);
        tournament = new TennisTournament(playerService, locationService, settings, schedule);
    }

    @Test(expected = NullPointerException.class)
    public void testInitNullParam() throws Exception {
        new TennisTournament(null, null, null, null);
    }

    @Test(expected = Exception.class)
    public void testInitEmptyParam() throws Exception {
        playerService = new PlayerService();
        new TennisTournament(playerService, locationService, settings, schedule);
    }

    @Test
    public void testGetName() {
        assertEquals("tournament1", tournament.getName());
    }

    @Test
    public void testGetPlayers() {
        assertTrue(playerList.containsAll(tournament.getPlayers()));
    }

    @Test(expected = Exception.class)
    public void testGetScheduleButTournamentNotStarted() throws Exception {
        tournament.getSchedule();
    }

    @Test
    public void testGetChampionButTournamentNotInit() throws Exception {
        assertEquals(null,tournament.getThePrizePlace(1));
    }

    @Test
    public void testStartTournament() throws Exception {
        tournament.start();
    }

    @Test(expected = Exception.class)
    public void testStartTournamentResultException() throws Exception {
        tournament.start();
        tournament.start();
    }

    @Test(expected = Exception.class)
    public void testFinishTournamentMatchesNotPlayed() throws Exception {
        tournament.start();
        tournament.finish();
    }

    @Test(expected = Exception.class)
    public void testFinishTournamentResultException() throws Exception {
        tournament.finish();
    }

    @Test
    public void testIsPlayedTrue() throws Exception {
        tournament.start();
        assertTrue(tournament.isStart());
    }

    @Test
    public void testIsPlayedFalse() throws Exception {
        assertFalse(tournament.isStart());
    }

    @Test()
    public void testFinishMatch() throws Exception {
        tournament.start();
        Score testScore = new Score();
        Match testMatch = tournament.getNextMatch();
        testScore.setPoints(1, 0);
        tournament.finishMatch(tournament.getNextMatch(), testScore);
        assertFalse(testMatch == tournament.getNextMatch());
    }

    @Test()
    public void testFinishMatches() throws Exception {
        tournament.start();
        List<Match> testList = tournament.getSchedule().getMatchesByState(MatchState.NOTPLAYED);
        List<Score> testListP = new ArrayList<>();
        for (int i = 0; i < testList.size(); i++) {
            testListP.add(new Score(1, 0));
        }
        Match testMatch = tournament.getNextMatch();
        tournament.finishMatches(testList, testListP);
        assertTrue(testList.size() == tournament.getSchedule().getMatchesByState(MatchState.PLAYED).size());
    }

    @Test()
    public void testGetNextMatch() throws Exception {
        tournament.start();
        Match match = tournament.getNextMatch();
        assertFalse(match.isPlayed());
        while (tournament.getNextMatch() != null) {
            Score testScore = new Score();
            testScore.setPoints(1, 0);
            tournament.finishMatch(tournament.getNextMatch(), testScore);
        }
        assertEquals(null, tournament.getNextMatch());
    }

    @Test(expected = Exception.class)
    public void testGetNextMatchException() throws Exception {
        Match match = tournament.getNextMatch();
        assertFalse(match.isPlayed());
        while (tournament.getNextMatch() != null) {
            Score testScore = new Score();
            testScore.setPoints(1, 0);
            tournament.finishMatch(tournament.getNextMatch(), testScore);
        }
        assertEquals(null, tournament.getNextMatch());
    }

    @Test
    public void testClone() throws Exception {
        assertEquals(tournament.clone().getName(),tournament.getName());
    }
}
