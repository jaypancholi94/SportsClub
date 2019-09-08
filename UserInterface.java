/**
 * Badminton class as child class
 *
 * @author Jay Pancholi
 * @version 27/04/2018
 */
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.time.*;
import static java.time.temporal.ChronoUnit.MINUTES;

public class UserInterface
{

    private Club sportsClub;
    private Scanner sc = new Scanner(System.in);

    public UserInterface(Club sportsClub)
    {
        this.sportsClub = sportsClub;
    }

    public void run() throws IOException
    {
        while(true)
            switch (menu() )
            {
                    case 1:
                        showAvailableCourts();  
                        break;
                    case 2:
                        makeBooking();
                        break;
                    case 3:
                        showMemberBookings();
                        break;
                     case 4:
                        showCourtBookings();
                        break;
                      case 5:
                        deleteBooking();
                        break;
                    case 6:
                    saveToFile();
                    break;
                     case 7:
                        return;
                     default:
                        System.out.println ( "Invalid option" );
                        break;
            }
    }

    private int menu()
    {
             System.out.println("|-------------------------------------------------|");
             System.out.println("| 1 - Show Available Courts                       |");
             System.out.println("| 2 - Make Booking for Member                     |");
             System.out.println("| 3 - Show Member Bookings                        |");
             System.out.println("| 4 - Show Court Bookings                         |");
             System.out.println("| 5 - Delete Booking                              |");
             System.out.println("| 6 - Save booking to file                        |");
             System.out.println("| 7 - Exit                                        |");
             System.out.println("|-------------------------------------------------|");
             System.out.print("Select your option (enter a selection number): ");
             int option = sc.nextInt();
             sc.nextLine();
             return  option;
     }

     // it will show available courts
    private void showAvailableCourts() 
    {   
        System.out.print("Enter sport name to show Available courts: ");
        String sport = sc.nextLine().trim();
        if (validateSport(sport)== true) 
        {
            String d;
            boolean flagForDate = false;
            LocalDate date;
                try
                {
                    do
                    {
                        System.out.print("Enter date in yyyy-mm-dd format: ");
                        d = sc.nextLine().trim();
                        date = LocalDate.parse(d);    
                
                    if (d.indexOf('-') == 4 && d.indexOf('-', 5) == 7) // partial date validation
                    {
                        LocalDate Current = LocalDate.now();
                        if (date.isAfter(Current) || date.equals(Current)) //partial date validation
                        {
                            flagForDate = true;    
                        }
                        else 
                        {
                            System.out.println("-----------------------------------------");
                            System.out.println("Error: Invalid date(accept today's date onwards)."); 
                            System.out.println("-----------------------------------------");          
                        }
                            
                    }
                    else
                    {
                        System.out.println("-----------------------------------------");
                        System.out.println("Error: Invalid date format.");
                        System.out.println("-----------------------------------------");    
                    }

                    }while(flagForDate == false);
                    checkAvailableCourt(sport,date);
                }
            catch (Exception e)
                {
                    System.out.println("-----------------------------------------");
                    System.out.println("Error: follow date formate.");
                    System.out.println("-----------------------------------------");
                }  
        }
        else
        {
            System.out.println("-----------------------------------------");
            System.out.println("Error: Invalid sport.");
            
        }
      
        
    }
    
    // make booking will add booking to booking list if user fullfilled all the precondition
    private void makeBooking()
    {  
        try
        {
            System.out.print("Enter sport name a member wish to play: ");
            String sport = sc.nextLine().trim();
            if (validateSport(sport) == true) 
            {
                String d;
                boolean flagForDate = false,flagForAvailability = false;
                LocalDate date;
                LocalTime startClubTime = LocalTime.parse("09:00");
                LocalTime endClubTime = LocalTime.parse("22:00");
                LocalTime startTime,endTime;
                int bookedTime,timeLimit = 0,courtId;

                do
                {
                    System.out.print("Enter date in yyyy-mm-dd format: ");
                    d = sc.nextLine().trim();
                    date = LocalDate.parse(d);
                    if (d.indexOf('-') == 4 && d.indexOf('-', 5) == 7) 
                    {
                        LocalDate Current = LocalDate.now();
                        LocalDate day7 = Current.plusDays(7);
                        if ((date.isAfter(Current) || date.equals(Current)) && (date.isBefore(day7) || date.equals(day7)))
                        {
                                flagForDate = true;
                        }
                        else 
                        {
                            System.out.println("-----------------------------------------");
                            System.out.println("Error: Invalid date(Member can book up to 7 days).");           
                            System.out.println("-----------------------------------------");
                        }
                            
                    }
                    else
                    {
                        System.out.println("Error: Invalid date format.");    
                    }
                }while(flagForDate == false);
                if (checkAvailableCourt(sport,date) == true) 
                {
                    System.out.println("=========================================");
                    System.out.print("Please enter Member ID: ");
                    int memberId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("-----------------------------------------");
                    int responce = sportsClub.validateMember(memberId,sport);
                    //System.out.println(">>>>>> responce: "+responce);
                    if (responce == -1) 
                    {
                        System.out.println("-----------------------------------------");
                        System.out.println("Error: Member not found.");
                        System.out.println("-----------------------------------------");
                    }
                    else if (responce == 0) 
                    {
                        System.out.println("-----------------------------------------");
                        System.out.println("Error: Member did not paid the fees.");
                        System.out.println("-----------------------------------------");
                    }
                    else if (responce == 1) 
                    {   
                        System.out.println("-----------------------------------------");
                        System.out.println("Error: Member enrolled for different sport.");
                        System.out.println("-----------------------------------------");
                    }
                    else if (responce == 2) 
                    {
                        System.out.println("*** Member's details verified ***");
                        System.out.println("-----------------------------------------");
                        bookedTime = sportsClub.getTimeDifference(memberId,sport,date);
                        
                        if (sport.equalsIgnoreCase("basketball")) 
                        {
                            timeLimit = 180 - bookedTime;
                        }
                        else if (sport.equalsIgnoreCase("badminton")) 
                        {
                            timeLimit = 120 - bookedTime;
                        }
                        
                        String sTime,eTime;
                        if (timeLimit == 0) 
                        {
                            System.out.println("-----------------------------------------");
                            System.out.println("Error: Member reaches his daily booking limit");
                            System.out.println("-----------------------------------------");

                        }
                        else 
                        {
                            System.out.print("Enter Court number: ");
                            courtId = sc.nextInt();
                            sc.nextLine();
                            boolean courtCheck = sportsClub.checkCourtId(sport,courtId);
                            System.out.println("------------------------------------------------------------------");
                            System.out.println("Remaining time left for the date "+date+" is "+timeLimit+" minutes");
                            System.out.println("------------------------------------------------------------------");
                            
                            if (courtCheck == true) 
                            {
                                do
                                {
                                    System.out.print("Enter booking start time(hh:mm): ");
                                    sTime = sc.nextLine().trim();
                                    System.out.print("Enter booking end time(hh:mm): ");
                                    eTime = sc.nextLine().trim();
                    
                                }while(sTime.length() != 5 && eTime.length() != 5);
                                startTime = LocalTime.parse(sTime);
                                endTime = LocalTime.parse(eTime);
                                int tempDiff = (int) MINUTES.between(startTime,endTime);

                                if (endTime.isAfter(startTime)) 
                                {
                                    if (tempDiff <= timeLimit) 
                                    {
                                        boolean validation = sportsClub.validateTime(memberId,sport,startTime,endTime,date,courtId);
                                        if (validation == true) 
                                        {
                                            boolean book = sportsClub.addBooking(memberId,sport,date,startTime,endTime,courtId);
                                            if (book == true) 
                                            {
                                                System.out.println("-----------------------------------------");
                                                System.out.println("Success: Booking sucessfull");  
                                                System.out.println("-----------------------------------------");
                                            }
                                        }
                                        else
                                        {
                                            System.out.println("-----------------------------------------");
                                            System.out.println("Error: Please enter valid time slot from above list.");
                                            System.out.println("-----------------------------------------");
                                        }
                                    }
                                    else 
                                    {
                                        System.out.println("-----------------------------------------");
                                        System.out.println("Error: Member can book upto given time.");  
                                        System.out.println("-----------------------------------------");  
                                    }
                                    
                                }
                                else
                                {
                                    System.out.println("-----------------------------------------");
                                    System.out.println("Error: End time should greater than start time");
                                    System.out.println("-----------------------------------------");
                                }
                            }
                            else 
                            {
                                System.out.println("-----------------------------------------");
                                System.out.println("Error: Invalid court.");    
                                System.out.println("-----------------------------------------");
                            } 
                        }
                       // startTime = LocalTime.parse()
                        
                        
                    }
                }
                else
                {
                    System.out.println("-----------------------------------------");
                    System.out.println("Error: Invalid sport");
                    System.out.println("-----------------------------------------");
                }
            }
            else
            {
                System.out.println("-----------------------------------------");
                System.out.println("Error: Invalid sport.");
            }
            
        }
        catch (Exception e) 
        {
            System.out.println("-----------------------------------------");
            System.out.println("Error: follow time and date format");       
            System.out.println("-----------------------------------------"); 
        }    
         
    }
    // showMemberBookings will show booking of specific member
    public void showMemberBookings()
    {
        System.out.print("Enter Member Id: ");
        int memberId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter sport: ");
        String sport = sc.nextLine().trim(); 
        
        int responce = sportsClub.validateMember(memberId,sport);
            if (responce == -1) 
            {
                System.out.println("-----------------------------------------");
                System.out.println("Error: Member not found.");
                System.out.println("-----------------------------------------");
            }
            else if (responce == 0) 
            {
                System.out.println("-----------------------------------------");
                System.out.println("Error: Member did not paid the fees.");
                System.out.println("-----------------------------------------");
            }
            else if (responce == 1) 
            {
                System.out.println("-----------------------------------------");
                System.out.println("Error: Member enrolled for different sport.");
                System.out.println("-----------------------------------------");
            }
            else if (responce == 2) 
            {
                showMemberBookings(memberId,sport);
            }

    }

    // this method will show booking related to specific court
    private void showCourtBookings()
    {
        System.out.print("Enter sport name: ");
        String sport = sc.nextLine().trim();
        System.out.print("Enter court number: ");
        int courtId = sc.nextInt();
        sc.nextLine();
        boolean sportCheck = validateSport(sport);
        if (sportCheck == true) 
        {
            boolean checkCourtId = sportsClub.checkCourtId(sport,courtId);
            if (checkCourtId == true) 
            {
                showCourtBookings(sport,courtId);
            }
            else 
            {
                System.out.println("-----------------------------------------");
                System.out.println("Error: Invalid court number");    
                System.out.println("-----------------------------------------");
            }
        }
        else 
        {
            System.out.println("-----------------------------------------");
            System.out.println("Error: Invalid sport");    
            System.out.println("-----------------------------------------");
        }
        
    }

    // it will delete booking which user demanded if the it fullfilled preconditions
    private void deleteBooking()
    {
        try
        {
            System.out.print("Enter Member number: ");
            int memberId = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Sport: ");
            String sport = sc.nextLine().trim();
            String sTime,eTime,d;
            LocalDate date;
            LocalTime startTime,endTime;
            boolean flag = false;
            int responce = sportsClub.validateMember(memberId,sport);
                if (responce == -1) 
                {
                    System.out.println("-----------------------------------------");
                    System.out.println("Error: Member not found.");
                    System.out.println("-----------------------------------------");
                }
                else if (responce == 0) 
                {
                    System.out.println("-----------------------------------------");
                    System.out.println("Error: Member did not paid the fees.");
                    System.out.println("-----------------------------------------");
                }
                else if (responce == 1) 
                {
                    System.out.println("-----------------------------------------");
                    System.out.println("Error: Member enrolled for different sport.");
                    System.out.println("-----------------------------------------");
                }
                else if (responce == 2) 
                {
                    do
                    {
                        System.out.print("Enter booking start time(hh:mm): ");
                        sTime = sc.nextLine();
                        System.out.print("Enter booking end time(hh:mm): ");
                        eTime = sc.nextLine();
                        do
                        {
                            System.out.print("Enter date in yyyy-mm-dd format: ");
                            d = sc.nextLine().trim();
                            date = LocalDate.parse(d);
                            if (d.indexOf('-') == 4 && d.indexOf('-', 5) == 7) 
                            {
                                flag = true;
                            }   
                        }while(flag == false);
                    }while(sTime.length() != 5 && eTime.length() != 5);
                    startTime = LocalTime.parse(sTime);
                    endTime= LocalTime.parse(eTime);

                    boolean remove = sportsClub.removeBooking(memberId,sport,date,startTime,endTime);
                    if (remove == true) 
                    {
                        System.out.println("-----------------------------------------");
                        System.out.println("Success: "+memberId+"'s booking removed."); 
                        System.out.println("-----------------------------------------");  
                    }
                    else
                    {
                        System.out.println("-----------------------------------------");
                        System.out.println("Error: Not found the given details");
                        System.out.println("-----------------------------------------");
                    }
                }
        }
        catch (Exception e) 
        {
            System.out.println("-----------------------------------------");
            System.out.println("Error: follow time and date format");    
            System.out.println("-----------------------------------------");
        }
        
    }
    // save bookings to the file
    public void saveToFile() throws IOException
    {
        System.out.println("It will save all the booking list to textFile");
        System.out.println("-----------------------------------------");
        System.out.print("Enter file name with extention: ");
        String fileName = sc.nextLine();
        System.out.println("-----------------------------------------");
            if(sportsClub.saveToFile(fileName) == true)
            {
                System.out.println("-----------------------------------------");
                System.out.println("Success: File saved.");
                System.out.println("-----------------------------------------");
            }
    }
//-------------------------------------------------------------------------------------------
    /**
     * checkAvailableCourt will print available court
     *
     * @param   String sport,LocalDate date
     * @return    boolean 
     */
    public boolean checkAvailableCourt(String sport,LocalDate date)
    {
        boolean flagForAvailability = false;
        LocalTime startClubTime = LocalTime.parse("09:00");
        LocalTime endClubTime = LocalTime.parse("22:00");

        ArrayList<Sport> sportList=sportsClub.getSportList();
        for (int i = 0;i < sportList.size(); i++) 
        {

            System.out.println("=========================================");
            if (sportList.get(i).getSportName().equalsIgnoreCase(sport)) 
            {
                flagForAvailability = true;
                System.out.println("Sport: "+sportList.get(i).getSportName());    
                System.out.println("=========================================");
                ArrayList<Court> courtList = sportList.get(i).getSportCourtList();
                System.out.println("Available courts: ");
                System.out.println("-----------------------------------------");
                for (int j = 0 ; j < courtList.size() ; j++ ) 
                {
                    ArrayList<Booking> courtBooking = courtList.get(j).getBookingList();
                    if (courtBooking.size() == 0) 
                    {
                        System.out.println("Court number: "+courtList.get(j).getCourtId());    
                        System.out.println("Available for "+date+" and timings "+startClubTime+" to "+endClubTime+".");
                        
                    }else
                    {
                        System.out.println("Court number: "+courtList.get(j).getCourtId());
                        System.out.println("Available for "+date+" and timings "+startClubTime+" to "+endClubTime+".");
                        for (int k = 0 ; k < courtBooking.size() ; k++ ) 
                        {
                            LocalDate fromBooking = courtBooking.get(k).getBookingDate();
                            if (fromBooking.equals(date)) 
                            {
                                
                                System.out.println(">>> Except "+courtBooking.get(k).getStartTime()+" to "+courtBooking.get(k).getEndTime());

                            }
                        }
                        
                    }
                    System.out.println("-----------------------------------------");
                    //System.out.println(courtList.get(j).getCourtId());
                } 

            }
               
        }
        if (flagForAvailability == false) 
        {
            return false;
        }
        return true;
    }
    /**
     * showMemberBookings will print member booking list
     *
     * @param   int memberId, String sport
     */
    public void showMemberBookings(int memberId, String sport)
    {
        boolean found = false;
        ArrayList<Sport> sportList = sportsClub.getSportList();
        for (int i = 0;i<sportList.size() ;i++ ) 
        {
            int courtId = 0;
            LocalDate bookedDate = LocalDate.now();
            LocalTime startTime = LocalTime.now(),endTime= LocalTime.now();
            if (sport.equalsIgnoreCase(sportList.get(i).getSportName())) 
            {
                ArrayList<Court> courtList = sportList.get(i).getSportCourtList();
                for (int j =0 ;j<courtList.size() ;j++ ) 
                {
                    courtId = courtList.get(j).getCourtId();
                    ArrayList<Booking> bookingList = courtList.get(j).getBookingList();
                    for (int k=0;k<bookingList.size() ;k++ ) 
                    {
                        Member memberObj = bookingList.get(k).getMemberObject();
                        //System.out.println(memberObj.getMemberId());
                        if (memberId == memberObj.getMemberId()) 
                        {
                            bookedDate = bookingList.get(k).getBookingDate();
                            startTime = bookingList.get(k).getStartTime();
                            endTime = bookingList.get(k).getEndTime();
                            System.out.println("Court: "+courtId+", Booked "+startTime+" to "+endTime+" on "+bookedDate+" for "+sportList.get(i).getSportName());
                            found = true;
                        }    
                    }
                }
            }    
        }

        if (found == false) 
        {
            System.out.println("No booking found.");    
        }
    }
    /**
     * showCourtBookings will print court booking list
     *
     * @param   String sport,int courtId
     */
    public void showCourtBookings(String sport,int courtId)
    {
        boolean flag = false;
        ArrayList<Sport> sportList = sportsClub.getSportList();
        for (int i = 0;i<sportList.size() ;i++ ) 
        {
            
            if (sport.equalsIgnoreCase(sportList.get(i).getSportName())) 
            {
                ArrayList<Court> courtList = sportList.get(i).getSportCourtList();
                for (int j =0 ;j<courtList.size() ;j++ ) 
                {
                    if (courtId == courtList.get(j).getCourtId()) 
                    {
                        ArrayList<Booking> bookingList = courtList.get(j).getBookingList();
                        if (bookingList.size() == 0) 
                        {
                            flag = true;
                        }
                        else 
                        {   System.out.println("Court "+courtId);
                            System.out.println("---------------");
                            for (int k=0;k<bookingList.size() ;k++ ) 
                            {
                                LocalDate bookedDate = bookingList.get(k).getBookingDate();
                                LocalTime startTime = bookingList.get(k).getStartTime();
                                LocalTime endTime = bookingList.get(k).getEndTime();                               
                                System.out.println("booked "+startTime+" to "+endTime+" on "+bookedDate);
                                
                            }
                        }
                          
                    }
                }
            }
        }
        if (flag == true) 
        {
            System.out.println("No booking at all");    
        }
    }
    /**
     * validateSport will validate the given sport exists or not
     *
     * @param  String sport
     * @return    boolean
     */
    public boolean validateSport(String sport)
    {
        ArrayList<Sport> sportList = sportsClub.getSportList();
        for (int i = 0;i<sportList.size() ;i++ ) 
        {
            if (sport.equalsIgnoreCase(sportList.get(i).getSportName())) 
            {
                return true;    
            }
        }
        return false;
    }
} // end class
