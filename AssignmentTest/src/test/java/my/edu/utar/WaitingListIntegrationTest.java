package my.edu.utar;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WaitingListIntegrationTest {
    User user;
    WaitingList waitingList;
    
    @Before
    public void setup() {
        user = new User("Abu","VIP",false);
        waitingList = new WaitingList();
    }
    
    // Test adding a user to the waiting list
    @Test
    public void testAddWaiting() {
        waitingList.addWaiting(user);
        assertEquals(1, waitingList.getVipWaitingList().size());
    }

    // Test getting the waiting list when it contains users
    @Test
    public void testGetWaiting() {
        waitingList.addWaiting(user);

        String ER = "VIP Waiting List:\n1 . Abu\n";
        assertEquals(ER,waitingList.getWaiting(user));
    }
    
    // Test getting the waiting list when it is empty
    @Test
    public void testGetWaiting_NoUsers() {
        String expectedOutput = "No user currently in VIP waiting list.";
        assertEquals(expectedOutput, waitingList.getWaiting(user));
    }
    
    // Test removing a user from the waiting list
    @Test
    public void testRemoveWaiting() {
        waitingList.addWaiting(user);
        assertEquals(1, waitingList.getVipWaitingList().size());

        waitingList.removeWaiting(user);
        assertEquals(0, waitingList.getVipWaitingList().size());
    }

}
