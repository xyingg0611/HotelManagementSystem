package my.edu.utar;
public class User{
    private String name;
    private String member_type;
    private Boolean excl_reward;

    public User(String name, String member_type, Boolean excl_reward) {
        setName(name);
        setMemberType(member_type);
        setRewardStatus(excl_reward);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) throws IllegalArgumentException{
    	if(name.equals("")) {
    		throw new IllegalArgumentException("Name cannot be null or empty.");
    	}
        this.name = name;
    }

    public String getMemberType() {
        return member_type;
    }
    public void setMemberType(String member_type) throws IllegalArgumentException{
    	if (!(member_type.equals("VIP") ||member_type.equals("NORMAL") ||member_type.equals("NON_MEMBER"))) {
    		throw new IllegalArgumentException("Invalid member type");
    	}
        this.member_type = member_type;
    }

    public boolean getRewardStatus() {
        return excl_reward;
    }
    public void setRewardStatus(Boolean excl_reward) throws IllegalArgumentException{
    	if(excl_reward == null) {
    		throw new IllegalArgumentException("Invalid boolean type.");
    	}
    	this.excl_reward = excl_reward;
    }

}
