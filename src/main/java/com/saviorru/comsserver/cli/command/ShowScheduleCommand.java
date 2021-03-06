package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.localization.SingletonResourceBundle;
import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.tournament.Tournament;

import java.time.format.DateTimeFormatter;
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
        if (tournament == null) throw new NullPointerException("Tournament not created");
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
            String isPlayedMatch = (match.isPlayed()) ? SingletonResourceBundle.getResourceBundle().getString("text.played") : SingletonResourceBundle.getResourceBundle().getString("text.notPlayed");
            count++;
            if (!match.isPlayed()) {
                System.out.println(SingletonResourceBundle.getResourceBundle().getString("numberMatch") + ": " + count);
                System.out.println(SingletonResourceBundle.getResourceBundle().getString("text.player") + " 1: " + match.getFirstSide());
                System.out.println(SingletonResourceBundle.getResourceBundle().getString("text.player") + " 2: " + match.getSecondSide());
                System.out.println(SingletonResourceBundle.getResourceBundle().getString("dateMatch") + ": " + match.getDate().format(DateTimeFormatter.ofPattern("dd.LL.yyyy HH:mm")));
                System.out.println(SingletonResourceBundle.getResourceBundle().getString("statusMatch") + ": " + isPlayedMatch);
            } else {

                System.out.println(SingletonResourceBundle.getResourceBundle().getString("numberMatch") + ": " + count);
                System.out.println(SingletonResourceBundle.getResourceBundle().getString("text.player") + " 1: " + match.getFirstSide());
                System.out.println(SingletonResourceBundle.getResourceBundle().getString("text.player") + " 2: " + match.getSecondSide());
                System.out.println(SingletonResourceBundle.getResourceBundle().getString("dateMatch") + ": " + match.getDate().format(DateTimeFormatter.ofPattern("dd.LL.yyyy HH:mm")));
                System.out.println(SingletonResourceBundle.getResourceBundle().getString("statusMatch") + ": " + isPlayedMatch);
                System.out.println(SingletonResourceBundle.getResourceBundle().getString("scoreFP") + ": " + match.getPointsFirstSide());
                System.out.println(SingletonResourceBundle.getResourceBundle().getString("scoreSP") + ": " + match.getPointsSecondSide());
                System.out.println(SingletonResourceBundle.getResourceBundle().getString("winner") + ": " + match.getWinner());
            }
        }
    }
}
