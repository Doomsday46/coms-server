package com.saviorru.comsserver.domain;
import com.saviorru.comsserver.domain.model.Player;
import com.saviorru.comsserver.domain.dispatcher.PlayerService;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static junit.framework.TestCase.*;

public class PlayerServiceTests {
    private PlayerService testSubject;
    private Player player1;
    private Player player2;
    private Player player3;

    @Before
    public void initTest()
    {
        testSubject = new PlayerService();
        player1 = mock(Player.class);
        player2 = mock(Player.class);
        player3 = mock(Player.class);
    }

    @Test()
    public void dispatcherAddTest() throws Exception
    {
        testSubject.addPlayer(player1);
        testSubject.addPlayer(player2);
        assertEquals(2,testSubject.getAllPlayers().size());
    }
    @Test(expected = Exception.class)
    public void dispatcherAddEcxTest() throws Exception
    {
        testSubject.addPlayer(player1);
        testSubject.addPlayer(player1);
    }
    @Test()
    public void dispatcherGetPlayerByNumberTest() throws Exception
    {
        testSubject.addPlayer(player1);
        assertEquals(player1,testSubject.getPlayerByNumber(1));
    }

}
