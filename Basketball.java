/**
 * Basketball class as child class
 *
 * @author Jay Pancholi
 * @version 27/04/2018
 */
public class Basketball extends Sport
{
    // instance variables
	private int netHight;
    /**
     * Constructor for objects of class Basketball
     */
    public Basketball(String sportName, int usageFees, int insuranceFees, int netHight)
    {
    	super(sportName,usageFees,insuranceFees);
    	this.netHight = netHight;
    }
    /**
     * getHight method for accessing value of this varable
     *
     * @return  int netHight
     */
    public int getNetHight()
    {
        return netHight;
    }
    /**
     * setNetHight method for seting value of setNetHeight
     *
     * @param  netHeight varible 
     * @return    boolean true
     */
    public boolean setNetHight(int netHight)
    {
        this.netHight= netHight;
        return true;
    }
    /**
     * toString method for printing value of class variable
     *
     * @return    String
     */
    public String toString()
    {
        return "Sport Name: "+sportName+", usageFees: "+usageFees+", insuranceFees: "+insuranceFees+", netHight: "+netHight;
    }

}
