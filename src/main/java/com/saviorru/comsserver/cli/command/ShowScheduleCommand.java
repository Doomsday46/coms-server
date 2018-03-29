package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.tournament.Tournament;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShowScheduleCommand implements Command {

    private Tournament tournament;

    public ShowScheduleCommand(Tournament tournament) {
        this.tournament = tournament;
    }

    @Override
    public Boolean execute(){
        showSchedule();
        return true;
    }

    private void showSchedule(){
        if(tournament == null) throw new NullPointerException("Tournament not found");
        List<Match> matches = new ArrayList<>(tournament.getSchedule().getAllMatches());
        matches.sort(new Comparator<Match>() {
            public int compare(Match o1, Match o2) {
                return (o1.getDate().isAfter(o2.getDate())) ? 1 : (o1.getDate().equals(o2.getDate())) ? 0 : -1;
            }
        });
        printSchedule(matches);
    }

    private void printSchedule(List<Match> matches){
        int count = 0;
        for (Match match : matches) {
            String isPlayedMatch = (match.isPlayed()) ? " сыгран" : " не сыгран";
            count++;
            if (!match.isPlayed()) {
                System.out.println("Номер матча: " + count);
                System.out.println("Игрок 1: " + match.getFirstSide());
                System.out.println("Игрок 2: " + match.getSecondSide());
                System.out.println("Дата матча: " + match.getDate().toString());
                System.out.println("Статус матча: " + isPlayedMatch);
            } else {

                System.out.println("Номер матча: " + count);
                System.out.println("Игрок 1: " + match.getFirstSide());
                System.out.println("Игрок 2: " + match.getSecondSide());
                System.out.println("Дата матча: " + match.getDate().toString());
                System.out.println("Статус матча: " + isPlayedMatch);
                System.out.println("Очки первого игрока: " + match.getPointsFirstSide());
                System.out.println("Очки второго игрока: " + match.getPointsSecondSide());
                System.out.println("Победитель: " + match.getWinner());
            }
        }
    }
}
