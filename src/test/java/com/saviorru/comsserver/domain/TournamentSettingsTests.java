package com.saviorru.comsserver.domain;

import com.saviorru.comsserver.domain.dispatcher.DateService;
import com.saviorru.comsserver.domain.schematictype.SchemeType;
import com.saviorru.comsserver.domain.tournament.TournamentSettings;
import com.saviorru.comsserver.domain.tournament.TournamentSettingsImpl;
import com.saviorru.comsserver.domain.winnerindetifier.WinnerIdentifier;
import org.junit.Before;

import org.junit.Test;

import static junit.framework.TestCase.*;

import java.time.LocalDateTime;

public class TournamentSettingsTests {
    private TournamentSettings testSubject;
    private SchemeType schemeType;
    private TimeSettings timeSettings;



    @Before
    public void initTest() throws Exception
    {
        schemeType = SchemeType.ROUND;
        timeSettings = new TimeSettings(11, 18, 12);
        testSubject = new TournamentSettingsImpl("tournament1", schemeType, LocalDateTime.now(), timeSettings);
    }
    @Test()
    public void settingsGetDateDispatherTest() throws Exception
    {
        DateService dd = testSubject.getDateDispatcher();
        assertFalse(dd==null);
    }
    @Test()
    public void settingsGetWinnerIdentTest() throws Exception
    {
        WinnerIdentifier dd = testSubject.getWinnerIdentifier();
        assertFalse(dd==null);
    }
}
