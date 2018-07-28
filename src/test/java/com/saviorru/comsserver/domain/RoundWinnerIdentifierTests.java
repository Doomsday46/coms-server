package com.saviorru.comsserver.domain;
import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.model.Player;
import com.saviorru.comsserver.domain.winnerindetifier.RoundWinnerIdentifier;
import junit.framework.TestCase;
import org.junit.Before;
import static org.mockito.Mockito.*;
import org.junit.Test;


import static junit.framework.TestCase.*;

import java.util.ArrayList;
import java.util.List;

public class RoundWinnerIdentifierTests {

    private RoundWinnerIdentifier testSubject;
    private List<Player> playersList;
    private List<Match> matchesList;

    @Before
    public void initTest() throws Exception
    {
        testSubject = new RoundWinnerIdentifier();
        playersList = new ArrayList<>();
        matchesList = new ArrayList<>();
       for (int i=0; i<4; i++)
       {
           playersList.add(mock(Player.class));
       }

        for (int i = 0; i < playersList.size() - 1; i++) {
            for (int j = i; j < playersList.size(); j++) {
                if (i == j) continue;
                Match match = mock(Match.class);
                when(match.getWinner()).thenReturn(playersList.get(i));
                when(match.getFirstSide()).thenReturn(playersList.get(i));
                when(match.getSecondSide()).thenReturn(playersList.get(j));
                when(match.getPointsFirstSide()).thenReturn(1);
                when(match.getPointsSecondSide()).thenReturn(0);
                matchesList.add(match);
            }
        }
    }
    @Test()
    public void identTest() throws Exception
    {
        testSubject.identifyWinners(matchesList);

        assertEquals(3, testSubject.identifyWinners(matchesList).size());
        TestCase.assertEquals(playersList.get(0), testSubject.identifyWinners(matchesList).get(0));
    }
    @Test()
    public void identNotOneTest() throws Exception
    {
        when(matchesList.get(2).getWinner()).thenReturn(playersList.get(3));
        when(matchesList.get(2).getPointsFirstSide()).thenReturn(0);
        when(matchesList.get(2).getPointsSecondSide()).thenReturn(1);
        testSubject.identifyWinners(matchesList);
        assertEquals(3, testSubject.identifyWinners(matchesList).size());
        TestCase.assertEquals(playersList.get(1), testSubject.identifyWinners(matchesList).get(1));
    }
}
