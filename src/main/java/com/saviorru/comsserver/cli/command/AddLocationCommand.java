package com.saviorru.comsserver.cli.command;

import com.saviorru.comsserver.domain.dispatcher.LocationService;
import com.saviorru.comsserver.domain.model.Location;

public class AddLocationCommand implements Command {

    private LocationService locationService;
    private Location location;

    public AddLocationCommand(Location location) {
       this.location = location;
    }

    @Override
    public Boolean execute(){
        locationService.addLocation(location);
        return true;
    }

}
