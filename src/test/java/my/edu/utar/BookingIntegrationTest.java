package my.edu.utar;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(JUnitParamsRunner.class)
public class BookingIntegrationTest {


	@Test
	@Parameters({
		// all possibility with 3 room booking as VIP.
	    "juanhong, VIP, false,3,3,0,0,false,false,false,0,0,0",
	    "juanhong, VIP, false,3,2,0,0,true,false,false,1,0,0",
	    "juanhong, VIP, false,3,2,0,0,false,true,false,0,1,0",
	    "juanhong, VIP, false,3,2,0,0,false,false,true,0,0,1",
	    "juanhong, VIP, false,3,1,0,0,true,false,false,2,0,0",
	    "juanhong, VIP, false,3,1,0,0,false,true,false,0,2,0",
	    "juanhong, VIP, false,3,1,0,0,false,false,true,0,0,2",
	    "juanhong, VIP, false,3,1,0,0,true,true,false,1,1,0",
	    "juanhong, VIP, false,3,1,0,0,false,true,true,0,1,1",
	    "juanhong, VIP, false,3,1,0,0,true,false,true,1,0,1",
	    "juanhong, VIP, false,3,0,0,0,true,true,true,1,1,1",
	    "juanhong, VIP, false,3,0,0,0,true,false,false,3,0,0",
	    "juanhong, VIP, false,3,0,0,0,false,true,false,0,3,0",
	    "juanhong, VIP, false,3,0,0,0,false,false,true,0,0,3",
	    "juanhong, VIP, false,3,0,0,0,true,true,false,2,1,0",
	    "juanhong, VIP, false,3,0,0,0,true,false,true,2,0,1",
	    "juanhong, VIP, false,3,0,0,0,true,true,false,1,2,0",
	    "juanhong, VIP, false,3,0,0,0,true,false,true,1,0,2",
	    "juanhong, VIP, false,3,0,0,0,false,true,true,0,2,1",
	    "juanhong, VIP, false,3,0,0,0,false,true,true,0,1,2",
	    // all possibility with 2 room booking as VIP.
	    "juanhong, VIP, false,2,2,0,0,false,false,false,0,0,0",
	    "juanhong, VIP, false,2,1,0,0,true,false,false,1,0,0",
	    "juanhong, VIP, false,2,1,0,0,false,true,false,0,1,0",
	    "juanhong, VIP, false,2,1,0,0,false,false,true,0,0,1",
	    "juanhong, VIP, false,2,0,0,0,true,false,false,2,0,0",
	    "juanhong, VIP, false,2,0,0,0,false,true,false,0,2,0",
	    "juanhong, VIP, false,2,0,0,0,false,false,true,0,0,2",
	    "juanhong, VIP, false,2,0,0,0,true,true,false,1,1,0",
	    "juanhong, VIP, false,2,0,0,0,false,true,true,0,1,1",
	    "juanhong, VIP, false,2,0,0,0,true,false,true,1,0,1",
	    // all possibility with 1 room booking as VIP.
	    "juanhong, VIP, false,1,1,0,0,false,false,false,0,0,0",
	    "juanhong, VIP, false,1,0,0,0,true,false,false,1,0,0",
	    "juanhong, VIP, false,1,0,0,0,false,true,false,0,1,0",
	    "juanhong, VIP, false,1,0,0,0,false,false,true,0,0,1",
	    // all possibility with 2 room booking as NORMAL.
	    "janice,NORMAL, false,2,0,2,0,false,false,false,0,0,0",
	    "janice,NORMAL, false,2,0,1,0,false,true,false,0,1,0",
	    "janice,NORMAL, false,2,0,1,0,false,false,true,0,0,1",
	    "janice,NORMAL, true,2,0,1,0,true,false,false,1,0,0",
	    "janice,NORMAL, false,2,0,2,0,true,false,false,1,0,0",
	    "janice,NORMAL, false,2,0,0,0,false,true,false,0,2,0",
	    "janice,NORMAL, true,2,0,0,0,true,false,false,2,0,0",
	    "janice,NORMAL, false,2,0,2,0,true,false,false,2,0,0",
	    "janice,NORMAL, false,2,0,0,0,false,false,true,0,0,2",
	    "janice,NORMAL, false,2,0,1,0,true,true,false,1,1,0",
	    "janice,NORMAL, false,2,0,0,0,false,true,true,0,1,1",
	    "janice,NORMAL, true,2,0,0,0,true,true,false,1,1,0",
	    "janice,NORMAL, false,2,0,1,0,true,false,true,1,0,1",
	    "janice,NORMAL, true,2,0,0,0,true,false,true,1,0,1",
	    // all possibility with 1 room booking as NORMAL.
	    "janice,NORMAL, false,1,0,1,0,false,false,false,0,0,0",
	    "janice,NORMAL, false,1,0,0,0,false,true,false,0,1,0",
	    "janice,NORMAL, false,1,0,0,0,false,false,true,0,0,1",
	    "janice,NORMAL, true,1,0,0,0,true,false,false,1,0,0",
	    "janice,NORMAL, false,1,0,1,0,true,false,false,1,0,0",
	    // all possibility with 1 room booking as NON_MEMBER.
	    "siti, NON_MEMBER, false,1,0,0,0,false,false,true,0,0,1",
	    "siti, NON_MEMBER, false,1,0,0,1,false,false,false,0,0,0",
	})
	public void testSetBooking(String username, String userType, boolean rewardStatus, 
			int numRoomsToBook, int expectedVipWaitingListCount, int expectedDeluxeWaitingListCount, 
			int expectedStandardWaitingListCount, boolean vipcheckroom, boolean deluxecheckroom,
			boolean standardcheckroom, int viproom, int deluxeroom, int standardroom) {
        
		// create object instance 
		User user = new User(username,userType,rewardStatus);
        WaitingList waitingList = new WaitingList();
	    Room mockRoom = mock(Room.class);
        Booking booking = new Booking(user);
        
	    mockRoom.vip = viproom ;
	    mockRoom.deluxe = deluxeroom ;
	    mockRoom.standard = standardroom ;
        
	    when(mockRoom.checkRoom("vip")).thenReturn(vipcheckroom);
	    when(mockRoom.checkRoom("deluxe")).thenReturn(deluxecheckroom);
	    when(mockRoom.checkRoom("standard")).thenReturn(standardcheckroom);
	    
	    // will add user into waiting list if not enough room are allocated.
	    booking.setBooking(numRoomsToBook, mockRoom, waitingList);
	    
	    assertEquals(expectedVipWaitingListCount, waitingList.getVipWaitingList().size());
	    assertEquals(expectedDeluxeWaitingListCount, waitingList.getMemberWaitingList().size());
	    assertEquals(expectedStandardWaitingListCount, waitingList.getNormalWaitingList().size());

	    }
	
	@Test(expected=IllegalArgumentException.class)
	@Parameters({
		//numRoomsToBook,expectedVipRoomCount,expectedDeluxeRoomCount,expectedStandardRoomCount
		"marry, VIP, false,4",
	    "jerry, VIP, false,-2",
	    "janice, NORMAL, false,3",
	    "siti, NON_MEMBER, false,2",
	})
	public void testInvalidSetBooking(String username, String memberType, boolean reward, int numBook) {
	    // Create a user with an invalid type
	    User user = new User(username,memberType,reward);

	    // Create a Booking instance with the invalid user
	    Booking booking = new Booking(user);
	    
	    // Create mocks for WaitingList and Room
	    WaitingList waitingList = new WaitingList();
	    Room mockRoom = mock(Room.class);

	    // Attempt to book rooms with the invalid user, which should throw an IllegalArgumentException
	    booking.setBooking(numBook, mockRoom, waitingList);
	}
	@Test
	@Parameters({
	    "juanhong, VIP, false,0,0,0",
	    "janice, NORMAL, false,0,0,0",
	    "siti, NON_MEMBER, false,0,0,0",

	})
    public void testCancelBooking(String username, String userType, boolean rewardStatus,
    		 int expectedVipWaitingListCount,int expectedStandardWaitingListCount, int expectedDeluxeWaitingListCount) {
        // Create a user for the booking
        User user = new User(username, userType, rewardStatus);
        
        // Create a WaitingList object
        WaitingList waitingList = new WaitingList();
        
        // Add the user to the waiting list
        waitingList.addWaiting(user);
        
        // Create a Booking object
        Booking booking = new Booking(user);
        
        // Call the cancelBooking method
        booking.cancelBooking(waitingList);
        
        // Verify that the waiting list no longer contains the user
	    assertEquals(expectedVipWaitingListCount, waitingList.getVipWaitingList().size());
	    assertEquals(expectedDeluxeWaitingListCount, waitingList.getMemberWaitingList().size());
	    assertEquals(expectedStandardWaitingListCount, waitingList.getNormalWaitingList().size());
	    }
}
