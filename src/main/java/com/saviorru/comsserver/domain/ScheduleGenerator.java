package com.saviorru.comsserver.domain;

import java.util.List;

public interface ScheduleGenerator {

    Schedule generateSchedule() throws Exception;

    Schedule updateSchedule(List<Match> matchesList, Schedule existingSchedule) throws Exception;
    Schedule updateSchedule(Match match, Schedule existingSchedule) throws Exception;

}