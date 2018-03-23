package com.saviorru.comsserver.domain;

import com.saviorru.comsserver.domain.tournament.TournamentSettings;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        PlayerTest.class,
        OneOnOneMatchTest.class,
        LocationTests.class,
        LocationDispatherTests.class,
        OlympicSchemeTest.class,
        ScheduleTests.class,
        ScheduleGeneratorTests.class,
        DateDispatherTests.class,
        RoundWinnerIdentifierTests.class,
        RoundSchemeTests.class,
        PlayerDispatcherTests.class,
        OlympicGridTest.class,
        TournamentSettingsTests.class,
        TournamentReporsTests.class,
        TimeSettingsTest.class,
        TournamentTests.class,
        OlympicWinnerIndentifierTest.class
})
public class TestManager {
}