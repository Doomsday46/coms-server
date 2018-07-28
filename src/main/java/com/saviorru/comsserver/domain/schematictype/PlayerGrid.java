package com.saviorru.comsserver.domain.schematictype;

import javafx.util.Pair;

import java.util.List;

public interface PlayerGrid {

    List<Integer> getNumbersByTour(Integer tour);
    Integer getTourCount();
}
