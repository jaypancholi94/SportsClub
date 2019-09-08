/**
 * Booking class which uses interface for sorting, and this class is also used for make booking of court and member.
 *
 * @author Jay Pancholi
 * @version 27/04/2018
 */
import java.time.*;


public class Booking implements Comparable<Booking> 
{
    // instance varible
	private int bookingId;
	private LocalDate bookingDate;
	private LocalTime startTime;
	private LocalTime endTime;
	private Member bookedBy;
	private Court bookedFor;
	
    /**
     * Constructor for objects of class Booking
     */
    public Booking(int bookingId, LocalDate bookingDate, LocalTime startTime, LocalTime endTime, Member bookedBy, Court bookedFor)
    {
    	this.bookingId = bookingId;
    	this.bookingDate = bookingDate;
    	this.startTime = startTime;
    	this.endTime = endTime;
    	this.bookedBy = bookedBy;
    	this.bookedFor = bookedFor;
    }
    /**
     * getBookingId method for accessing value of booking id
     *
     * @return  int bookingId
     */
    public int getBookingId()
    {
        return bookingId;
    }
    /**
     * getStartTime method for accessing value of startTime object
     *
     * @return  LocalDate startTime
     */
    public LocalTime getStartTime()
    {
    	return startTime;
    }
    /**
     * getEndTime method for accessing value of endTime object
     *
     * @return  LocalTime endTime
     */
    public LocalTime getEndTime()
    {
    	return endTime;
    }
    /**
     * getBookingDate method for accessing value of booking date
     *
     * @return  LocalDate bookingDate
     */
    public LocalDate getBookingDate()
    {
    	return bookingDate;
    }
    /**
     * getMemberObject method for accessing value of bookedBy
     *
     * @return  Member bookedBy
     */
   	public Member getMemberObject()
   	{
   		return bookedBy;
   	}
    /**
     * getCourtObject method for accessing value of bookedFor
     *
     * @return  Court bookedFor
     */
    public Court getCourtObject()
    {
        return bookedFor;
    }
    /**
     * toString method for printing value
     *
     * @return    String
     */
    public String toString()
    {
        return "Booking ID: "+bookingId+", Date: "+bookingDate+", Time: "+startTime+" to "+endTime+", Booked by: "+bookedBy.getMemberName()+"("+bookedBy.getMemberId()+"), Court ID: "+bookedFor.getCourtId();
        //return "Booking ID: "+bookingId+"\nDate: "+bookingDate+"\nTime: "+startTime+" to "+endTime+"\nBooked by: "+bookedBys;
    }

	public int compareTo (Booking b1)
    {
    	if (this.startTime.equals(b1.startTime)) 
    	{
    		return 0;
    	}
    	else if (this.startTime.isAfter(b1.startTime))
    	{
    		return 1;
    	}
    	return -1;
    	
    } 
}
