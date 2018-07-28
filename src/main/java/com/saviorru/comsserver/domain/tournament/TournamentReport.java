package com.saviorru.comsserver.domain.tournament;

import com.saviorru.comsserver.localization.SingletonResourceBundle;
import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.model.Player;
import com.saviorru.comsserver.domain.schematictype.SchemeType;
import javafx.util.Pair;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private TournamentSettings tournamentSettings;
    private Tournament tournament;


    public TournamentReport(Tournament tournament) {
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
        this.tournament = tournament;
        this.playerScoresTable = this.calcScores(this.contestersList, this.matchesHistory);
        fillPrizePlace();

    }

    private void fillPrizePlace() {
        for (int i = 0; i < tournament.getTournamentSettings().getPrizePlacesCount(); i++) {
            this.prizeWinners.add(tournament.getThePrizePlace(i + 1));
        }
    }

    private List<Pair<Player, Integer>> calcScores(List<Player> contestersList, List<Match> matchesHistory) {
        Map<Player, Integer> playerScores = new HashMap<>();
        for (Player player : contestersList) {
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
        Stream<Map.Entry<Player, Integer>> st = playerScores.entrySet().stream();

        st.sorted(Comparator.comparing(e -> e.getValue()))
                .forEach(e -> result.add(new Pair<Player, Integer>(e.getKey(), e.getValue())));
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
        result.append(SingletonResourceBundle.getResourceBundle().getString("dateRC")).append(": ").append(reportDate.format(DateTimeFormatter.ofPattern("dd.LL.yyyy HH:mm"))).append("\n");
        result.append(SingletonResourceBundle.getResourceBundle().getString("nameTournament")).append(": ").append(getTournamentName()).append("\n");
        result.append(SingletonResourceBundle.getResourceBundle().getString("dateSt")).append(": ").append(startDate.format(DateTimeFormatter.ofPattern("dd.LL.yyyy HH:mm"))).append("\n");
        result.append(SingletonResourceBundle.getResourceBundle().getString("dateEnd")).append(": ");
        if (endDate == null)
            result.append(SingletonResourceBundle.getResourceBundle().getString("tournamentNotFinish")).append("\n");
        else
            result.append(endDate.format(DateTimeFormatter.ofPattern("dd.LL.yyyy HH:mm"))).append("\n");
        result.append(SingletonResourceBundle.getResourceBundle().getString("tournamentSystem")).append(": ");
        switch (schemeType) {
            case ROUND:
                result.append(SingletonResourceBundle.getResourceBundle().getString("roundScheme")).append("\n");
                break;
            case OLYMPIC:
                result.append(SingletonResourceBundle.getResourceBundle().getString("olympicScheme")).append("\n");
                break;
        }
        result.append(SingletonResourceBundle.getResourceBundle().getString("prizersTournament")).append("\n");
        for (int i = 0; i < prizeWinners.size(); i++) {
            result.append(i + 1).append(". ");
            if (prizeWinners.get(i) != null)
                result.append(prizeWinners.get(i).toString());
            result.append("\n");
        }
        result.append(SingletonResourceBundle.getResourceBundle().getString("ratingPlayersTournament")).append(": ").append("\n");
        for (int i = 0; i < playerScoresTable.size(); i++) {
            result.append(+(i + 1)).append(".  ").append(playerScoresTable.get(i).getKey().toString()).append("   ").append(playerScoresTable.get(i).getValue().toString()).append("\n");
        }
        result.append("\n" + "\n");
        result.append(SingletonResourceBundle.getResourceBundle().getString("historyMatch")).append(": ").append("\n");
        for (int i = 0; i < matchesHistory.size(); i++) {
            result.append(i + 1).append(".  ").append(matchesHistory.get(i).toString());
        }
        return result.toString();
    }
}
