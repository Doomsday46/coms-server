package com.saviorru.comsserver.domain;

import com.saviorru.comsserver.domain.model.Location;
import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.model.OneOnOneMatch;
import com.saviorru.comsserver.domain.model.Player;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;

public class OneOnOneMatchTest {

    private Player testPlayer1, testPlayer2;
    private Match match;

    @Test(expected = Exception.class)
    public void testInitMatchWithCloneSidesResultNull() throws Exception {
        new OneOnOneMatch(testPlayer1, testPlayer1, new Location("1","2"), LocalDateTime.now());
    }

    @Test(expected = NullPointerException.class)
    public void testInitMatchWithNullParametersResultNull() throws Exception {
        new OneOnOneMatch(null, null, null, null);
    }

    @Test(expected = NullPointerException.class)
    public void testInitMatchParametersResultNull() throws Exception {
        new OneOnOneMatch(null,testPlayer2, null, null);
    }

    @Test(expected = NullPointerException.class)
    public void testInitMatchParametersWithDateNullResultNull() throws Exception {
        new OneOnOneMatch(testPlayer1, testPlayer2, null, null);
    }

    @Before
    public void initialize() throws Exception {
        testPlayer1 = new Player("a", "b", LocalDate.now());
        testPlayer2 = new Player("c", "b", LocalDate.now());
        match = new OneOnOneMatch(testPlayer1, testPlayer2, new Location("1", ""), LocalDateTime.now());
    }

    @Test
    public void testGetWinnerResultFirstSidePlayers() throws Exception {
        match.setPoints(11, 10);
        match.setMatchState(MatchState.PLAYED);
        assertEquals(testPlayer1, match.getWinner());
    }

    @Test
    public void testGetWinnerResultSecondSidePlayers() throws Exception {
        match.setPoints(10, 11);
        match.setMatchState(MatchState.PLAYED);
        assertEquals(testPlayer2, match.getWinner());
    }

    @Test
    public void testSetPoints() throws Exception {
        match.setPoints(10, 11);
        assertEquals(10, match.getPointsFirstSide());
        assertEquals(11, match.getPointsSecondSide());
    }

    @Test(expected = Exception.class)
    public void testSetPointsParamNegativeNumberResultException() throws Exception {
        match.setPoints(-5, 11);
        assertEquals(0, match.getPointsFirstSide());
        assertEquals(0, match.getPointsSecondSide());
    }

    @Test(expected = Exception.class)
    public void testSetPointsWhenPlayedMatchResultExceptionAllMatchPlayed() throws Exception {
        match.setPoints(10, 11);
        match.setMatchState(MatchState.PLAYED);
        match.setPoints(8, 5);
        assertEquals(10, match.getPointsFirstSide());
        assertEquals(11, match.getPointsSecondSide());
    }

    @Test
    public void testIsPlayedResultTrue() throws Exception {
        match.setPoints(10, 11);
        match.setMatchState(MatchState.PLAYED);
        assertTrue(match.isPlayed());
    }

    @Test
    public void testIsPlayedResultFalse() throws Exception {
        match.setPoints(10, 11);
        assertFalse(match.isPlayed());
    }

    @Test
    public void testEquales(){
        try {
            Match match = new OneOnOneMatch(testPlayer1,testPlayer2,mock(Location.class),LocalDateTime.of(1975,1,1,1,1));
            Match match2 = new OneOnOneMatch(testPlayer2,testPlayer1,mock(Location.class),LocalDateTime.of(1975,1,1,1,1));
            assertTrue(match.equals(match2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
