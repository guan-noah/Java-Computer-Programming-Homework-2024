//PhoneBook.java
/* Noah Guan 
 * 01/11/2025 
 * Period 6 Java w/ Mr. Yu 
 * PhoneBook.java 
 * Homework for 01/10/2025 
 * Testing Plan: 
none :D 
*/
import java.util.Scanner;
class PhoneBook {
    private String name, city, phone;
    private int areacode;
    public PhoneBook()
    {
        name = new String ("Thomas Jefferson");
        city = new String ("New York");
        phone = new String ("123-4567");
    }
    public static void main(String[] arguments) 
    {
        PhoneBook book = new PhoneBook();
        book.getInput();
        book.processInputAndPrint();
    }
    public void getInput()
    {
        System.out.println("\n\n\n");
        System.out.print("Enter name as first last: ");
        Scanner keyboard = new Scanner(System.in);
        name = keyboard.nextLine().trim();
        System.out.print("Enter city of residence: ");
        city = keyboard.nextLine().trim();
        System.out.print("Enter phone number (without area code): ");
        phone = keyboard.nextLine().trim();
    }
    public void processInputAndPrint()
    {
        int lSI = name.lastIndexOf(' '); //lastSpaceIndex 
        if (city.equalsIgnoreCase("Mountain View") || city.equalsIgnoreCase("Palo Alto"))
            phone = "(650) " + phone; 
        else if (city.equalsIgnoreCase("Cupertino")||city.equalsIgnoreCase("San Jose"))
            phone = "(408) " + phone;
        else 
            phone = "(???) " + phone; 
        
        System.out.println("The phone number will be alphabetized under " + name.charAt(lSI+1) + ", under " + name.substring(lSI+1) + ", " + name.substring(0, name.indexOf(' ')) + " at " + phone);
        //takes letter after space (last name initial), then everything at and after the letter after space (last name), then everything from 0 to space (first name), then the phone  
        System.out.println("\n\n\n");
    }
}
