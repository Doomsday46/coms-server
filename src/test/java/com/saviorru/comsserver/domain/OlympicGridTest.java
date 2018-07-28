package com.saviorru.comsserver.domain;

import com.saviorru.comsserver.domain.schematictype.OlympicGrid;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OlympicGridTest {

    private List<List<Integer>> listList;
    private final Integer COUNT_ROUND = 5;
    private final Integer COUNT_PLAYERS = 9;

    @Before
    public void init() {
        listList = new ArrayList<>();
        int countPlayers = COUNT_PLAYERS;
        for (Integer i = 0; i < COUNT_ROUND; i++) {
            listList.add(new ArrayList<>(1));
            for (Integer j = 0; j < countPlayers; j++) {
                listList.get(i).add(j+1);
            }
            countPlayers /=  2;
        }
    }

    @Test
    public void testGetTourCount_whenEightPlayers_resultFour() throws Exception {
        assertEquals(COUNT_ROUND, new OlympicGrid(listList).getTourCount());
    }

    @Test(expected = Exception.class)
    public void testGetTourCount_whenNullParam_resultException() throws Exception {
        assertEquals(COUNT_ROUND, new OlympicGrid(null).getTourCount());
    }

    @Test
    public void testGetNumbersTour_whenFirstTour_resultFirstTour() throws Exception {
        assertEquals(listList.get(0), new OlympicGrid(listList).getNumbersByTour(1));
    }

    @Test
    public void testGetNumbersTour_whenLastTour_resultLastTour() throws Exception {
        assertEquals(listList.get(3), new OlympicGrid(listList).getNumbersByTour(4));
    }

//    @Test(expected = Exception.class)
//    public void testGetNumbersTour_whenOutOfRangeRound_resultException() throws Exception {
//        new OlympicGrid(listList).getNumbersByTour(5);
//    }
//
//    @Test(expected = Exception.class)
//    public void testGetNumbersTour_whenParamNegativeValue_resultException() throws Exception {
//        new OlympicGrid(listList).getNumbersByTour(5);
//    }
//
//    @Test(expected = Exception.class)
//    public void testGetNumbersTour_whenParamZero_resultException() throws Exception {
//        new OlympicGrid(listList).getNumbersByTour(5);
//    }

    @Test
    public void testToString() throws Exception {
        System.out.print(new OlympicGrid(listList).toString());
    }

}
