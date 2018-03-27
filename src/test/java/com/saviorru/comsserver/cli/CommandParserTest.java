package com.saviorru.comsserver.cli;

import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.*;

public class CommandParserTest {
    private CommandParser testSubject;


    @Before
    public void initTest() throws Exception
    {
        testSubject = new CommandParser();
        testSubject.addParsingRule("help", new ArrayList<ArgumentType>());
        ArgumentType[] rule = {ArgumentType.ALPHA, ArgumentType.ALPHA, ArgumentType.DATE};
        testSubject.addParsingRule("addplayer", Arrays.asList(rule));
    }

    @Test()
    public void parserValidTest() throws Exception
    {
        CommandParameter commandParameter = testSubject.parse("help");
        assertEquals("help", commandParameter.getNameCommand());
    }
    @Test()
    public void parserValidComplexCommandTest() throws Exception
    {
        CommandParameter commandParameter = testSubject.parse("addplayer: Igor, Savochkin, 1978-10-01");
        assertEquals("addplayer",commandParameter.getNameCommand());
    }
    @Test(expected = Exception.class)
    public void parserInvalidTest() throws Exception
    {
        CommandParameter commandParameter = testSubject.parse("find: Igor, Savochkin, 1978-10-01");
        assertEquals("addplayer", commandParameter.getNameCommand());
    }

}
