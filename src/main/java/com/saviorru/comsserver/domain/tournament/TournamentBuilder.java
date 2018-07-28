package com.saviorru.comsserver.domain.tournament;

import com.saviorru.comsserver.domain.TimeSettings;
import com.saviorru.comsserver.domain.dispatcher.LocationService;
import com.saviorru.comsserver.domain.dispatcher.PlayerService;
import com.saviorru.comsserver.domain.schedule.Schedule;
import com.saviorru.comsserver.domain.schedule.ScheduleImpl;

public class TournamentBuilder {

    private PlayerService playerService;
    private LocationService locationService;
    private Schedule schedule;
    private TournamentSettings tournamentSettings;
    private TimeSettings timeSettings;

    public TournamentBuilder() {
    }

    public PlayerService getPlayerService() {
        return playerService;
    }

    public Tournament build(){
        return new TennisTournament(playerService, locationService,tournamentSettings,schedule);
    }

    public TournamentBuilder clearBuilder(){
        return new TournamentBuilder();
    }

    public TournamentBuilder setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
        return this;
    }

    public LocationService getLocationService() {
        return locationService;
    }

    public TournamentBuilder setLocationService(LocationService locationService) {
        this.locationService = locationService;
        return this;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public TournamentBuilder setSchedule(Schedule schedule) {
        this.schedule = schedule;
        return this;
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

}
