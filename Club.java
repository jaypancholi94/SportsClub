/**
 * Club class is responsible for doing logical operation for the system. Which contains 2 ArrayLists.
 *
 * @author Jay Pancholi
 * @version 27/04/2018
 */
import java.util.*;
import java.io.*;
import java.time.*;
import static java.time.temporal.ChronoUnit.MINUTES;

public class Club
{
    // instance variables
	private String clubName;
	private ArrayList<Member> memberList;
	private ArrayList<Sport> sportList;
	
    /**
     * Constructor for objects of class Club
     */
    public Club(String name) throws IOException
    {
        
        clubName = name;
        memberList = new ArrayList<>();
        sportList = new ArrayList<>();
        loadFromMember();
        loadFromSports();



    }

    /**
     * toString method for printing value
     *
     * @return    String
     */
    public String toString()
    {
        return "Club name: "+clubName;
    }

//-------------------------------------------- logial part of system------------------------
    /**
     * getSportList method for accessing value
     *
     * @return  sportList
     */
    public ArrayList<Sport> getSportList()
    {
        return sportList;
    }
    /**
     * validateMember method use to validate memeber.
     *
     * @param  int memberId,String sport
     * @return   int
     */
    public int validateMember(int memberId,String sport)
    {   // -1 not found, 0 found but not financial, 1 no eligible for the sport, 2 all good
        boolean foundFlag = false,financialFlag = false;

        for (int i = 0 ; i < memberList.size() ; i++ ) 
        {
            if (memberId == memberList.get(i).getMemberId()) 
            { 
                //System.out.println("member id: "+memberId);
                //System.out.println("fin: "+memberList.get(i).getFinancialStatus());
                foundFlag = true; // 0
                if (memberList.get(i).getFinancialStatus() == true) 
                {

                    financialFlag = true; // 1
                    
                    //System.out.println("sport: "+sportFromMember);
                    //System.out.println(sportFromMember.indexOf(sport));
                    String sportFromMember = memberList.get(i).getSportsPlayed();

                    if (sportFromMember.indexOf(sport)> -1) 
                    {
                        return 2;
                    }
                    else
                    {
                        return 1;
                    }
                }
                else 
                {
                    return 0;    
                }
            }    
        }
        return -1;
    }

    /**
     * getTimeDifference method usses to get the difference between time of specific user (helps for finding remaining time for the day.)
     *
     * @param  int memberId,String sport,LocalDate date
     * @return  int difference of time in minutes
     */
    public int getTimeDifference(int memberId,String sport,LocalDate date)
    {
        
        ArrayList<Booking> bookingList;
        int timeDifference = 0;
        //checks for every sports
        for (int i =0 ;i< sportList.size();i++) 
        {
            if (sport.equalsIgnoreCase(sportList.get(i).getSportName())) 
            {
                //check for every court
                ArrayList<Court> courtList = sportList.get(i).getSportCourtList();
                for (int j = 0;j < courtList.size() ; j++ ) 
                {
                    //check for eveybooking
                    bookingList = courtList.get(j).getBookingList();
                    for (int k = 0 ; k < bookingList.size() ; k++ ) 
                    {
                         LocalDate dateFromObj = bookingList.get(k).getBookingDate();
                         Member memberObj = bookingList.get(k).getMemberObject();
                         // memberid and date mathes it will check for its time
                         if (memberId == memberObj.getMemberId() && date.equals(dateFromObj)) 
                         {
                             LocalTime startTime = bookingList.get(k).getStartTime();
                             LocalTime endTime = bookingList.get(k).getEndTime();
                             int temp = (int)MINUTES.between(startTime,endTime);
                             timeDifference = timeDifference + temp;
                         }
                    }
                }
            }
        }
        //System.out.println("Time diff: "+timeDifference); // debug purpose
        return timeDifference;

    }

    /**
     * validateTime method helps to verify the time like what ever time inserted by the user is in the range or not
     *
     * @param  int memberId,String sport, LocalTime startTime, LocalTime endTime,LocalDate date, int courtId
     * @return    boolean 
     */
    public boolean validateTime(int memberId,String sport, LocalTime startTime, LocalTime endTime,LocalDate date, int courtId)
    {
        ArrayList<Booking> bookingList;
        final LocalTime OPEN = LocalTime.parse("09:00");
        final LocalTime CLOSE = LocalTime.parse("22:00"); 
        // condition check the working hours
        if (((startTime.isAfter(OPEN) || startTime.equals(OPEN)) && (endTime.isAfter(OPEN) || endTime.equals(OPEN))) && ((startTime.isBefore(CLOSE) || startTime.equals(CLOSE)) && (endTime.isBefore(CLOSE) || endTime.equals(CLOSE)))) 
        {
            
            for (int i = 0 ; i < sportList.size() ; i++ ) 
            {
                if (sport.equalsIgnoreCase(sportList.get(i).getSportName())) 
                {
                    
                    ArrayList<Court> courtList = sportList.get(i).getSportCourtList();
                    for (int j = 0;j<courtList.size() ;j++ ) 
                    {
                        if (courtId == courtList.get(j).getCourtId()) 
                        {
                            bookingList = courtList.get(j).getBookingList();
                            for (int k = 0 ; k < bookingList.size() ; k++ ) 
                            {
                                LocalDate dateFromObj = bookingList.get(k).getBookingDate();
                                //Member memberObj = bookingList.get(k).getMemberObject();
                                //memberId == memberObj.getMemberId() && 
                                if (date.equals(dateFromObj)) 
                                {
                                    LocalTime startTimeObj = bookingList.get(k).getStartTime(); 
                                    LocalTime endTimeObj = bookingList.get(k).getEndTime();
                                    
                                    // this condition checks for booked periods
                                    if (!(startTime.isAfter(endTimeObj) || endTime.isBefore(startTimeObj)))
                                    {   
                                        System.out.println(startTime+"is after EndTime obj: "+endTimeObj);
                                        System.out.println(endTime+"is before EndTime obj: "+startTimeObj);
                                        System.out.println("ext");
                                             return false;

                                    }   
                                }
                            }
                        }
                            
                    }
                }    
            }
        }
        else 
        {
            return false;    
        }
        return true;
    }
    /**
     * checkCourtId is used to verify the court id is valid or not
     *
     * @param   String sport,int courtId
     * @return    boolean
     */
    public boolean checkCourtId(String sport,int courtId)
    {
        for (int i=0;i < sportList.size() ; i++ ) 
        {
            if (sport.equalsIgnoreCase(sportList.get(i).getSportName())) 
            {
                ArrayList<Court> courtList = sportList.get(i).getSportCourtList();
                for (int j = 0 ; j< courtList.size() ; j++ ) 
                {
                    if (courtId == courtList.get(j).getCourtId()) 
                    {
                        return true;
                    }       
                }   
            }
        }

        return false;
    }
    /**
     * addBooking to make booking for the member and court.
     *
     * @param  int memberId, String sport,LocalDate date, LocalTime startTime, LocalTime endTime, int courtId
     * @return    boolean
     */
    public boolean addBooking(int memberId, String sport,LocalDate date, LocalTime startTime, LocalTime endTime, int courtId)
    {
        
        Court courtObj;
        
        for (int i = 0;i<sportList.size() ;i++ ) 
        {
            if (sport.equalsIgnoreCase(sportList.get(i).getSportName())) 
                {
                    ArrayList<Court> courtList = sportList.get(i).getSportCourtList();
                    for (int j = 0;j<courtList.size() ;j++ ) 
                    {
                        if (courtId == courtList.get(j).getCourtId()) 
                        {
                            courtObj = courtList.get(j);
                            Member memberObj = memberList.get(searchMemberIndex(memberId));
                            int bookingId = 1000;
                            ArrayList<Booking> bookingList = courtList.get(j).getBookingList();
                            //bookingId = bookingList.get((bookingList.size() - 1)).getBookingId();
                            //bookingId++;
                            courtList.get(j).addBooking(bookingId,date,startTime,endTime,memberObj,courtObj);
                            return true;
                        }
                    }
                }    
        }

        return false;
    }

    /**
     * searchMemberIndex to find index of member in arrayList
     *
     * @param  int memberId 
     * @return    int index
     */
    public int searchMemberIndex(int memberId)
    {
        int index = -1;
        for (int i = 0;i < memberList.size(); i++ ) 
        {
            if (memberId == memberList.get(i).getMemberId()) 
            {
                return i;
            }   
        }
        return index;

    }
    /**
     * removeBooking for removing the existed booking
     *
     * @param  int memberId,String sport, LocalDate date, LocalTime startTime,LocalTime endTime
     * @return    boolean
     */
    public boolean removeBooking(int memberId,String sport, LocalDate date, LocalTime startTime,LocalTime endTime)
    {
        for (int i = 0 ;i < sportList.size() ; i++ ) 
        {
            if (sport.equalsIgnoreCase(sportList.get(i).getSportName())) 
            {
                ArrayList<Court> courtList = sportList.get(i).getSportCourtList();
                for (int j = 0 ;j< courtList.size() ;j++ ) 
                {
                    ArrayList<Booking> bookingList = courtList.get(j).getBookingList();
                    for (int k = 0 ;k<bookingList.size() ;k++ ) 
                    {
                        Member mem = bookingList.get(k).getMemberObject();
                        int memberIdObj = mem.getMemberId();
                        LocalDate dateObj = bookingList.get(k).getBookingDate();
                        LocalTime startTimeObj = bookingList.get(k).getStartTime();
                        LocalTime endTimeObj =bookingList.get(k).getEndTime();
                        if (memberId == memberIdObj && date.equals(dateObj) && startTime.equals(startTimeObj) && endTime.equals(endTimeObj)) 
                        {
                            courtList.get(j).removeBooking(k);
                            return true;        
                        }        
                    }    
                }
            }
        }
        return false;
    }

//-------------------------------------------- Reading and Writing -------------------------------
    /**
     * loadFromSport method will read file Sports.txt and will store in to ArrayList of Sport.
     *
     */
      public void loadFromSports() throws IOException
    {
        // reading from file Sport.txt store lines in readLine ArrayList
        ArrayList<String> readLine = FileUtility.readFromFile("Sports.txt");
        String sportName;
        int usageFees=0;
        int insuranceFees=0;
        // read every index of readLine
        for (int i = 0 ; i < readLine.size() ; i++ ) 
        {
            // first three things are obvious
            String[] line = readLine.get(i).split(",");
            sportName = line[0].toLowerCase();
            usageFees = Integer.parseInt(line[1]);
            insuranceFees =  Integer.parseInt(line[2]);
            sportList.add(new Sport(sportName,usageFees,insuranceFees));

            for (int j = 3 ; j < line.length ; j++ ) 
            {
                // addCourts
                //System.out.println("court: "+line[j]);
                sportList.get(i).addCourt(Integer.parseInt(line[j]));
            }
        }
        
    }
       
    /**
     * loadFromMember method will read file Members.txt and will store in to ArrayList of Member.
     *
     */
    public void loadFromMember() throws IOException
    {
        ArrayList<String> readLine = FileUtility.readFromFile("Members.txt");
        String memberName;
        int memberId;
        String sportsPlayed; 
        boolean isFinancial;
        for (int i = 0 ; i < readLine.size(); i++ ) 
        {
            //there could be 3 possibility 
            //1. there will be 3 values, member name, member id and financial status
            //2. 4 values , forth is sport plyed
            //3. more than 4 values. have 2 or more sports.
            String[] line = readLine.get(i).split(",");
            if (line.length == 3) 
            {
                memberName = line[1];
                memberId = Integer.parseInt(line[0]);
                isFinancial = Boolean.parseBoolean(line[2]);
                memberList.add(new Member(memberName,memberId,"none",isFinancial));
            }
            else if (line.length == 4) 
            {
                memberName = line[1];
                memberId = Integer.parseInt(line[0]);
                isFinancial = Boolean.parseBoolean(line[2]);
                sportsPlayed = line[3];
                memberList.add(new Member(memberName,memberId,sportsPlayed.toLowerCase(),isFinancial));   
            }
            else if (line.length > 4) 
            {
                memberName = line[1];
                memberId = Integer.parseInt(line[0]);
                isFinancial = Boolean.parseBoolean(line[2]);
                sportsPlayed = line[3]+","+line[4];
                memberList.add(new Member(memberName,memberId,sportsPlayed.toLowerCase(),isFinancial));   
            }
            
        }  
        
    }
    /**
     * saveToFile will save the file according to user.
     * @param fileName
     * @return true
     */
    public boolean saveToFile(String fileName) throws IOException
    {
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0 ; i< sportList.size() ; i++ )
        {
            data.add(sportList.get(i).getSportName());
           
            data.add("-------------------------------");
            ArrayList<Court> courtList = sportList.get(i).getSportCourtList();
            for (int j = 0 ; j < courtList.size() ; j++ ) 
            {
                ArrayList<Booking> bookingList = courtList.get(j).getBookingList();
                if (bookingList.size() == 0) 
                {
                    
                }
                else
                {
                    for (int k = 0 ;k < bookingList.size() ; k++ ) 
                    {
                        String temp = bookingList.get(k).toString();
                        
                            data.add(temp);  
                            data.add("-------------------------------");    
                    } 
                }
                  
                
            }
        }
        FileUtility.writeToFile(fileName,data);
        return true;
    }
}   