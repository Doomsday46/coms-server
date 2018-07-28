package com.saviorru.comsserver.domain.schematictype;

import com.saviorru.comsserver.exceptions.FoundObjectException;
import javafx.util.Pair;

import java.util.*;

public class RoundScheme implements Scheme
{

    private List<List<Meet>> toursList;
    private Integer playersCount;

    public RoundScheme(Integer playersCount)
    {
        if (playersCount == null) throw new NullPointerException();
        this.toursList = this.buildScheme(playersCount);
        this.playersCount = playersCount;
    }

    private void assignMeet(Integer firstNumber, Integer secondNumber){
        for (List<Meet> tour: this.toursList)
        {
            for (Meet meet: tour) {
                Set<Integer> meetSet = new HashSet<>();
                meetSet.add(meet.getFirstNumber());
                meetSet.add(meet.getSecondNumber());
                Set<Integer> paramSet = new HashSet<>();
                paramSet.add(firstNumber);
                paramSet.add(secondNumber);
                if (meetSet.equals(paramSet)) {
                    meet.assign();
                    return;
                }
            }

        }
        throw new FoundObjectException("Cant find specified pair");

    }
    private List<List<Meet>> buildScheme(Integer playersCount)
    {
        List<List<Meet>> toursList = new ArrayList<>();
        for (int i = 0; i < playersCount - 1; i++) {
            ArrayList<Meet> tour = new ArrayList<>();
            for (int j = i; j < playersCount; j++) {
                if (i == j) continue;
                Meet meet = new Meet(i+1, j+1);
                tour.add(meet);
            }
            toursList.add(tour);
        }
        return toursList;
    }

    @Override
    public List<Pair<Integer, Integer>> getAllPairs() {
        List<Pair<Integer, Integer>> pairList = new ArrayList<>();
        for (List<Meet> tour: this.toursList)
        {
            for (Meet meet: tour) {

                pairList.add(new Pair<>(meet.firstNumber, meet.secondNumber));
                }
            }
        return pairList;
    }

    @Override
    public Pair<Integer, Integer> getNextNotPlayedPair(){
        for (List<Meet> tour: this.toursList)
        {
            for (Meet meet: tour) {
                if (!(meet.isAssigned())){
                    meet.assign();
                    return new Pair<>(meet.firstNumber, meet.secondNumber);
                }
            }
        }
        return null;
    }

    @Override
    public List<Pair<Integer, Integer>> getAllPairsInTour(Integer tourNumber){
        if (tourNumber == null) throw new NullPointerException();
        if (tourNumber > this.toursList.size()-1 || tourNumber < 0) throw new ArrayIndexOutOfBoundsException();
        List<Pair<Integer, Integer>>  tour = new ArrayList<>();
        for (Meet meet: this.toursList.get(tourNumber))
        {
            tour.add(new Pair<>(meet.firstNumber, meet.secondNumber));
        }
        return tour;
    }

    @Override
    public Integer getMaxPairCount() {

        return (playersCount*(playersCount-1))/2;
    }

    @Override
    public Integer getToursCount() {
        return this.toursList.size();
    }

    @Override
    public void updateScheme(List<Integer> winnersList) {

    }

    @Override
    public PlayerGrid getPlayerGrid(){
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(collectNumbers());
        matrix.get(0).add(0, -1);
        for (int i =1; i < playersCount+1; i++)
        {
            matrix.add(new ArrayList<>());
            matrix.get(i).add(0, matrix.get(0).get(i));
            for (int j =1; j < playersCount+1; j++) {
                if (i == j) {
                    matrix.get(i).add(-1);
                    continue;
                }
                if (checkMeet(matrix.get(i).get(0), matrix.get(0).get(j)))
                    matrix.get(i).add(1);
                else
                    matrix.get(i).add(0);
            }
        }
        PlayerGrid grid = new RoundGrid(matrix);

        return grid;
    }

    private List<Integer> collectNumbers()
    {
        Set<Integer> numbers = new HashSet<Integer>();
        for (List<Meet> tour: toursList)
        {
            for (Meet meet: tour)
            {
                numbers.add(meet.getFirstNumber());
                numbers.add(meet.getSecondNumber());
            }
        }
        List<Integer> result = new ArrayList<>(numbers);
        Collections.sort(result);
        return result;
    }

    private Boolean checkMeet(Integer firstNumber, Integer secondNumber)
    {
        Meet compareMeet = new Meet(firstNumber, secondNumber);
        for (List<Meet> tour: toursList)
        {
            for (Meet meet: tour)
            {
                if (meet.equals(compareMeet))
                {compareMeet = meet;
                    break;}
            }
        }
        return compareMeet.isAssigned();
    }

    @Override
    public String toString() {
        return "Круговая система";
    }


    private class Meet {
        private Integer firstNumber;
        private Integer secondNumber;
        private Boolean assigned;

        public Boolean isAssigned() {
            return assigned;
        }

        public Meet(Integer firstNumber, Integer secondNumber){
            if ((firstNumber == null) || (secondNumber == null))
                throw new NullPointerException();
            if (firstNumber.equals(secondNumber))
                throw new DuplicateFormatFlagsException("Duplicate numbers in one meet is not allowed");
            this.firstNumber = firstNumber;
            this.secondNumber = secondNumber;
            this.assigned = false;
        }

        public Integer getFirstNumber() {
            return this.firstNumber;
        }

        public Integer getSecondNumber() {
            return this.secondNumber;
        }

        public void assign()  {
            if (this.isAssigned()) return;
            this.assigned = true;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Meet meet = (Meet) o;

            Set<Integer> firstSet = new HashSet<>();
            firstSet.add(this.getFirstNumber());
            firstSet.add(this.getSecondNumber());
            Set<Integer> secondSet = new HashSet<>();
            secondSet.add(meet.getFirstNumber());
            secondSet.add(meet.getSecondNumber());
            return firstSet.equals(secondSet);
        }
    }
}

