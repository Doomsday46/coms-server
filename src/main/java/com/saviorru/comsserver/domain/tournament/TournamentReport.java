package com.saviorru.comsserver.domain.tournament;

import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.schematictype.SchemeType;
import com.saviorru.comsserver.domain.model.Player;
import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

public class TournamentReport {
    private List<Player> contestersList;
    private List<Pair<Player, Integer>> playerScoresTable;
    private List<Match> matchesHistory;
    private SchemeType schemeType;
    private List<Player> prizeWinners;
    private LocalDateTime startDate, endDate, reportDate;
    private String tournamentName;


    public TournamentReport(Tournament tournament) throws Exception {
        if (tournament == null) throw new NullPointerException();
        this.contestersList = tournament.getPlayers();
        if (tournament.isStart())
            this.matchesHistory = tournament.getSchedule().getAllMatches();
        else
            this.matchesHistory = new ArrayList<>();
        this.schemeType = tournament.getSchemeType();
        this.startDate = tournament.getStartDate();
        this.endDate = tournament.getEndDate();
        this.reportDate = LocalDateTime.now();
        this.tournamentName = tournament.getName();
        this.prizeWinners = new ArrayList<>();
        this.prizeWinners.add(tournament.getThePrizePlace(1));
        this.prizeWinners.add(tournament.getThePrizePlace(2));
        this.prizeWinners.add(tournament.getThePrizePlace(3));
        this.playerScoresTable = this.calcScores(this.contestersList, this.matchesHistory);

    }



    private List<Pair<Player, Integer>> calcScores( List<Player> contestersList, List<Match> matchesHistory) throws Exception
    {
        Map<Player, Integer> playerScores = new HashMap<>();
        for (Player player : contestersList)
        {
                playerScores.put(player, 0);
        }
        for (Match match : matchesHistory) {
            if (match.isPlayed()) {
                Player winner = match.getWinner();
                Integer score = playerScores.get(winner);
                score += 1;
                playerScores.replace(winner, score);
            }
        }
        List<Pair<Player, Integer>> result = new ArrayList<>();
        Stream<Map.Entry<Player,Integer>> st = playerScores.entrySet().stream();

        st.sorted(Comparator.comparing(e -> e.getValue()))
                .forEach(e ->result.add(new Pair<Player, Integer> (e.getKey(), e.getValue())));
        Collections.reverse(result);
        return result;
    }

    public List<Player> getContestersList() {
        return contestersList;
    }

    public List<Pair<Player, Integer>> getPlayerScoresTable() {
        return playerScoresTable;
    }

    public List<Match> getMatchesHistory() {
        return matchesHistory;
    }

    public SchemeType getSchemeType() {
        return schemeType;
    }

    public List<Player> getPrizeWinners() {
        return prizeWinners;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append( "Дата составления отчета: " + reportDate.toString() + "\n");
        result.append( "Название турнира: " + getTournamentName() + "\n");
        result.append( "Дата начала: " + startDate.toString() + "\n");
        result.append( "Дата окончания: ");
        if (endDate == null)
            result.append( "турнир еще не окончен" + "\n");
        else
            result.append( endDate.toString() + "\n");
        result.append( "Турнирная система: ");
        switch (schemeType)
        {
            case ROUND: result.append( "круговая" + "\n"); break;
            case OLYMPIC: result.append( "олимпийская" + "\n"); break;
        }
        result.append( "Призеры турнира:" + "\n");
        for (int i =0; i<prizeWinners.size(); i++) {
            result.append( (i+1) + ". ");
            if (prizeWinners.get(i) != null)
                result.append( prizeWinners.get(i).toString());
            result.append( "\n");
        }
        result.append( "Рейтинг участников в турнире: " + "\n");
        for (int i =0; i < playerScoresTable.size(); i++)
        {
             result.append( + (i+1) + ".  " + playerScoresTable.get(i).getKey().toString() +  "   " +
                    playerScoresTable.get(i).getValue().toString() + "\n");
        }
        result.append(  "\n" + "\n");
        result.append( "История матчей: " + "\n");
        for (int i=0; i < matchesHistory.size(); i++)
        {
            result.append((i+1) + ".  " + matchesHistory.get(i).toString());
        }
        return result.toString();
    }
}
