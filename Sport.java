/**
 * Sport class manage the things related with sport.
 *
 * @author Jay Pancholi
 * @version 27/04/2018
 */
import java.util.*;
public class Sport
{
    // instance variables
    protected String sportName;
    protected int usageFees;
    protected int insuranceFees;
    private ArrayList<Court> sportCourts;

    /**
     * Constructor for objects of class Sport
     */
    public Sport(String sportName, int usageFees, int insuranceFees)
    {        
    	this.sportName = sportName;
    	this.usageFees = usageFees;
    	this.insuranceFees = insuranceFees;
        this.sportCourts = new ArrayList<>();
    }
    /**
     * addCourt will invoke the constructor of court class
     *
     * @param  int Court
     */
    public void addCourt(int court)
    {
        sportCourts.add(new Court(court));
    }
    /**
     * getSportName method for accessing variable
     *
     * @return  sportName
     */
    public String getSportName()
    {
        return sportName;
    }
    /**
     * getSportCourtList method for accessing ArrayList
     *
     * @return  sportCourts
     */
    public ArrayList<Court> getSportCourtList()
    {
        return sportCourts;
    }
    /**
     * getUsageFees method for accessing variable
     *
     * @return  usageFees
     */
    public int getUsageFees()
    {
        return usageFees;
    }
    /**
     * getInsuranceFees method for accessing variable
     *
     * @return  insuranceFees
     */
    public int getInsuranceFees()
    {
        return insuranceFees;
    }

    /**
     * setSportName method for seting value of sportName
     *
     * @param  String sportName
     * @return    boolean true
     */
    public boolean setSportName(String sportName)
    {
        this.sportName = sportName;
        return true;
    }
    /**
     * setUsageFees method for seting value of usageFees
     *
     * @param  int usageFees
     * @return    boolean true
     */
    public boolean setUsageFees(int usageFees)
    {
        this.usageFees = usageFees;
        return true;
    }
    /**
     * setInsuranceFees method for seting value of insuranceFees.
     *
     * @param  int insuranceFees
     * @return    boolean true
     */
    public boolean setInsuranceFees(int insuranceFees)
    {
        this.insuranceFees =insuranceFees;
        return true;
    }

    /**
     * toString method for printing value
     *
     * @return    String
     */
    public String toString()
    {
          return "SportName: "+sportName+"\n usageFees: "+usageFees+"\n insuranceFees :"+insuranceFees+"\n Number of court: "+sportCourts.size();
    }
    
}