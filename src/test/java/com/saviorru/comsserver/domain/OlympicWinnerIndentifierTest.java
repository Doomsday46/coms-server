package com.saviorru.comsserver.domain;

import com.saviorru.comsserver.domain.dispatcher.DateService;
import com.saviorru.comsserver.domain.model.Location;
import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.model.OneOnOneMatch;
import com.saviorru.comsserver.domain.model.Player;
import com.saviorru.comsserver.domain.winnerindetifier.OlympicWinnerIndentifier;
import com.saviorru.comsserver.domain.winnerindetifier.WinnerIdentifier;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class OlympicWinnerIndentifierTest {
    private List<Player> playerList;
    private List<Location> locationList;
    private List<Match> matchList;
    private DateService dateService;
    private WinnerIdentifier winnerIdentifier;

    @Before
    public void init() throws Exception {
        playerList = new ArrayList<>();
        locationList = new ArrayList<>();
        matchList = new ArrayList<>();
        dateService = new DateService(LocalDateTime.now(), new TimeSettings(10, 18, 1));
        for (int i = 0; i < 8; i++) {
            playerList.add(new Player("Andrey" + i, "Momp", LocalDate.of(1950 + i, 1, 1)));
            locationList.add(new Location("Table" + i, ""));
        }
        for (int i = 0,j = 0; i < 8; i += 2,j++) {
            matchList.add(new OneOnOneMatch(playerList.get(i), playerList.get(i + 1), locationList.get(0), dateService.getNextDate().plusHours(j)));
            matchList.get(j).setPoints(10,11);
            matchList.get(j).setMatchState(MatchState.PLAYED);
        }
        winnerIdentifier = new OlympicWinnerIndentifier();
    }

    @Test(expected = NullPointerException.class)
    public void testIdentifyWinners_whenNullParameter_NullPointerException() throws Exception {
        winnerIdentifier.identifyWinners(null);
    }

    @Test(expected = Exception.class)
    public void testIdentifyWinners_whenEmptyParameter_Exception() throws Exception {
        matchList.clear();
        winnerIdentifier.identifyWinners(matchList);
    }

    @Test
    public void testIdentifyWinners() throws Exception {
        assertEquals(playerList.get(playerList.size()-1),winnerIdentifier.identifyWinners(matchList).get(0));
    }
}
