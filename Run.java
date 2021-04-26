import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Run {

	public static void main(String[] args){
		
		//Attributes
		Scanner inputReader = new Scanner(System.in);
		
		System.out.println("Would you like to view or input or clear data?(view/input/clear)");
		String viewOrInput = inputReader.next();
		System.out.println("What is the name of the employee that you will access( or create)?");
		String targetEmpName = inputReader.next();
		switch(viewOrInput) {
			case "input":
				System.out.println("Are you inputting for a new or existing employee?(new/existing)");
				String newOrExisting = inputReader.next();
				switch(newOrExisting) {
						case "new":
						try {
							
							Employee newEmp = new Employee(targetEmpName);
							ArrayList<ArrayList<Double>> arrayList = new ArrayList<ArrayList<Double>>();							
							
							ArrayList<Double> defaultInfo1 = new ArrayList<Double>();
							ArrayList<Double> defaultInfo2 = new ArrayList<Double>();
							ArrayList<Double> defaultInfo3 = new ArrayList<Double>();
							ArrayList<Double> defaultInfo4 = new ArrayList<Double>();
							ArrayList<Double> defaultInfo5 = new ArrayList<Double>();
							ArrayList<Double> defaultInfo6 = new ArrayList<Double>();
							ArrayList<Double> defaultInfo7 = new ArrayList<Double>();
							
							arrayList.add(defaultInfo1);
							arrayList.add(defaultInfo2);
							arrayList.add(defaultInfo3);
							arrayList.add(defaultInfo4);
							arrayList.add(defaultInfo5);
							arrayList.add(defaultInfo6);
							arrayList.add(defaultInfo7);

							for(int b=0; b<arrayList.size(); b++ ) {
								for(int c=0; c<3; c++) {
								arrayList.get(b).add(0.0);
								}
							}
							
							
							for(int a=1; a<8; a++) { //for each date, set the info to 0.0
								newEmp.empInfo.put(getDateOfDayOfWeek(a), arrayList.get(a-1));
								newEmp.setTips(getDateOfDayOfWeek(a), arrayList.get(a-1).get(0));
								newEmp.setHours(getDateOfDayOfWeek(a), arrayList.get(a-1).get(1));
								newEmp.setEarnings(getDateOfDayOfWeek(a), arrayList.get(a-1).get(2));
								System.out.print(newEmp.getTips(getDateOfDayOfWeek(a))+"\t"+newEmp.getHours(getDateOfDayOfWeek(a))+"\t"+newEmp.getEarnings(getDateOfDayOfWeek(a))+"\n");

							
							}
							//write to file
						FileOutputStream fos = new FileOutputStream(targetEmpName+".bin");
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						
						oos.writeObject(newEmp);
					} catch (IOException e) {
						e.printStackTrace();
					}	
							;
							break;
						case "existing":
							System.out.println("Which data would you like to edit?(tips/ hours/ earnings)");
							String targetField = inputReader.next();
							System.out.println("For which date would you like to edit the data? (mm/dd/yyyy)");
							String targetDate = inputReader.next();
							
							Employee emp;
							try {//read from file
								FileInputStream fis = new FileInputStream("C:\\Users\\Jakho\\.eclipse\\IB DRAFT\\"+targetEmpName+".bin");
								ObjectInputStream ois = new ObjectInputStream(fis);
								emp = (Employee) ois.readObject();
								
								FileOutputStream fos = new FileOutputStream(targetEmpName+".bin");
								ObjectOutputStream oos = new ObjectOutputStream(fos);
								
								ArrayList<Double> existingArray1 = emp.empInfo.get(getDateOfDayOfWeek(1));
								ArrayList<Double> existingArray2 = emp.empInfo.get(getDateOfDayOfWeek(2));
								ArrayList<Double> existingArray3 = emp.empInfo.get(getDateOfDayOfWeek(3));
								ArrayList<Double> existingArray4 = emp.empInfo.get(getDateOfDayOfWeek(4));
								ArrayList<Double> existingArray5 = emp.empInfo.get(getDateOfDayOfWeek(5));
								ArrayList<Double> existingArray6 = emp.empInfo.get(getDateOfDayOfWeek(6));
								ArrayList<Double> existingArray7 = emp.empInfo.get(getDateOfDayOfWeek(7));

								
								switch(targetField){
								case "tips":
									System.out.println("What will the new tip value be?");
									double newTip = inputReader.nextDouble();
									emp.setTips(targetDate, newTip);
									oos.writeObject(emp);
									break;
								case "hours":
									System.out.println("What will the new hours value be?");
									double newHours = inputReader.nextDouble();
									emp.setHours(targetDate, newHours);
									oos.writeObject(emp);
									break;
								case "earnings":
									System.out.println("What will the new earnings value be?");
									double newEarnings = inputReader.nextDouble();
									emp.setEarnings(targetDate, newEarnings);
									break;
								default:
									System.out.println("restart and answer previous question with tips, hours, or earnings");
									break;
								}
							break;
							}catch(IOException | ClassNotFoundException e) {
								e.printStackTrace();
							}
						break;
						default:
							System.out.println("Error: Please restart and answer previous querstion with new or eixsting");
					}
				break;
			case "view":
				Employee emp;
				try {
					FileInputStream fis = new FileInputStream("C:\\Users\\Jakho\\.eclipse\\IB DRAFT\\"+targetEmpName+".bin");
					ObjectInputStream ois = new ObjectInputStream(fis);
					emp = (Employee) ois.readObject();
					System.out.println(emp.getName()+"\t");
					for(int a=1; a<8; a++) {
						System.out.print(getDateOfDayOfWeek(a)+"\t"+emp.empInfo.get((getDateOfDayOfWeek(a)))+"\n");
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
				break;
			case "clear":
			try {
				new FileOutputStream("C:\\Users\\Jakho\\.eclipse\\IB DRAFT\\"+targetEmpName+".bin").close();
				File file = new File("C:\\Users\\Jakho\\.eclipse\\IB DRAFT\\"+targetEmpName+".bin");
				file.delete();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
			default:
				System.out.println("Error: Please restart and answer first question with view or input or clear");
				break;
		}
	}
	public static String getDateOfDayOfWeek(int num1to7) {
		LocalDate today = LocalDate.now();
		DayOfWeek dow = DayOfWeek.from(today);
		int todayValue = dow.getValue();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/YYYY");
		LocalDate dateOfDayOfWeek;
		
		switch(num1to7) {
		case 1: 
			switch(todayValue){
				case 1:
					dateOfDayOfWeek = today.minusDays(0);
					return dtf.format(dateOfDayOfWeek);
				case 2:
					dateOfDayOfWeek = today.minusDays(1);
					return dtf.format(dateOfDayOfWeek);
				case 3:
					dateOfDayOfWeek = today.minusDays(2);
					return dtf.format(dateOfDayOfWeek);
				case 4:
					dateOfDayOfWeek = today.minusDays(3);
					return dtf.format(dateOfDayOfWeek);
				case 5:
					dateOfDayOfWeek = today.minusDays(4);
					return dtf.format(dateOfDayOfWeek);
				case 6:
					dateOfDayOfWeek = today.minusDays(5);
					return dtf.format(dateOfDayOfWeek);
				default:
					dateOfDayOfWeek = today.minusDays(6);
					return dtf.format(dateOfDayOfWeek);
			}
		case 2:
			switch(todayValue){
			case 1:
				dateOfDayOfWeek = today.plusDays(1);
				return dtf.format(dateOfDayOfWeek);
			case 2:
				dateOfDayOfWeek = today.plusDays(0);
				return dtf.format(dateOfDayOfWeek);
			case 3:
				dateOfDayOfWeek = today.minusDays(1);
				return dtf.format(dateOfDayOfWeek);
			case 4:
				dateOfDayOfWeek = today.minusDays(2);
				return dtf.format(dateOfDayOfWeek);
			case 5:
				dateOfDayOfWeek = today.minusDays(3);
				return dtf.format(dateOfDayOfWeek);
			case 6:
				dateOfDayOfWeek = today.minusDays(4);
				return dtf.format(dateOfDayOfWeek);
			default:
				dateOfDayOfWeek = today.minusDays(5);
				return dtf.format(dateOfDayOfWeek);
		}
		case 3:
			switch(todayValue){
			case 1:
				dateOfDayOfWeek = today.plusDays(2);
				return dtf.format(dateOfDayOfWeek);
			case 2:
				dateOfDayOfWeek = today.plusDays(1);
				return dtf.format(dateOfDayOfWeek);
			case 3:
				dateOfDayOfWeek = today.minusDays(0);
				return dtf.format(dateOfDayOfWeek);
			case 4:
				dateOfDayOfWeek = today.minusDays(1);
				return dtf.format(dateOfDayOfWeek);
			case 5:
				dateOfDayOfWeek = today.minusDays(2);
				return dtf.format(dateOfDayOfWeek);
			case 6:
				dateOfDayOfWeek = today.minusDays(3);
				return dtf.format(dateOfDayOfWeek);
			default:
				dateOfDayOfWeek = today.minusDays(4);
				return dtf.format(dateOfDayOfWeek);
			}
		case 4:
			switch(todayValue){
			case 1:
				dateOfDayOfWeek = today.plusDays(3);
				return dtf.format(dateOfDayOfWeek);
			case 2:
				dateOfDayOfWeek = today.plusDays(2);
				return dtf.format(dateOfDayOfWeek);
			case 3:
				dateOfDayOfWeek = today.plusDays(1);
				return dtf.format(dateOfDayOfWeek);
			case 4:
				dateOfDayOfWeek = today.minusDays(0);
				return dtf.format(dateOfDayOfWeek);
			case 5:
				dateOfDayOfWeek = today.minusDays(1);
				return dtf.format(dateOfDayOfWeek);
			case 6:
				dateOfDayOfWeek = today.minusDays(2);
				return dtf.format(dateOfDayOfWeek);
			default:
				dateOfDayOfWeek = today.minusDays(3);
				return dtf.format(dateOfDayOfWeek);
			}
		case 5:
			switch(todayValue){
			case 1:
				dateOfDayOfWeek = today.plusDays(4);
				return dtf.format(dateOfDayOfWeek);
			case 2:
				dateOfDayOfWeek = today.plusDays(3);
				return dtf.format(dateOfDayOfWeek);
			case 3:
				dateOfDayOfWeek = today.plusDays(2);
				return dtf.format(dateOfDayOfWeek);
			case 4:
				dateOfDayOfWeek = today.plusDays(1);
				return dtf.format(dateOfDayOfWeek);
			case 5:
				dateOfDayOfWeek = today.minusDays(0);
				return dtf.format(dateOfDayOfWeek);
			case 6:
				dateOfDayOfWeek = today.minusDays(1);
				return dtf.format(dateOfDayOfWeek);
			default:
				dateOfDayOfWeek = today.minusDays(2);
				return dtf.format(dateOfDayOfWeek);
			}
		case 6:
			switch(todayValue){
			case 1:
				dateOfDayOfWeek = today.plusDays(5);
				return dtf.format(dateOfDayOfWeek);
			case 2:
				dateOfDayOfWeek = today.plusDays(4);
				return dtf.format(dateOfDayOfWeek);
			case 3:
				dateOfDayOfWeek = today.plusDays(3);
				return dtf.format(dateOfDayOfWeek);
			case 4:
				dateOfDayOfWeek = today.plusDays(2);
				return dtf.format(dateOfDayOfWeek);
			case 5:
				dateOfDayOfWeek = today.plusDays(1);
				return dtf.format(dateOfDayOfWeek);
			case 6:
				dateOfDayOfWeek = today.minusDays(0);
				return dtf.format(dateOfDayOfWeek);
			default:
				dateOfDayOfWeek = today.minusDays(1);
				return dtf.format(dateOfDayOfWeek);
			}
		default:
			switch(todayValue){
			case 1:
				dateOfDayOfWeek = today.plusDays(6);
				return dtf.format(dateOfDayOfWeek);
			case 2:
				dateOfDayOfWeek = today.plusDays(5);
				return dtf.format(dateOfDayOfWeek);
			case 3:
				dateOfDayOfWeek = today.plusDays(4);
				return dtf.format(dateOfDayOfWeek);
			case 4:
				dateOfDayOfWeek = today.plusDays(3);
				return dtf.format(dateOfDayOfWeek);
			case 5:
				dateOfDayOfWeek = today.plusDays(2);
				return dtf.format(dateOfDayOfWeek);
			case 6:
				dateOfDayOfWeek = today.plusDays(1);
				return dtf.format(dateOfDayOfWeek);
			default:
				dateOfDayOfWeek = today.minusDays(0);
				return dtf.format(dateOfDayOfWeek);
			}
		}
	}

}
