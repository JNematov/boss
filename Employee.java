import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Employee implements Serializable{

	//Attributes
	String name;
	HashMap<String, ArrayList<Double>> empInfo = new HashMap<String, ArrayList<Double>>();
	
	
	//constructor
	public Employee(String name) {
		this.name = name;
	}
	
	//setters
	public void setName(String name) {
		this.name = name;
	}
	
	public void setTips(String date, double newtips) {
		empInfo.get(date).set(0, newtips);
	}
	
	public void setHours(String date, double newhours) {
		empInfo.get(date).set(1, newhours);
	}
	
	public void setEarnings(String date, double hours, double wage) {
		empInfo.get(date).set(2, (wage*hours));
	}
	
	public void setEarnings(String date, double newearnings) {
		empInfo.get(date).set(2, newearnings);
	}
	
	//getters
	public String getName() {
		return name;
	}

	public double getTips(String date) {
		return empInfo.get(date).get(0);
	}
	
	public double getHours(String date) {
		return empInfo.get(date).get(1);
	}
	
	public double getEarnings(String date) {
		return empInfo.get(date).get(2);
	}
	
	//others
	static public String getWeek() {
		LocalDate today = LocalDate.now();
		DayOfWeek dayOfWeek = DayOfWeek.from(today); 
		int todayValue = dayOfWeek.getValue();
		LocalDate thisWeekMonday;
		
		switch(todayValue) {
			case 1: 
				thisWeekMonday = today;
				break;
			case 2:
				thisWeekMonday = today.minusDays(1);
				break;
			case 3:
				thisWeekMonday = today.minusDays(2);
				break;
			case 4:
				thisWeekMonday = today.minusDays(3);
				break;
			case 5:
				thisWeekMonday = today.minusDays(4);
				break;
			case 6:
				thisWeekMonday = today.minusDays(5);
				break;
			default:
				thisWeekMonday = today.minusDays(6);
		}
		
		LocalDate thisWeekSunday = thisWeekMonday.plusDays(6);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/YYYY");
		String formattedThisWeekMon = dtf.format(thisWeekMonday);
		String formattedThisWeekSun = dtf.format(thisWeekSunday);
		
		return formattedThisWeekMon+" - "+formattedThisWeekSun;
		
		}
	
	
}
