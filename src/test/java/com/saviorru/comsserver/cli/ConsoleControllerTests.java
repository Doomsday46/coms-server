package com.saviorru.comsserver.cli;
import org.junit.Before;
import org.junit.Test;

public class ConsoleControllerTests {

    private Interpreter testSubject;

    @Before
    public void initTest() throws Exception
    {
        testSubject = new Interpreter();
    }

    @Test()
    public void ccHelpCmdTest() throws Exception {
        System.out.print(testSubject.parse("help"));
    }

    @Test()
    public void ccBackupTest() throws Exception {
        System.out.print(testSubject.parse("help"));
        System.out.print(testSubject.parse("set player: Igor, Savochkin, 1969-10-11"));
        System.out.print('1');
    }
}
