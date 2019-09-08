/**
 * Member class for managing member details
 *
 * @author Jay Pancholi
 * @version 27/04/2018
 */
public class Member
{
    // instance variables
	private String memberName;
	private int memberId;
	private String sportsPlayed;
	private boolean isFinancial;
    
    /**
     * Constructor for objects of class Member
     */
    public Member(String memberName,int memberId, String sportsPlayed, boolean isFinancial)
    {
    	this.memberName = memberName;
    	this.memberId = memberId;
    	this.sportsPlayed = sportsPlayed;
    	this.isFinancial = isFinancial;
    } 
    /**
     * getMemberName method for accessing value
     *
     * @return  memberName
     */
    public String getMemberName()
    {
        return memberName;
    }
    /**
     * getMemberId method for accessing value
     *
     * @return  memberId
     */
    public int getMemberId()
    {
        return memberId;
    }
    /**
     * getFinancialStatus method for accessing value
     *
     * @return  isFinancial
     */
    public boolean getFinancialStatus()
    {
        return isFinancial;
    }
    /**
     * getSportsPlayed method for accessing value
     *
     * @return  sportsPlayed
     */
    public String getSportsPlayed()
    {
        return sportsPlayed;
    }
    /**
     * toString method for printing value
     *
     * @return    String
     */
    public String toString()
    {
        return "Member id: "+memberId+"\nMember name: "+memberName+"\nSports: "+sportsPlayed+"\nFinancial: "+isFinancial;
    }
    
}