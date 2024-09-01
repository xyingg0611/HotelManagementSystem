package my.edu.utar;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
	
    public static void main(String args[]){

        Scanner scanner = new Scanner(System.in);
        Room rooms = new Room();
        WaitingList waitinglist = new WaitingList();
        User user;

        boolean status = true;
        while (status){
            System.out.println("Please enter your name:");
            String name = scanner.next();
            int choice;
            do{
                System.out.println("Please enter your member type:");
                System.out.println("    1. VIP");
                System.out.println("    2. Normal member");
                System.out.println("    3. Non-member");
                System.out.println("\nPlease enter the number:");
                choice = scanner.nextInt();
                if(choice <1 || choice >3){
                    System.out.println("Please enter 1, 2 or 3.");
                }
            }while(choice <1 || choice >3);
            if (choice ==1){
                user = new User(name,"VIP",false);
            }
            else if (choice == 2){
                int reward;
                do{
                    System.out.println("Do u have exclusive reward?");
                    System.out.println("    1. yes");
                    System.out.println("    2. no");
                    reward = scanner.nextInt();
                    if(reward <1 || reward>2){
                        System.out.println("Enter 1 or 2 only.");
                    }
                }while(reward <1 || reward>2);
                if(reward ==1){
                    user = new User(name, "NORMAL", true);
                }
                else{
                    user = new User(name, "NORMAL", false);
                }
            }
            else{
                user = new User(name, "NON_MEMBER", false);

            }
            
            scanner.nextLine();

            // Main interface
            boolean status2 = true;
            while (status2){
                int decision;
                do{
                    System.out.println("Dear customer, do you wish to:");
                    System.out.println("    1. Set booking");
                    System.out.println("    2. Cancel booking");
                    System.out.println("    3. Get waiting list");
                    System.out.println("    4. Change to another user");
                    System.out.println("Please key in your selection");
                    decision = scanner.nextInt();
                    if (decision <1 || decision >4){
                        System.out.println("Please enter between 1 to 4");
                    }
                }while(decision <1 || decision >4);

                Booking booking = new Booking(user);
                if(decision == 1){
                    int numRooms;
                    do{
                    System.out.println("Please enter the number of room you wish to book:");
                    System.out.println("    Maximum number of room to book:");
                    System.out.println("        VIP:3");
                    System.out.println("        Member:2");
                    System.out.println("        Non-member:1");
                    numRooms = scanner.nextInt();
                    if (numRooms <1 || numRooms >3){
                        System.out.println("Please choose between 1 to 3.");
                    }
                    }while(numRooms <1 || numRooms >3);
                    booking.setBooking(numRooms, rooms, waitinglist);
                }
                else if (decision == 2){
                    booking.cancelBooking(waitinglist);
                }
                else if (decision == 3){
                    System.out.println(waitinglist.getWaiting(user)); 
                }
                else{
                    status2 = false;
                }
            }
        }
    scanner.close();
    }




}
