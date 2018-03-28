package com.saviorru.comsserver.domain.winnerindetifier;

import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.model.Player;

import java.util.*;
import java.util.stream.Stream;

public class RoundWinnerIdentifier implements WinnerIdentifier {


    public RoundWinnerIdentifier() {
    }

    @Override
    public List<Player> identifyWinners(List<Match> finishedMatches) {
        if (finishedMatches == null) throw new NullPointerException();
        if (finishedMatches.isEmpty()) throw new IllegalArgumentException("Matches list is empty");

        Map<Player, Integer> playerScores = new HashMap<>();
        for (Match match : finishedMatches)
        {
            if (match == null) throw new NullPointerException();
            if (!(playerScores.containsKey(match.getFirstSide())))
                playerScores.put(match.getFirstSide(), 0);
            if (!(playerScores.containsKey(match.getSecondSide())))
                playerScores.put(match.getSecondSide(), 0);
        }
        for (Match match : finishedMatches) {
            Player winner = match.getWinner();
                Integer score = playerScores.get(winner);
                score = score + 1;
                playerScores.replace(winner, score);
            }

        List<Player> sortedPlayers = new ArrayList<>();
        Map<Player, Integer> bergerMap = calcBerger(finishedMatches, playerScores);
        Stream <Map.Entry<Player,Integer>> st = bergerMap.entrySet().stream();

        st.sorted(Comparator.comparing(e -> e.getValue()))
                .forEach(e ->sortedPlayers.add(e.getKey()));
        List<Player> result = sortedPlayers.subList(sortedPlayers.size()-3, sortedPlayers.size());
        Collections.reverse(result);
        return result;
    }

    private Map<Player, Integer> calcBerger(List<Match> matchesList, Map<Player, Integer> playerScores){
        Map<Player, Integer> bergerMap = new HashMap<>();
        for (Player player : playerScores.keySet()) {
            Integer bergerCoeff = 0;
            for (Match match : matchesList) {
                if (match.getFirstSide().equals(player) || match.getSecondSide().equals(player)) {
                    if (match.getWinner().equals(player)) {
                        if (match.getFirstSide().equals(player)) {
                            bergerCoeff = bergerCoeff + playerScores.get(match.getSecondSide());
                        } else {
                            bergerCoeff = bergerCoeff + playerScores.get(match.getFirstSide());
                        }
                    } else {
                        if (match.getFirstSide().equals(player)) {
                            bergerCoeff = bergerCoeff - playerScores.get(match.getSecondSide());
                        } else {
                            bergerCoeff = bergerCoeff - playerScores.get(match.getFirstSide());
                        }
                    }
                }
            }
            bergerMap.put(player, bergerCoeff);
        }
        return bergerMap;
    }
}
