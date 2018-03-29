package com.saviorru.comsserver.domain.tournament;

import com.saviorru.comsserver.domain.TimeSettings;
import com.saviorru.comsserver.domain.dispatcher.LocationDispatcher;
import com.saviorru.comsserver.domain.dispatcher.PlayerDispatcher;
import com.saviorru.comsserver.domain.schedule.Schedule;
import com.saviorru.comsserver.domain.schedule.ScheduleImpl;

public class TournamentBuilder {

    private PlayerDispatcher playerDispatcher;
    private LocationDispatcher locationDispatcher;
    private Schedule schedule;
    private TournamentSettings tournamentSettings;
    private TimeSettings timeSettings;
    private Tournament tournament;

    public TournamentBuilder() {
        playerDispatcher = new PlayerDispatcher();
        locationDispatcher = new LocationDispatcher();
        schedule = new ScheduleImpl();
    }

    public PlayerDispatcher getPlayerDispatcher() {
        return playerDispatcher;
    }

    public TournamentBuilder setPlayer(PlayerDispatcher playerDispatcher) {
        this.playerDispatcher = playerDispatcher;
        return this;
    }

    public LocationDispatcher getLocationDispatcher() {
        return locationDispatcher;
    }

    public TournamentBuilder setLocationDispatcher(LocationDispatcher locationDispatcher) {
        this.locationDispatcher = locationDispatcher;
        return this;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public TournamentSettings getTournamentSettings() {
        return tournamentSettings;
    }

    public TournamentBuilder setTournamentSettings(TournamentSettings tournamentSettings) {
        this.tournamentSettings = tournamentSettings;
        return this;
    }

    public TimeSettings getTimeSettings() {
        return timeSettings;
    }

    public TournamentBuilder setTimeSettings(TimeSettings timeSettings) {
        this.timeSettings = timeSettings;
        return this;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public TournamentBuilder setTournament(Tournament tournament) {
        this.tournament = tournament;
        return this;
    }

    public Tournament build(){
        try {
            this.tournament =  new TennisTournament(playerDispatcher,locationDispatcher,tournamentSettings,schedule);
            return this.tournament;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
