package my.edu.utar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class BookingTest {
	private Booking booking;
    private User user;
    private Room room;
    private WaitingList waitingList;
    
    static ArrayList<String[]> linesRead;
    // Initialize test data from file
    static {
        Scanner inputStream = null;
        linesRead = new ArrayList<>();
        String testDataFileName = "testData.txt";
        System.out.println("Reading test values from file " + testDataFileName + "\n");
        try {
            inputStream = new Scanner(new File(testDataFileName));
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the file " + testDataFileName);
            System.exit(0);
        }

        while (inputStream.hasNextLine()) {
            String singleLine = inputStream.nextLine();
            String[] tokens = singleLine.split(",");
            linesRead.add(tokens);
        }

        inputStream.close();
    }

    @Before
    public void setUp() {
        user = mock(User.class);
        room = mock(Room.class);
        waitingList = mock(WaitingList.class);;
        booking = new Booking(user);
    }
    


	private Object[] getDataForCancelBookingValid() {
		return new Object[] { 
				new Object[] { linesRead.get(0)[0],linesRead.get(0)[1],linesRead.get(0)[2] },
		};
	}
    
    @Test
    @Parameters(method = "getDataForCancelBookingValid")
    public void testCancelBookingValid(String username, String userType, boolean reward) {
        // Set up the mock user object
        when(user.getName()).thenReturn(username);
        when(user.getMemberType()).thenReturn(userType);
        when(user.getRewardStatus()).thenReturn(reward);

        // Add the user to the waiting list
        waitingList.addWaiting(user);

        // Call the cancelBooking method with the mock waiting list
        booking.cancelBooking(waitingList);

        // Verify that the removeWaiting method was called with the expected user
        verify(waitingList).removeWaiting(user);
    }
    
  //test the cancel booking by using the null user, an IllegalArgumentException should be detected
  	@Test(expected = IllegalArgumentException.class)
  	public void testInvalidCancelBookingNullUser() {
  	    booking = new Booking(null);
  	    
  	    // Attempt to call cancelBooking with the mock WaitingList
  	    // This should throw an IllegalArgumentException because the user is null
  	    booking.cancelBooking(waitingList);
  	    
  	    // Verify that removeWaiting is never called because the user is null
  	    verify(waitingList, never()).removeWaiting(any(User.class));
  	}
  	
	private Object[] getDataForGetBooking() {
		return new Object[] { 
				new Object[] { linesRead.get(0)[0],linesRead.get(0)[1],linesRead.get(0)[2] },
		};
	}
	
  //test the getBooking function by inputing the valid data and check whether the function can pass the test?
	@Test
    @Parameters(method = "getDataForGetBooking")
  	public void testGetBooking(String username, String userType, boolean reward) {
        when(user.getName()).thenReturn(username);
        when(user.getMemberType()).thenReturn(userType);
        when(user.getRewardStatus()).thenReturn(reward);
        
        Booking booking = new Booking(user);

  	    // Prepare the expected result from the bookings.txt
  	    String expectedResult = "John VIP 1";

  	    // Call the getBooking() method and capture the result
  	    String result = booking.getBooking(user);

  	    // Verify that the result matches the expected result
  	    assertEquals(expectedResult, result);
  	}
	
	//test the getBooking by using the null user for the invalid test
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidGetBookingNullUser() {
	    // Create a Booking instance with a null user
	    Booking booking = new Booking(null);
	    
	    // Call the getBooking() method with null user, expecting an exception
	    booking.getBooking(null);
	}
	
	//test the getBooking with the unknown username
	@Test
	public void testInvalidGetBookingUserNotFound() {
        when(user.getName()).thenReturn("unknown_user");
        when(user.getMemberType()).thenReturn("VIP");
        when(user.getRewardStatus()).thenReturn(false);

	    Booking booking = new Booking(user);
	    
	    // Call the getBooking() method and capture the result
	    String result = booking.getBooking(user);
	    
	    // Verify that the result is null since the user is not found in the file
	    assertNull(result);
	}
	
	//Equivalent Partition for valid test for enough room for setBooking function
	//Partition of VIP for room booking is 3, thus the numRoom test is 3
	//Partition of NORMAL for room booking is 2, thus the numRoom test is 2 
	//Partition of NON_MEMBER for room booking is 1, thus the numRoom test is 1.
	//all the other value is same for the testing
	private Object[] getDataForSetBookingEnoughRoom() {
		return new Object[] { 
				new Object[] { linesRead.get(1)[0],linesRead.get(1)[1],linesRead.get(1)[2],linesRead.get(1)[3],linesRead.get(1)[4] ,linesRead.get(1)[5],linesRead.get(1)[6]},
				new Object[] { linesRead.get(2)[0],linesRead.get(2)[1],linesRead.get(2)[2],linesRead.get(2)[3],linesRead.get(2)[4] ,linesRead.get(2)[5],linesRead.get(2)[6]},
				new Object[] { linesRead.get(3)[0],linesRead.get(3)[1],linesRead.get(3)[2],linesRead.get(3)[3],linesRead.get(3)[4] ,linesRead.get(3)[5],linesRead.get(3)[6]},
				new Object[] { linesRead.get(4)[0],linesRead.get(4)[1],linesRead.get(4)[2],linesRead.get(4)[3],linesRead.get(4)[4] ,linesRead.get(4)[5],linesRead.get(4)[6]}
		};
	}
	@Test
    @Parameters(method = "getDataForSetBookingEnoughRoom")
	public void testValidSetBookingWithEnoughRoom(String username, String userType, boolean rewardStatus, int numRoomsToBook, int expectedVipRoomCount, int expectedDeluxeRoomCount, int expectedStandardRoomCount) {
	    
        when(user.getName()).thenReturn(username);
        when(user.getMemberType()).thenReturn(userType);
        when(user.getRewardStatus()).thenReturn(rewardStatus);
        
	    Booking booking = new Booking(user);
	    
	    
	    //preset the number of room available for testing
	    room.vip = 5 ;
	    room.deluxe = 5 ;
	    room.standard = 5 ;
	    
	    // Set mock behavior for checkRoom method in Room
	    when(room.checkRoom("vip")).thenReturn(room.vip > 0);
	    when(room.checkRoom("deluxe")).thenReturn(room.deluxe > 0);
	    when(room.checkRoom("standard")).thenReturn(room.standard > 0);
	    
	    // Call setBooking
	    booking.setBooking(numRoomsToBook, room, waitingList);
	    
	    // Verify the expected room counts after booking
	    assertEquals(expectedVipRoomCount, room.vip);
	    assertEquals(expectedDeluxeRoomCount, room.deluxe);
	    assertEquals(expectedStandardRoomCount, room.standard);
	}
	
	//Equivalent Partition for valid test for the not enough room for setBooking function
	private Object[] getDataForSetBookingNotEnoughRoom() {
		return new Object[] { 
				new Object[] { linesRead.get(5)[0],linesRead.get(5)[1],linesRead.get(5)[2],linesRead.get(5)[3],linesRead.get(5)[4] ,linesRead.get(5)[5],linesRead.get(5)[6]},
				new Object[] { linesRead.get(6)[0],linesRead.get(6)[1],linesRead.get(6)[2],linesRead.get(6)[3],linesRead.get(6)[4] ,linesRead.get(6)[5],linesRead.get(6)[6]},
				new Object[] { linesRead.get(7)[0],linesRead.get(7)[1],linesRead.get(7)[2],linesRead.get(7)[3],linesRead.get(7)[4] ,linesRead.get(7)[5],linesRead.get(7)[6]},
				new Object[] { linesRead.get(8)[0],linesRead.get(8)[1],linesRead.get(8)[2],linesRead.get(8)[3],linesRead.get(8)[4] ,linesRead.get(8)[5],linesRead.get(8)[6]}
		};
	}
	@Test
    @Parameters(method = "getDataForSetBookingNotEnoughRoom")	
	public void testValidSetBookingWithNotEnoughRoom(String username, String userType, boolean rewardStatus, int numRoomsToBook, int expectedVipRoomCount, int expectedDeluxeRoomCount, int expectedStandardRoomCount) {
        when(user.getName()).thenReturn(username);
        when(user.getMemberType()).thenReturn(userType);
        when(user.getRewardStatus()).thenReturn(rewardStatus);

	    Booking booking = new Booking(user);
	    
	    
	    //preset the number of room available for testing
	    room.vip = 1 ;
	    room.deluxe = 1 ;
	    room.standard = 1 ;
	    
	    // Set mock behavior for checkRoom method in Room
	    when(room.checkRoom("vip")).thenReturn(room.vip > 0);
	    when(room.checkRoom("deluxe")).thenReturn(room.deluxe > 0);
	    when(room.checkRoom("standard")).thenReturn(room.standard > 0);
	    
	    // Call setBooking
	    booking.setBooking(numRoomsToBook, room, waitingList);
	    
	    // Verify the expected room counts after booking
	    assertEquals(expectedVipRoomCount, room.vip);
	    assertEquals(expectedDeluxeRoomCount, room.deluxe);
	    assertEquals(expectedStandardRoomCount, room.standard);
	}
	
	//Equivalent Partition for invalid test by using no room available and let the user pass into the waiting list
	private Object[] getDataForSetBookingNotEnoughRoomToWaitingList() {
		return new Object[] { 
				new Object[] { linesRead.get(9)[0],linesRead.get(9)[1],linesRead.get(9)[2],linesRead.get(9)[3]},
				new Object[] { linesRead.get(10)[0],linesRead.get(10)[1],linesRead.get(10)[2],linesRead.get(10)[3]},
				new Object[] { linesRead.get(11)[0],linesRead.get(11)[1],linesRead.get(11)[2],linesRead.get(11)[3]},
				new Object[] { linesRead.get(12)[0],linesRead.get(12)[1],linesRead.get(12)[2],linesRead.get(12)[3]}

		};
	}
	@Test
    @Parameters(method = "getDataForSetBookingNotEnoughRoomToWaitingList")	
	public void testValidSetBookingWithNotEnoughRoomToWaitingList(String username, String userType, boolean rewardStatus, int numRoomsToBook) {
        when(user.getName()).thenReturn(username);
        when(user.getMemberType()).thenReturn(userType);
        when(user.getRewardStatus()).thenReturn(rewardStatus);
        
	    Booking booking = new Booking(user);
	    
	    
	    // Set initial room counts and availability
	    room.vip = 0; // No VIP rooms available
	    room.deluxe = 0; // No deluxe rooms available
	    room.standard = 0; // No standard rooms available
	    
	    // Call the setBooking method
	    booking.setBooking(numRoomsToBook, room, waitingList);
	   
	    // Verify that the user was added to the waiting list when there is no enough room
	    // VIP book 3 rooms will invoke add waiting list 3 times as 3 type of room is not available for each attempt for booking (VIP, DELUXE, STANDARD)
	    // NORMAL book 2 rooms will invoke add waiting list 2 times as 2 type of room is not available for each attempt for booking (DELUXE, STANDARD)
	    // NON_MEMBER book 3 rooms will invoke add waiting list 1 times as 1 type of room is not available for each attempt for booking (STANDARD)
	    if (userType.equals("VIP")) {
	    	verify(waitingList, times(3)).addWaiting(user);
	    } else if (userType.equals("NORMAL")) {
	    	verify(waitingList, times(2)).addWaiting(user);
	    } else if (userType.equals("NON_MEMBER")) {
	    	verify(waitingList, times(1)).addWaiting(user);
	    }
	    
	}
	
	//Equivalent Partition for invalid test by using null user, other member type, invalid room number, null member type
	private Object[] getDataForInvalidSetBooking() {
		return new Object[] { 
				new Object[] { linesRead.get(13)[0],linesRead.get(13)[1],linesRead.get(13)[2],linesRead.get(13)[3]},
				new Object[] { linesRead.get(14)[0],linesRead.get(14)[1],linesRead.get(14)[2],linesRead.get(14)[3]},
				new Object[] { linesRead.get(15)[0],linesRead.get(15)[1],linesRead.get(15)[2],linesRead.get(15)[3]},
				new Object[] { linesRead.get(16)[0],linesRead.get(16)[1],linesRead.get(16)[2],linesRead.get(16)[3]},
				new Object[] { linesRead.get(17)[0],linesRead.get(17)[1],linesRead.get(17)[2],linesRead.get(17)[3]},
				new Object[] { linesRead.get(18)[0],linesRead.get(18)[1],linesRead.get(18)[2],linesRead.get(18)[3]},
				new Object[] { linesRead.get(19)[0],linesRead.get(19)[1],linesRead.get(19)[2],linesRead.get(19)[3]},
				new Object[] { linesRead.get(20)[0],linesRead.get(20)[1],linesRead.get(20)[2],linesRead.get(20)[3]},
				new Object[] { linesRead.get(21)[0],linesRead.get(21)[1],linesRead.get(21)[2],linesRead.get(21)[3]},
				new Object[] { linesRead.get(22)[0],linesRead.get(22)[1],linesRead.get(22)[2],linesRead.get(22)[3]}
		};
	}
	@Test(expected = IllegalArgumentException.class)
	@Parameters(method = "getDataForInvalidSetBooking")
	public void testInvalidSetBooking(String username, String userType, boolean rewardStatus, int numBook) {
	    // Create user with provided data
        when(user.getName()).thenReturn(username);
        when(user.getMemberType()).thenReturn(userType);
        when(user.getRewardStatus()).thenReturn(rewardStatus);
	    
	    // Create booking with the created user
	    Booking booking = new Booking(user);

	    // Call setBooking and expect an IllegalArgumentException
	    booking.setBooking(numBook, room, waitingList);
	}

}