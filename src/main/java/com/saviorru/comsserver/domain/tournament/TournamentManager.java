package com.saviorru.comsserver.domain.tournament;

import java.util.ArrayList;
import java.util.List;

public class TournamentManager {

    private List<Tournament> tournaments;
    private Tournament activeTournament;
    private Integer sizeTournaments, oldSize;

    public TournamentManager(){
        tournaments = new ArrayList<>();
        oldSize = 0;
        sizeTournaments = 0;
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public boolean addTournament(Tournament tournament) {
        if(tournament == null) throw new NullPointerException("При добавлении отсутствовал турнир");
        tournaments.add(tournament);
        activeTournament = tournaments.get(tournaments.size()-1);
        sizeTournaments = tournaments.size();
        return true;
    }

    public Tournament getTournament(int index) {
        if (index < 0 || index > tournaments.size()) throw new IllegalArgumentException("Такого турнира не существует");
        return tournaments.get(index);
    }

    public boolean setActiveTournament(int index) {
        if (index < 0 || index > tournaments.size()) throw new IllegalArgumentException("Такого турнира не существует");
        activeTournament = tournaments.get(index);
        return true;
    }
    public boolean setActiveTournament(String nameTournament) {
        if (nameTournament == null) throw new NullPointerException("Требуется указать имя турнира");
        if (nameTournament.isEmpty()) throw new IllegalArgumentException("Имя должно содеражть хотя бы 1 символ");
        for (Tournament tournament : tournaments) {
            if (nameTournament.equals(tournament.getName())) activeTournament = tournament;
        }
        return true;
    }

    public Tournament getTournament(String nameTournament) {
        if (nameTournament == null) throw new NullPointerException("Требуется указать имя турнира");
        if (nameTournament.isEmpty()) throw new IllegalArgumentException("Имя должно содеражть хотя бы 1 символ");
        for (Tournament tournament : tournaments) {
            if (nameTournament.equals(tournament.getName())) return tournament;
        }
        return null;
    }

    public Tournament getActiveTournament() {
        return activeTournament;
    }

    public boolean isAddTournament(){
        if(!oldSize.equals(sizeTournaments)){
            oldSize = sizeTournaments;
            return true;
        }
        return false;
    }
}
