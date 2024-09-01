package my.edu.utar;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {
	// initialize object for all testing
	User user = new User("Chow","VIP",false); 
	
	@Test
	public void testGetName() {
		// "Chow" will be compared with user.getName()
		String ER = "Chow";
		String AR = user.getName();
		assertEquals(ER,AR);
	}

	@Test
	public void testSetName() {
		// use user.setName("Tan") to change to name of 'user'
		user.setName("Tan");
		// compare "Tan" with user.getName()
		assertEquals("Tan",user.getName());
	}
	
	//Invalid test for setName
	@Test(expected=IllegalArgumentException.class)
	public void testSetNameFail() {
		// empty value will invoke IllegalArgument Exception
		user.setName("");
	}
	
	@Test
	public void testGetRewardStatus() {
		// "false" will be compared with user.getRewardStatus()
		Boolean AR = user.getRewardStatus();
		Boolean ER = false;
		assertEquals(AR,ER);
	}

	@Test
	public void testSetRewardStatus() {
		// change the reward status to true
		user.setRewardStatus(true);
		// test if the change is success
		assertTrue(user.getRewardStatus());
	}
	
	// invalid test for setRewardStatus
	@Test(expected=IllegalArgumentException.class)
	public void testSetRewardStatusFail() {
		// null value will invoke IllegalArgumentException
		user.setRewardStatus(null);
	}

	@Test
	public void testGetMemberType() {
		// "VIP" will be compared with user.getMemberType()
		String AR = user.getMemberType();
		String ER = "VIP";
		assertEquals(AR,ER);
	}
	
	@Test
	public void testSetMemberType() {
		// set member type to "NORMAL"
		user.setMemberType("NORMAL");
		// compare "NORMAL" with user.getMemberType()
		assertEquals(user.getMemberType(),"NORMAL");
	}
	
	//Invalid test
	@Test(expected=IllegalArgumentException.class)
	public void testSetMemberTypeFail() {
		// Member type can only accept "VIP","NORMAL","NON_MEMBER"
		user.setMemberType("USER");
	}
}
