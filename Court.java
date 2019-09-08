/**
 * Court class as for passing values of other class and managing sorting
 *
 * @author Jay Pancholi
 * @version 27/04/2018
 */
import java.util.*;
import java.time.*;

public class Court
{
    // instance variables
    private ArrayList<Booking> courtBookings;
   	private  int courtId;
    /**
     * Constructor for objects of class Court
     */
    public Court(int courtId)
    {
        this.courtId = courtId;
        courtBookings = new ArrayList<>();
        //courtBookings.add(new Booking(1,LocalDate.parse("2018-04-24"),LocalTime.parse("15:00"),LocalTime.parse("17:00"),"Jay"));
        //courtBookings.add(new Booking(2,LocalDate.parse("2018-04-24"),LocalTime.parse("09:00"),LocalTime.parse("10:00"),"Rujuta"));
        //courtBookings.add(new Booking(2,LocalDate.parse("2018-04-24"),LocalTime.parse("11:00"),LocalTime.parse("12:00"),"Raj"));
        //Collections.sort(courtBookings);
    }
    /**
     * getCourtId method for accessing value
     *
     * @return  courtId
     */
    public int getCourtId()
    {
        return courtId;
    }
    /**
     * toString method for printing value
     *
     * @return    String
     */
    public String toString()
    {   
        String temp = "heyo";
        if (courtBookings.size() == 0) 
        {
            temp = "Court ID: "+courtId+"\n No booking.";
        }
        return temp;
    }
    /**
     * addBooking to make booking for the member and court.
     *
     * @param  int memberId, String sport,LocalDate date, LocalTime startTime, LocalTime endTime, int courtId
     * @return    boolean
     */
    public boolean addBooking(int bookingId, LocalDate bookingDate, LocalTime startTime, LocalTime endTime, Member bookedBy, Court bookedFor)
    {
        //checks the previous id
        if (courtBookings.size() == 0) 
        {
            bookingId = 1;
        }
        else 
        {
            bookingId = courtBookings.get(courtBookings.size()-1).getBookingId();
            bookingId++;
        }
        
        courtBookings.add(new Booking(bookingId,bookingDate,startTime,endTime,bookedBy,bookedFor));
        Collections.sort(courtBookings);
        return true;
    }
    /**
     * remove booking from the booking list
     *
     * @param  int index
     * @return    boolean
     */
    public void removeBooking(int index)
    {

        courtBookings.remove(index);
        Collections.sort(courtBookings);
    }
    // Methods------------------------------------------
    /**
     * getBookingList for accessing the bookingList
     *
     * @return  courtBookings
     */
    public ArrayList<Booking> getBookingList()
    {
        return courtBookings;
    }  

}
