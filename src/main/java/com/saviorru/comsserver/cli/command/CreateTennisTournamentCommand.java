package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.domain.dispatcher.DateService;
import com.saviorru.comsserver.domain.dispatcher.LocationService;
import com.saviorru.comsserver.domain.dispatcher.PlayerService;
import com.saviorru.comsserver.domain.schedule.Schedule;
import com.saviorru.comsserver.domain.schedule.ScheduleImpl;
import com.saviorru.comsserver.domain.tournament.TournamentBuilder;
import com.saviorru.comsserver.domain.tournament.TournamentManager;

public class CreateTennisTournamentCommand implements Command {

    private TournamentBuilder tournamentBuilder;
    private TournamentManager tournamentManager;
    private LocationService locationService;
    private PlayerService playerService;
    private DateService dateService;
    private Schedule schedule;

    public CreateTennisTournamentCommand() {
        this.tournamentBuilder = new TournamentBuilder();
        this.tournamentManager = new TournamentManager();
        this.locationService = new LocationService();
        this.playerService = new PlayerService();
        this.dateService = new DateService();
        this.schedule = new ScheduleImpl();
    }

    @Override
    public Boolean execute(){
        tournamentBuilder.setLocationService(locationService).setPlayerService(playerService)

        tournamentManager.addTournament(tournamentBuilder.setLocationService().build());
        this.tournamentBuilder = this.tournamentBuilder.clearBuilder();
        return true;
    }

}
