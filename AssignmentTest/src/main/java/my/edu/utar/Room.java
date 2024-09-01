package my.edu.utar;

public class Room {
    int vip;
    int deluxe;
    int standard;

    public Room() {
        vip = 1;
        deluxe = 3;
        standard = 0;
    }
    
    public boolean checkRoom(String room_type){
        if (vip > 0 && room_type.equals("vip")){
            return true;
        }
        else if (deluxe > 0 && room_type.equals("deluxe")){
            return true;
        }
        else if (standard > 0 && room_type.equals("standard")){
            return true;
        }
        else{
            return false;
        }
    }
}

