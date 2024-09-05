# HotelBookingSystem

# Installs

1. Go to this website to download Eclipse:
https://www.eclipse.org/downloads/
   - **Using Eclipse will make it much easier to perform testing compare to other compilers.**
   - The link below contains the guidelines to complete the downloading process: https://www.eclipse.org/downloads/packages/installer

2. Go to  this website to download VMware:
https://www.vmware.com/info/workstation-player/evaluation
   - **VMware is a virtual machine virtualization and cloud computing software provider.** 
Users can use VMware to gain access from Windows 10 to Windows 11.
   - The link below contains the guidelines for installing and using VMware:
https://www.youtube.com/watch?v=cygsGM5sNJo

# Prerequisite
**Junit4 and Jar files must be installed and set up properly**
Please refer to **Set Up The Enviroment**for for the following process.

# Set Up
1. Download all the files provided onto your desktop.
2. Open your Eclipse.
3. On the top left-hand corner, you can see **File**; click on it.
4. Next, click on **import**.
5. Click **Next** and choose **Browse**. (This will lead you into your directory and select the folder you downloaded just now.)
6. Click **Finish**.
7. You can see the project file on the right-hand side in Eclipse.

**Reminder**
1. If your library dependency is below JAVA-SE18, you have to change to Java System Libary (JAVA SE-20).




# Test Description

Junit is a widely used testing framework for Java programming. It helps developers write and run tests for their code, ensuring that individual units of functionality work as intended.

## Installed jar files
1.	Provide the jar file
2.	Install the jar file on your laptop or PC it will be used after this.

## Set Up The Environment
1.	Eclipse contains the JUnit package to create a JUnit test.

2.	The library can be added during the creation of class using the wizard. If we want to add it later, **right-click** on the project name in the packages explorer and select **Build Path -> Add Libraries**.

3.	Select the JUnit from the list. Then, click **[Next]**

4. Select JUnit 4 and then **[Finish]**, You should see the JUnit library has been added to the project in the package explorer.

5. The **jar file** can be added during the creation of class using the wizard. If we want to add it later, right-click on the project name in the package explorer and select configure build path.

6.	Select Add External JARs at **right-hand side.**

7.	Select **All** the jar file on your desktop file, then click **open**.

8.	After that, we can see the jar that we selected was shown in the library. Then click **Apply and Close** at the right bottom.

9.	You also may check the library and jar file that are added on the left-hand side.

10.	Once we set up the testing environment, we can conduct unit testing, integration testing, and test suite.

## Unit Test
Unit testing is a software testing technique where individual components or functions of a program are tested in isolation to ensure they work as expected. These tests are typically automated and focus on verifying the correctness of specific parts of the code, often called "units."

1.	The @RunWith(JUnitParamsRunner.class) annotation is used in JUnit testing to specify a custom runner class that will execute the test class. In this case, the JUnitParamsRunner is a runner provided by the JUnitParams library, which is used to support parameterized tests in JUnit.

2.	To make the method run in the test, there must be an @Test before the method.

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

## Additional Notes
There are other testing classes and you can explore them! Good Luck!

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
-	Use cd to navigate the 'HotelManagementSystem' directory
-	cd path\to\HotelManagementSystem
  
2.	Run Gradle command
-	gradle init
-	（a)Select Basic as type of build and
-	 (b)Enter your project name
-	 (c)Select Groovy
-	 (d)Choose if you want to generate build using new APIs and behavior
  
3.	Compile code
-	Paste the below code into build.gradle.
```
apply plugin: 'java'
apply plugin:'jacoco'
jacoco {
    toolVersion = "0.8.7"
}

apply from: 'test.gradle'

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}



repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
    flatDir {
        dirs 'jar_files/jar_files'
    }
}

dependencies {
    // This dependency is used by the application.
    testImplementation 'junit:junit:4.13.2'

    // Include local JAR files
    testImplementation name: 'byte-buddy-1.12.8'
    testImplementation name: 'byte-buddy-agent-1.12.8'
    testImplementation name: 'JUnitParams-1.0.2'
    testImplementation name: 'mockito-core-4.4.0'
    testImplementation name: 'objenesis-3.2'
}

testing {
    suites {
        // Configure the built-in test suite
        test {
            // Use JUnit4 test framework
            useJUnit('4.13.2')
        }
    }
}
```
-	Then run 'gradle build' in command prompt.

4. 	Run the test
-	Paste the below code in test.gradle.
```
// Configure the test task
test {
    // Specify the directory containing test classes
    testClassesDirs = sourceSets.test.output.classesDirs

    // Optionally configure test options
    testLogging {
        // Configure which events to log during test execution
        events 'passed', 'skipped', 'failed'
    }
    finalizedBy jacocoTestReport
}

jacocoTestReport {
    dependsOn test
    reports {
        xml.required = true
        html.required = true
    }
}
```
-	Then run 'gradle test' in command prompt.

# Deployment Automation
How to deploy the JAVA program on Docker

Steps
1. Downlaod Docker from official website
   https://docs.docker.com/desktop/
   <img width="638" alt="Screenshot 2024-09-05 at 10 43 53 AM" src="https://github.com/user-attachments/assets/9ba04a03-60b4-4a5e-9d58-b37609971fc7">
3. Create file called 'Dockerfile' in your repository.
4. Type in according to the below code into the Dockerfile.

<img width="385" alt="image" src="https://github.com/user-attachments/assets/76b4f0dc-278d-457f-8441-efff4f6806d6">

6. Run commmand “docker build -t hotelmanagement:1.0”.
7. Run command "docker run -d --name hotelmanagement-container -p 8083:8080 hotelmanagement:1.0".
8. The container should now visible in your Docker app.

9. Run these commands in Git Bash:
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





 
