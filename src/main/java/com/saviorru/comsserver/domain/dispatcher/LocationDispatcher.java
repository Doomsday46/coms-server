package com.saviorru.comsserver.domain.dispatcher;

import com.saviorru.comsserver.domain.model.Location;
import com.saviorru.comsserver.exceptions.FoundObjectException;

import java.util.ArrayList;
import java.util.List;

public class LocationDispatcher {
    private List<Location> locationsList;


    public LocationDispatcher() {
        this.locationsList = new ArrayList<Location>();
    }

    public List<Location> getAllLocations() {
        return locationsList;
    }

    public void addLocation(Location newLocation) {
        if (newLocation == null) throw new NullPointerException("Null argument");
        if (this.locationsList.contains(newLocation)) {
            throw new FoundObjectException("Duplicate locations is not allowed");
        } else {
            this.locationsList.add(newLocation);
        }
    }

    public void addAllLocation(List<Location> Locations) {
        if (Locations == null) throw new NullPointerException("Null argument");
        if (this.locationsList.containsAll(Locations)) {
            throw new FoundObjectException("Duplicate locations is not allowed");
        } else {
            this.locationsList.addAll(Locations);
        }
    }

    public void removeLocation(Location existingLocation) {
        if (existingLocation == null) throw new NullPointerException("Null argument");
        if (this.locationsList.contains(existingLocation)) {
            this.locationsList.remove(existingLocation);
        } else {
            throw new FoundObjectException("Location doesn't exist in dispather");
        }
    }

    public void removeLocationByPlace(String locationPlace) {
        if (locationPlace == null) throw new NullPointerException("Null argument");
        for (Location location : this.locationsList) {
            if (location.getPlace().equals(locationPlace)) {
                this.locationsList.remove(location);
                return;
            }
        }
        throw new FoundObjectException("Location with specified place doesn't exist in dispather");
    }

    public Location getFreeLocation() {
        for (Location location : this.locationsList) {
            if (!(location.isBusy())) {
                return location;
            }
        }
        return null;
    }

    public List<Location> getAllFreeLocations() {
        List<Location> freeLocationsList = new ArrayList<Location>();
        for (Location location : this.locationsList) {
            if (!(location.isBusy())) {
                freeLocationsList.add(location);
            }
        }
        return freeLocationsList;
    }

    public void reserveLocation(Location location) {
        if (location == null) throw new NullPointerException("Null argument");
        if (this.locationsList.contains(location)) {
            if (!(location.isBusy())) {
                location.setBusy(true);
            } else {
                throw new FoundObjectException("Location is busy");
            }
        } else {
            throw new FoundObjectException("Location doesn't exist in dispather");
        }
    }

    public void reserveLocationByPlace(String locationPlace) {
        if (locationPlace == null) throw new NullPointerException("Null argument");
        for (Location location : this.locationsList) {
            if (location.getPlace().equals(locationPlace)) {
                if (!(location.isBusy())) {
                    location.setBusy(true);
                    return;
                } else {
                    throw new FoundObjectException("Location is busy");
                }
            }

        }
        throw new FoundObjectException("Location doesn't exist in dispather");
    }

    public void freeLocation(Location location) {
        if (location == null) throw new NullPointerException("Null argument");
        if (this.locationsList.contains(location)) {
            location.setBusy(false);

        } else {
            throw new FoundObjectException("Location doesn't exist in dispather");
        }
    }

    public void freeLocationByPlace(String locationPlace) {
        if (locationPlace == null) throw new NullPointerException("Null argument");
        for (Location location : this.locationsList) {
            if (location.getPlace().equals(locationPlace)) {
                location.setBusy(false);
                return;
            }
        }
        throw new FoundObjectException("Location doesn't exist in dispather");
    }
}
