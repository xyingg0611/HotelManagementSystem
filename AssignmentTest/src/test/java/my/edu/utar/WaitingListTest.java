package my.edu.utar;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class WaitingListTest {

    private WaitingList waitingList;

    @Mock
    private User user;

    @Before
    public void setUp() {
        // Initialize the Mockito annotations
        MockitoAnnotations.openMocks(this);
        // Create a new WaitingList instance for each test
        waitingList = new WaitingList();
    }

    // Test adding a user to the VIP waiting list
    @Test
    public void testAddWaitingVIP() {
        when(user.getMemberType()).thenReturn("VIP");
        waitingList.addWaiting(user);
        assertEquals(1, waitingList.getVipWaitingList().size());
    }
    
    // Test adding a user to the member waiting list
    @Test
    public void testAddWaitingMember() {
        when(user.getMemberType()).thenReturn("NORMAL");
        waitingList.addWaiting(user);
        assertEquals(1, waitingList.getMemberWaitingList().size());
    }
    
    // Test adding a user to the non-member waiting list
    @Test
    public void testAddWaitingNonMember() {
        when(user.getMemberType()).thenReturn("NON_MEMBER");
        waitingList.addWaiting(user);
        assertEquals(1, waitingList.getNormalWaitingList().size());
    }

    // Test getting the VIP waiting list as a string
    @Test
    public void testGetWaitingVIP() {
        User user = mock(User.class);
        when(user.getMemberType()).thenReturn("VIP");
        when(user.getName()).thenReturn("Abu");
        waitingList.addWaiting(user);
        String ER = "VIP Waiting List:\n1 . Abu\n";
        assertEquals(ER, waitingList.getWaiting(user));
    }
    
    // Test getting the member waiting list as a string
    @Test
    public void testGetWaitingMember() {
        User user = mock(User.class);
        when(user.getMemberType()).thenReturn("NORMAL");
        when(user.getName()).thenReturn("Ali");
        waitingList.addWaiting(user);
        String ER = "Member Waiting List:\n1 . Ali\n";
        assertEquals(ER, waitingList.getWaiting(user));
    }
    
    // Test getting the non-member waiting list as a string
    @Test
    public void testGetWaitingNonMember() {
        User user = mock(User.class);
        when(user.getMemberType()).thenReturn("NON_MEMBER");
        when(user.getName()).thenReturn("Siti");
        waitingList.addWaiting(user);
        String ER = "Non-member Waiting List:\n1 . Siti\n";
        assertEquals(ER, waitingList.getWaiting(user));
    }

    // Test removing a user from the VIP waiting list
    @Test
    public void testRemoveWaitingVIP() {
        when(user.getMemberType()).thenReturn("VIP");
        waitingList.addWaiting(user);
        waitingList.removeWaiting(user);
        assertEquals(0, waitingList.getVipWaitingList().size());
    }
    
    // Test removing a user from the member waiting list
    @Test
    public void testRemoveWaitingMember() {
        when(user.getMemberType()).thenReturn("NORMAL");
        waitingList.addWaiting(user);
        waitingList.removeWaiting(user);
        assertEquals(0, waitingList.getVipWaitingList().size());
    }
    
    // Test removing a user from the non-member waiting list
    @Test
    public void testRemoveWaitingNonMember() {
        when(user.getMemberType()).thenReturn("NON_MEMBER");
        waitingList.addWaiting(user);
        waitingList.removeWaiting(user);
        assertEquals(0, waitingList.getVipWaitingList().size());
    }

}
