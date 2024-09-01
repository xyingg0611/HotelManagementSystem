# HotelBookingSystem

# Installs

1. Go to this website to download Eclipse:
https://www.eclipse.org/downloads/
   - **Using Eclipse will be much easier to perfome testing compare to other compiler.**
   - The link below contains the guideline to complete the downloading process : https://www.eclipse.org/downloads/packages/installer

2. Go to  this website to download VMware:
https://www.vmware.com/info/workstation-player/evaluation
   - **VMware is a vitual machine is a virtualization and cloud computing software provider.** 
User can us VMware to gain asses to Windows10 to Windows 11.
   - The link below contains the guideline to install and use VMware:
https://www.youtube.com/watch?v=cygsGM5sNJo

# Prerequisite
**Junit4 and Jar files must be installed and set up properly**
For the following process, please refer to **Set Up The Enviroment**.

# Set Up
1. Download all the files provided onto your desktop.
2. Open your Eclipse.
3. On the top left-hand corner, you can see **File**; click on it.
4. Next, click on **import**.
5. Click **Next** and choose **Browse**. (This will lead you into your directory and select the folder you had downloaded just now.)
6. Click **Finish**.
7. Now you can see the project file on the right-hand side in Eclipse.

**Reminder**
1. If your library dependency is below JAVA-SE18, you have to change to Java System Libary (JAVA SE-20).




# Test Description

Junit is a widely used testing framework for Java programming. It helps developers swrite and run tests for their code, ensuring that individual units of functionality work as intended.

## Installed jar files
1.	Provide the jar file
2.	Install the jar file on your own laptop or PC it will be use after this.

## Set Up The Environment
1.	Eclipse contains the JUnit package to create a JUnit test.

2.	The library can be added during the creation of class using the wizard. If we want to add it later, **right-click** on project name in the packages explorer and select **Build Path -> Add Libraries**.

3.	Select the JUnit from the list. Then, click **[Next]**

4. Select JUnit 4 and then **[Finish]**, You should see the JUnit library has been added into the project in the package explorer.

5. The **jar file** can be added during the creation of class using the wizard. If we want to add it later, right-click on project name in the package explorer and select confugure build path.

6.	Select Add External JARs at **right hand side.**

7.	Select **All** the jar file on your desktop file, then click **open**.

8.	After that, we can see the jar that we selected was shown in the library. Then click **Apply and Close** at the right bottom.

9.	You also may check the library and jar file that are added on the left-hand side.

10.	Once we set up the testing environment , we are able to conduct unit testing, integration testing and test suite

## Unit Test
Unit testing is a software testing technique where individual components or functions of a program are tested in isolation to ensure they work as expected. These tests are typically automated and focus on verifying the correctness of specific parts of the code, often called "units."

1.	The @RunWith(JUnitParamsRunner.class) annotation is used in JUnit testing to specify a custom runner class that will execute the test class. In this case, the JUnitParamsRunner is a runner provided by the JUnitParams library, which is used to support parameterized tests in JUnit.

2.	To make the method will run in test, there must be an @Test before the method.

3.	Example code:
```java
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
```
4.	To view if the testing method is valid or invalid, we can click the green icon for run. Then it will show the result green color means valid or successful, red color means invalid or unsuccessful.
<img width="479" alt="Screenshot 2024-09-01 at 1 44 11 PM" src="https://github.com/user-attachments/assets/447a324c-206e-410b-9310-b6e2c5e219e7">

![image](https://github.com/user-attachments/assets/4b46a6af-b0c7-4946-82e0-0427e81fe3fd)

## Integration Test
1.	The @RunWith(JUnitParamsRunner.class) annotation is used in JUnit testing to specify a custom runner class that will execute the test class. In this case, the JUnitParamsRunner is a runner provided by the JUnitParams library, which is used to support parameterized tests in JUnit.

2.	To run the integration test , we will using mockito, it is required to include the jar_files posted above to carry out the testing.

```java
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

```
## Additional Notes
There are other testing classes and you can explore them! Good Luck!!!!

# Build Configuration with Gradle
## Prerequisites
1.	Install Gradle
-	Ensure Gradle is installed on your system. You can download it from https://docs.gradle.org/current/userguide/installation.html#ex-installing-manually

2.	Install java
-	Ensure JDK is installed on your system, version acceptable are JDK 17 and JDK 20. Also if there is latest version of JDK is acceptable.
-	Download link refer https://www.oracle.com/my/java/technologies/downloads/#jdk22-windows

Steps:
1.	Navigate to the project directory
-	Open command prompt
-	Use cd to navigate ‘SoftwareTestingAssignment’ directory
-	cd path\to\SoftwareTestingAssignment

2.	Create the ‘build.gradle’ file
-	Use text editor like notepad to create the ‘build.gradle’ file
-	Write the below code in the ‘build.gradle’ file
-	![image](https://github.com/user-attachments/assets/bb8a8f23-19e5-44b4-8451-78df6ffc44cd)
-	After done enter the code, save the file in the ‘SoftwareTestingAssignment’ directory
  
3.	Directory structure
-	Ensure the ‘SoftwareTestingAssignment’’ directory has the following structure
-	![image](https://github.com/user-attachments/assets/53358749-3642-4456-8b43-90acb6f2dd0e)
-	Command for use check the project directory (dir build.gradle OR dir)
  
4.	Run Gradle command
-	gradle init
-	（a)Select Application as type of build and
-	 (b)Select the implementation language as Java.
-	 (c)Then, enter the appropriate Java version (max 21)
-	 (d)Select single application project as application structure.
-	 (e)Select Kotlin as build script DSL
-	 (f)select J Unit 4 for the test framework.
  
5.	Compile code
-	gradle build

6. 	Run the test
-	gradle test

# Deployment
How to deploy the JAVA program on the GitHub by using Git

Steps
1. Downlaod Git from official website
   https://git-scm.com/download/win .
2. Set up the Git  which you can find git bash in your Start menu after installation.
3. Set Up your name and email address, which will be used for commit messages .
4. Run these commands in Git Bash:
```
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```
5. Create new repository on GitHub
6. Initialize your local repository
```
cd path/to/your/project
```
- initialize git
```
git init
```

7. Add and commit the files
```
git add .
git commit -m "Initial commit"
```

8. Add remote Reposiory, go to your GitHub repository page, and find the URL under the "Code button" Copy the URL
```
git remote add origin https://github.com/username/repository.git
```
replace "https://github.com/username/repository.git" with your copies URL

10. Push to GitHub
   ```
   git push -u origin master
   ```
   if you're using a different branch(main), replace 'master' with 'main'





 
