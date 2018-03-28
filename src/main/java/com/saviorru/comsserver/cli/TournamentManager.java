package com.saviorru.comsserver.cli;

import com.saviorru.comsserver.domain.tournament.Tournament;

import java.util.List;

public class TournamentManager {

    private List<Tournament> tournaments;

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public boolean addTournament(Tournament tournament) {
        if(tournament == null) throw new NullPointerException("При добавлении отсутствовал турнир");
        tournaments.add(tournament);
        return true;
    }

    public Tournament getTournament(int index) {
        if (index < 0 || index > tournaments.size()) throw new IllegalArgumentException("Такого турнира не существует");
        return tournaments.get(index);
    }

    public Tournament getTournament(String nameTournament) {
        if (nameTournament == null) throw new NullPointerException("Требуется указать имя турнира");
        if (nameTournament.isEmpty()) throw new IllegalArgumentException("Имя должно содеражть хотя бы 1 символ");
        for (Tournament tournament : tournaments) {
            if (nameTournament.equals(tournament.getName())) return tournament;
        }
        return null;
    }
}
