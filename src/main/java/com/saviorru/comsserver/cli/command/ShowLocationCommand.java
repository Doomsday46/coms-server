package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.cli.TextProgram;
import com.saviorru.comsserver.domain.model.Location;
import com.saviorru.comsserver.domain.tournament.Tournament;

public class ShowLocationCommand implements Command {

    private Tournament tournament;

    public ShowLocationCommand(Tournament tournament){
        this.tournament = tournament;
    }

    @Override
    public Boolean execute(){
        int number = 1;
        for(Location location: tournament.getLocations()){
            number++;
            System.out.println(TextProgram.getResourceBundle().getString("match.location.number") + " " + number + " : " + location.getPlace()
                            + ";  " + TextProgram.getResourceBundle().getString("match.location.description")  + ": " + location.getDescription());
        }
        return number > 0;
    }

}
