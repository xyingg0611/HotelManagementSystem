package my.edu.utar;

import java.util.*;

//SUT
public class WaitingList {
	
	
    private List<User> vipWaitingList;
    private List<User> memberWaitingList;
    private List<User> normalWaitingList;

    public WaitingList() {
        this.vipWaitingList = new ArrayList<>();
        this.memberWaitingList = new ArrayList<>();
        this.normalWaitingList = new ArrayList<>();
    }
    
    //MUT
    public void addWaiting(User user) {
        switch(user.getMemberType()) {
            case "VIP":
                vipWaitingList.add(user);
                break;
            case "NORMAL":
                memberWaitingList.add(user);
                break;
            case "NON_MEMBER":
                normalWaitingList.add(user);
                break;
        }
    }

    public List<User> getNormalWaitingList() {
        return normalWaitingList;
    }

    public List<User> getVipWaitingList() {
        return vipWaitingList;
    }
    
    public List<User> getMemberWaitingList() {
        return memberWaitingList;
    }
    
    //MUT
    public String getWaiting(User user) {
        switch(user.getMemberType()) {
            case "VIP":
                if (vipWaitingList.isEmpty()) {
                    return "No user currently in VIP waiting list.";
                } else {
                    StringBuilder tobereturn = new StringBuilder("VIP Waiting List:\n");
                    for(int i=0; i<vipWaitingList.size(); i++) {
                        String name = vipWaitingList.get(i).getName();
                        tobereturn.append((i+1)).append(" . ").append(name != null ? name : "Unknown").append("\n");
                    }
                    return tobereturn.toString();
                }
            case "NORMAL":
                if (memberWaitingList.isEmpty()) {
                    return "No user currently in member waiting list.";
                } else {
                    StringBuilder tobereturn = new StringBuilder("Member Waiting List:\n");
                    for(int i=0; i<memberWaitingList.size(); i++) {
                        String name = memberWaitingList.get(i).getName();
                        tobereturn.append((i+1)).append(" . ").append(name != null ? name : "Unknown").append("\n");
                    }
                    return tobereturn.toString();
                }
            case "NON_MEMBER":
                if (normalWaitingList.isEmpty()) {
                    return "No user currently in non-member waiting list.";
                } else {
                    StringBuilder tobereturn = new StringBuilder("Non-member Waiting List:\n");
                    for(int i=0; i<normalWaitingList.size(); i++) {
                        String name = normalWaitingList.get(i).getName();
                        tobereturn.append((i+1)).append(" . ").append(name != null ? name : "Unknown").append("\n");
                    }
                    return tobereturn.toString();
                }
            default:
            	throw new IllegalArgumentException("Invalid user type: " + user.getMemberType());
        }
    }
    
    //MUT
    public void removeWaiting(User user) {
        switch (user.getMemberType()) {
            case "VIP":
                vipWaitingList.remove(user);
                break;
            case "NORMAL":
                memberWaitingList.remove(user);
                break;
            case "NON_MEMBER":
                normalWaitingList.remove(user);
                break;
        }
    }
}
