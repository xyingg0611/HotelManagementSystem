package my.edu.utar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class Booking {
    private User user;
	private ArrayList<String> strList;
	private int strLimit = 0;
	

    public Booking(User user) {
        this.user = user;
    }
    
	public void setBooking(int numRooms, Room room, WaitingList waitinglist) {
        int maxRooms;
        String memberType = user.getMemberType();

        if (user == null || user.getName() == "") {
            throw new IllegalArgumentException("Username cannot be null");
        }
        
        if (!memberType.equals("VIP") && !memberType.equals("NORMAL") && !memberType.equals("NON_MEMBER")) {
            throw new IllegalArgumentException("Invalid user type: " + memberType);
        }
        
     // Determine the maximum rooms based on membership type
        switch (memberType) {
            case "VIP":
                maxRooms = 3;
                break;
            case "NORMAL":
                maxRooms = 2;
                break;
            case "NON_MEMBER":
                maxRooms = 1;
                break;
            default:
                throw new IllegalArgumentException("Invalid user type: " + memberType);
        }

        // Validate the number of rooms requested
        if (numRooms < 1 || numRooms > maxRooms) {
            throw new IllegalArgumentException("Number of rooms requested is invalid. Allowed range: 1-" + maxRooms);
        }
        
        switch(memberType) {
            case "VIP":
                maxRooms = 3; 
                if (room.checkRoom("vip")) {
                    room.vip -= numRooms;
                    if (room.vip < 0){
                        if(room.checkRoom("deluxe")){
                            room.deluxe -= Math.abs(room.vip);
                            if(room.deluxe < 0){
                                if(room.checkRoom("standard")){
                                    room.standard -= Math.abs(room.deluxe);
                                    if (room.standard < 0){
                                        for(int i = room.standard;i < 0; i++){
                                            waitinglist.addWaiting(user); 
                                        }  
                                        room.standard = 0;
                                    }   
                                }
                                else {
                                	for(int i = room.deluxe; i<0;i++) {
                                		waitinglist.addWaiting(user);
                                	}
                                }
                                room.deluxe = 0;
                            }
                        }
                        else if(room.checkRoom("standard")){
                            room.standard -= Math.abs(room.vip);
                            if (room.standard < 0){
                                for(int i = room.standard;i < 0; i++){
                                    waitinglist.addWaiting(user); 
                                }  
                                room.standard = 0;
                            }   
                        }
                        else{
                            for(int i = room.vip;i < 0; i++){
                                waitinglist.addWaiting(user); 
                            }  
                        }
                        room.vip = 0;
                    }
                }   
                else if (room.checkRoom("deluxe")) {
                    room.deluxe -= numRooms;
                    if(room.deluxe < 0){
                        if(room.checkRoom("standard")){
                            room.standard -= Math.abs(room.deluxe);
                            if (room.standard < 0){
                                for(int i = room.standard;i < 0; i++){
                                    waitinglist.addWaiting(user); 
                                }  
                                room.standard = 0;
                            }   
                        }
                        else{
                            for(int i = room.deluxe;i < 0; i++){
                                waitinglist.addWaiting(user); 
                            }  
                        }
                        room.deluxe = 0;
                    }
                }   
                else if (room.checkRoom("standard")) {
                    room.standard -= numRooms;
                    if (room.standard <0){
                        for(int i = room.standard;i < 0; i++){
                            waitinglist.addWaiting(user);
                        }
                        room.standard = 0;
                    }
                }   
                else {
                    for (int i = 0;i<numRooms;i++){
                        waitinglist.addWaiting(user);
                    }
                }
                break;
            case "NORMAL":
                if (room.checkRoom("deluxe")) {
                    room.deluxe -= numRooms;
                    if (room.deluxe <0){
                        if(user.getRewardStatus() && room.checkRoom("vip")){
                            room.vip -= Math.abs(room.deluxe);
                            user.setRewardStatus(false);
                            if (room.vip < 0){
                                if(room.checkRoom("standard")){
                                    room.standard -= Math.abs(room.vip);
                                    if (room.standard <0){
                                        for(int i = room.standard;i < 0; i++){
                                            waitinglist.addWaiting(user);
                                        }
                                        room.standard = 0;
                                    }
                                }
                                else {
                                	for(int i = room.vip;i<0;i++) {
                                		waitinglist.addWaiting(user);
                                	}
                                }
                                room.vip = 0;
                            }
                        }
                        else if(room.checkRoom("standard")){
                            room.standard -= Math.abs(room.deluxe);
                            if (room.standard <0){
                                for(int i = room.standard;i < 0; i++){
                                    waitinglist.addWaiting(user);
                                }
                                room.standard = 0;
                            }
                        }
                        else{
                            for(int i = room.deluxe;i < 0; i++){
                                waitinglist.addWaiting(user);
                            }
                        }
                        room.deluxe = 0;
                    }
                }
                else if (user.getRewardStatus() && room.checkRoom("vip")){
                    room.vip -= numRooms;
                    user.setRewardStatus(false);
                    if (room.vip < 0){
                        if(room.checkRoom("standard")){
                            room.standard -= Math.abs(room.vip);
                            if (room.standard <0){
                                for(int i = room.standard;i < 0; i++){
                                    waitinglist.addWaiting(user);
                                }
                                room.standard = 0;
                            }
                        }
                        else{
                            for(int i = room.vip;i < 0; i++){
                                waitinglist.addWaiting(user);
                            } 
                        }
                        room.vip = 0;
                    }
                }
                else if(room.checkRoom("standard")){
                    room.standard -= numRooms;
                    if (room.standard <0){
                        for(int i = room.standard;i < 0; i++){
                            waitinglist.addWaiting(user);
                        }
                        room.standard = 0;
                    }
                }
                else {
                    for (int i = 0;i<numRooms;i++){
                        waitinglist.addWaiting(user);
                    }
                }
                break;
            case "NON_MEMBER":
                maxRooms = 1;
                if (room.checkRoom("standard")) {
                    room.standard -= numRooms;
                    if (room.standard <0){
                        for(int i = room.standard;i < 0; i++){
                            waitinglist.addWaiting(user);
                        }
                        room.standard = 0;
                    }
                } else {
                    for (int i = 0;i<numRooms;i++){
                        waitinglist.addWaiting(user);
                    }
                }
                break;
        }

}

    
	public String getBooking(User user) {
	    if (user == null) {
	        throw new IllegalArgumentException("User cannot be null");
	    }

	    try {
	        BufferedReader reader = new BufferedReader(new FileReader("bookings.txt"));
	        String line;
	        while ((line = reader.readLine()) != null) {
	            if (line.contains(user.getName())) {
	                reader.close();
	                return line;
	            }
	        }
	        reader.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null; // Return null if no booking is found for the user
	}
    
	public void cancelBooking(WaitingList waitingList) {
		if (user == null) {
			throw new IllegalArgumentException("Invalid user");
		} else {
			waitingList.removeWaiting(user);
		}
	}
    
 }

