/**
 * Badminton class as child class
 *
 * @author Jay Pancholi
 * @version 27/04/2018
 */
public class Badminton extends Sport
{
    // instance variables
	private boolean needRacket;
    /**
     * Constructor for objects of class Badminton
     */
    public Badminton(String sportName, int usageFees, int insuranceFees, boolean needRacket)
    {
    	super(sportName,usageFees,insuranceFees); // calling parent class constructor
    	this.needRacket = needRacket; 
    }
    /**
     * getNeedRacket method for accessing value
     *
     * @return  needRacket
     */
    public boolean getNeedRacket()
    {
        return needRacket;
    }
    /**
     * setNeedRacket method for seting value of needRacket
     *
     * @param  needRacket varible 
     * @return    boolean true
     */
    public boolean setNeedRacket(boolean needRacket)
    {
        this.needRacket =needRacket;
        return true;
    }
    /**
     * toString method for printing value
     *
     * @return    String
     */
    public String toString()
    {
        return "Sport Name: "+sportName+", usageFees: "+usageFees+", insuranceFees: "+insuranceFees+", needRacket: "+needRacket;
    }

}
