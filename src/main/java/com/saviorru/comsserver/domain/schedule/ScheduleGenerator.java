package com.saviorru.comsserver.domain.schedule;

import com.saviorru.comsserver.domain.model.Match;
import com.saviorru.comsserver.domain.schematictype.Scheme;

import java.util.List;

public interface ScheduleGenerator {

    Schedule generateSchedule();

    Schedule updateSchedule(List<Match> matchesList, Schedule existingSchedule);
    Schedule updateSchedule(Match match, Schedule existingSchedule);
    Scheme getScheme();

}
