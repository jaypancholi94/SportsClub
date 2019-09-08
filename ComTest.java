import java.util.*;
import java.time.*;
class ComTest 
{
	public static void main(String[] args) 
	{
		ArrayList<Booking> courtBookings = new ArrayList<>();
		LocalDate date = LocalDate.now();
		LocalTime time1s = LocalTime.parse("15:00");
		LocalTime time1e = LocalTime.parse("17:00");
		
		LocalTime time2s = LocalTime.parse("09:00");
		LocalTime time2e = LocalTime.parse("10:00");
		courtBookings.add(new Booking(1,date,time2s,time2e,"rujuta"));
		courtBookings.add(new Booking(2,LocalDate.parse("2018-10-10"),time2s,time2e,"Jay"));
		LocalTime time3s = LocalTime.parse("11:00");
		LocalTime time3e = LocalTime.parse("12:00");
		courtBookings.add(new Booking(3,date,time3s,time3e,"Raj"));

		Collections.sort(courtBookings);
		for (int i = 0;i< courtBookings.size() ;i++ ) 
		{
			System.out.println(courtBookings.get(i));	
		}

	}	
}