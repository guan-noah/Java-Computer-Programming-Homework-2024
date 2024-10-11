//Noah Guan
//9-4-24 Per 6
/*
Schedule.java
Program #3
Making my school schedule using Format and printf
*/
public class Schedule
{
	public static void main (String[] args)
	{
		System.out.println("\n\n\n");
		
		String label = "Course Schedule For:";
		String myName = "Noah Guan";
		System.out.println(Format.center(label, 100));
		System.out.println(Format.center(myName, 100));
		System.out.printf("Period %-1s:%-5s%-35s%-25s%-5s\n", "#", "", "Course", "Teacher", "", "Room #");
		System.out.printf("Period %-1d:%-5s%-35s%-25s%-5s\n", 1, "", "PE 9", "Mr. Sullivan", "", "Gym");
		System.out.printf("Period %-1d:%-5s%-35s%-25s%-5s\n", 2, "", "Spanish II", "Ms. Autrán", "", "A209");
		System.out.printf("Period %-1d:%-5s%-35s%-25s%-5s\n", 3, "", "Biology", "Mr. Jones", "", "B111");
		System.out.printf("Period %-1d:%-5s%-35s%-25s%-5s\n", 4, "", "Literature / Writing", "Mr. Holaday", "", "B206");
		System.out.printf("Period %-1d:%-5s%-35s%-25s%-5s\n", 5, "", "Algebra II / Trigonometry", "Mr. Payne", "", "D202");
		System.out.printf("Period %-1d:%-5s%-35s%-25s%-5s\n", 6, "", "Computer Programming / Java", "Mr. Yu", "", "E203");
		System.out.printf("Period %-1d:%-5s%-35s%-25s%-5s\n", 7, "", "Free", "N/A", "", "N/A");
		String bill = new String("Outstanding bill: ");
		double amount = 34.56789;
		System.out.printf("%s$%-5.2f", bill, amount);
		
        System.out.println("\n\n\n");
		
		System.out.println("\n\n\n");
	}
}
