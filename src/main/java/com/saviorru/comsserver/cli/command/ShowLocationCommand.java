package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.cli.TournamentBuilder;
import com.saviorru.comsserver.domain.dispatcher.LocationDispatcher;
import com.saviorru.comsserver.domain.model.Location;

public class ShowLocationCommand implements Command {

    private TournamentBuilder tournamentBuilder;

    public ShowLocationCommand(TournamentBuilder tournamentBuilder){
        this.tournamentBuilder = tournamentBuilder;
    }

    @Override
    public Boolean execute() throws Exception {
        int number = 1;
        for(Location location: tournamentBuilder.getLocationDispatcher().getAllLocations()){
            number++;
            System.out.println("Место проведение номер " + number + " : " +  location.getPlace()
                            + "; Описание: " + location.getDescription());
        }
        return number > 0;
    }

}
