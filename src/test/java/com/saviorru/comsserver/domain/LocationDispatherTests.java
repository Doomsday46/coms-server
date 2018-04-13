package com.saviorru.comsserver.domain;

import com.saviorru.comsserver.domain.model.Location;
import com.saviorru.comsserver.domain.dispatcher.LocationService;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static junit.framework.TestCase.*;

public class LocationDispatherTests {
    private LocationService testSubject;


    @Before
    public void testInit()
    {
        testSubject = new LocationService();
    }


    @Test()
    public void dispatherAddTest()
    {
        Location testLockMock = mock(Location.class);
        try {
            testSubject.addLocation(testLockMock);
            assertEquals(1, testSubject.getAllLocations().size());
        }
        catch (Exception e) {}
    }
    @Test(expected = Exception.class)
    public void dispatherAddExceptionTest() throws Exception
    {
        Location testLockMock = mock(Location.class);
        testSubject.addLocation(testLockMock);
        testSubject.addLocation(testLockMock);
    }
    @Test(expected = Exception.class)
    public void dispatherAddExceptionByPlaceTest() throws Exception
    {
        Location testLoc1 = new Location("1", "");
        Location testLoc2 = new Location("1", "");
        testSubject.addLocation(testLoc1);
        testSubject.addLocation(testLoc2);
    }
    @Test
    public void dispatherRemoveTest() throws Exception
    {
        Location testMock = mock(Location.class);
        testSubject.addLocation(testMock);
        testSubject.addLocation(mock(Location.class));
        testSubject.removeLocation(testMock);
        assertEquals(1, testSubject.getAllLocations().size());
    }
    @Test(expected = Exception.class)
    public void dispatherRemoveExceptionTest() throws Exception
    {
        testSubject.addLocation(mock(Location.class));
        Location mock = mock(Location.class);
        testSubject.removeLocation(mock);
    }
    @Test()
    public void dispatherRemoveByPlaceTest() throws  Exception
    {
        String testString = "1";
        Location testLoc = new Location(testString, "");
        Location testLocMock1 = mock(Location.class);
        Location testLocMock2 = mock(Location.class);
        when(testLocMock1.getPlace()).thenReturn("2");
        when(testLocMock2.getPlace()).thenReturn("3");
        testSubject.addLocation(testLoc);
        testSubject.addLocation(testLocMock1);
        testSubject.addLocation(testLocMock2);
        testSubject.removeLocationByPlace(testString);
        assertEquals(2, testSubject.getAllLocations().size());
    }
    @Test(expected = Exception.class)
    public void dispatherRemoveByPlaceExceptionTest() throws Exception
    {
        Location testLocMock1 = mock(Location.class);
        Location testLocMock2 = mock(Location.class);
        when(testLocMock1.getPlace()).thenReturn("2");
        when(testLocMock2.getPlace()).thenReturn("3");
        testSubject.addLocation(testLocMock1);
        testSubject.addLocation(testLocMock2);
        testSubject.removeLocationByPlace("1");
    }
    @Test()
    public void dispatherGetFreeTest() throws Exception
    {
        Location testLocMock1 = mock(Location.class);
        Location testLocMock2 = mock(Location.class);
        when(testLocMock1.isBusy()).thenReturn(false);
        when(testLocMock2.isBusy()).thenReturn(true);
        testSubject.addLocation(testLocMock1);
        testSubject.addLocation(testLocMock2);
        assertFalse(testSubject.getFreeLocation().isBusy());

    }
    @Test()
    public void dispatherGetNoFreeTest() throws Exception
    {
        Location testLocMock1 = mock(Location.class);
        Location testLocMock2 = mock(Location.class);
        when(testLocMock1.isBusy()).thenReturn(true);
        when(testLocMock2.isBusy()).thenReturn(true);
        testSubject.addLocation(testLocMock1);
        testSubject.addLocation(testLocMock2);
        assertEquals(null, testSubject.getFreeLocation());
    }
    @Test()
    public void dispatherGetAllFreeTest() throws Exception
    {
        Location testLocMock1 = mock(Location.class);
        Location testLocMock2 = mock(Location.class);
        when(testLocMock1.isBusy()).thenReturn(false);
        when(testLocMock2.isBusy()).thenReturn(false);
        testSubject.addLocation(testLocMock1);
        testSubject.addLocation(testLocMock2);
        assertEquals(2, testSubject.getAllFreeLocations().size());
    }
    @Test()
    public void dispatherReserveTest() throws Exception
    {
        Location testLocMock1 = new Location("1", "");
        Location testLocMock2 = new Location("2", "");
        testSubject.addLocation(testLocMock1);
        testSubject.addLocation(testLocMock2);
        testSubject.reserveLocation(testLocMock1);
        testSubject.reserveLocation(testLocMock2);
        assertEquals(0, testSubject.getAllFreeLocations().size());
    }
    @Test(expected = Exception.class)
    public void dispatherReserveExceptionTest() throws Exception
    {
        Location testLocMock1 = new Location("1", "");
        Location testLocMock2 = new Location("2", "");
        testSubject.addLocation(testLocMock1);
        testSubject.addLocation(testLocMock2);
        testSubject.reserveLocation(testLocMock1);
        testSubject.reserveLocation(testLocMock1);
    }
    @Test()
    public void dispatherReserveByPlaceTest() throws Exception
    {
        Location testLocMock1 = new Location("1", "");
        Location testLocMock2 = new Location("2", "");
        testSubject.addLocation(testLocMock1);
        testSubject.addLocation(testLocMock2);
        testSubject.reserveLocationByPlace("1");
        testSubject.reserveLocationByPlace("2");
        assertEquals(0, testSubject.getAllFreeLocations().size());
    }
    @Test(expected = Exception.class)
    public void dispatherReserveByPlaceExcTest() throws Exception
    {
        Location testLocMock1 = new Location("1", "");
        Location testLocMock2 = new Location("2", "");
        testSubject.addLocation(testLocMock1);
        testSubject.addLocation(testLocMock2);
        testSubject.reserveLocationByPlace("1");
        testSubject.reserveLocationByPlace("1");
        assertEquals(0, testSubject.getAllFreeLocations().size());
    }
    @Test()
    public void dispatherFreeLocTest() throws Exception
    {
        Location testLocMock1 = new Location("1", "");
        Location testLocMock2 = new Location("2", "");
        testSubject.addLocation(testLocMock1);
        testSubject.addLocation(testLocMock2);
        testSubject.reserveLocationByPlace("1");
        testSubject.reserveLocationByPlace("2");
        testSubject.freeLocation(testLocMock1);
        assertEquals(1, testSubject.getAllFreeLocations().size());
    }
    @Test(expected = Exception.class)
    public void dispatherFreeLocExcTest() throws Exception
    {
        Location testLocMock1 = new Location("1", "");
        Location testLocMock2 = new Location("2", "");
        testSubject.addLocation(testLocMock1);
        //testSubject.addLocation(testLocMock2);
        testSubject.reserveLocationByPlace("1");
        testSubject.reserveLocationByPlace("2");
        testSubject.freeLocation(testLocMock2);
        //assertEquals(1, testSubject.getAllFreeLocations().size());
    }
    @Test()
    public void dispatherFreeLocByPlaceTest() throws Exception
    {
        Location testLocMock1 = new Location("1", "");
        Location testLocMock2 = new Location("2", "");
        testSubject.addLocation(testLocMock1);
        testSubject.addLocation(testLocMock2);
        testSubject.reserveLocationByPlace("1");
        testSubject.reserveLocationByPlace("2");
        testSubject.freeLocationByPlace("1");
        assertEquals(1, testSubject.getAllFreeLocations().size());
    }
    @Test(expected = Exception.class)
    public void dispatherFreeLocByPlaceExcTest() throws Exception
    {
        Location testLocMock1 = new Location("1", "");
        Location testLocMock2 = new Location("2", "");
        testSubject.addLocation(testLocMock1);
        //testSubject.addLocation(testLocMock2);
        testSubject.reserveLocationByPlace("1");
        //testSubject.reserveLocationByPlace("2");
        testSubject.freeLocationByPlace("2");
        //assertEquals(1, testSubject.getAllFreeLocations().size());
    }
}
