package com.saviorru.comsserver.domain;
import com.saviorru.comsserver.domain.dispatcher.DateService;
import org.junit.Test;

import java.time.LocalDateTime;

import static junit.framework.TestCase.*;

public class DateDispatherTests {
    private DateService testSubject;

    @Test(expected = Exception.class)
    public void dispatherCreateExcTest3() throws Exception
    {
        testSubject = new DateService(null, null);
    }
    @Test()
    public void dispatherGetDateTest1() throws Exception
    {
        testSubject = new DateService(LocalDateTime.now(), new TimeSettings(10, 18, 12));
        System.out.print(LocalDateTime.now());
        System.out.print('\n');
        System.out.print(testSubject.getNextDate());
        assertEquals(true, testSubject.getNextDate().isAfter(LocalDateTime.now()));
    }
}
