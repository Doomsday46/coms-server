package com.saviorru.comsserver.domain;

import javafx.util.Pair;

import java.util.List;

public interface PlayerGrid {

    List<Integer> getNumbersByTour(Integer tour) throws Exception;
    Integer getTourCount();
}