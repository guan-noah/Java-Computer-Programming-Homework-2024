//MyID.java
/* Noah Guan 
 * 01/11/2025 
 * Period 6 Java w/ Mr. Yu 
 * MyID.java 
 * Personal project for 01/10/2025 
 * Testing Plan: 
none :D 
*/
import java.util.Scanner;
class Main //myID
{
    //information about person 
    private String phone, name, city, region, country, likes, dislikes;
    private int age;
    public Main()
    {
        phone = "(???) ???-????";
        name = city = region = country = likes = dislikes = "?";
        age = 0;
    }
    public static void main(String[] arguments) 
    {
        Main main = new Main();
        main.run();
    }
    private void run()
    {
        
    }
    public String get(String info)
    {
        if (info.trim().equalsIgnoreCase("name"))
        {
            return name;
        }
        else if (info.trim().equalsIgnoreCase("city"))
        {
            return city;
        }
        else if (info.trim().equalsIgnoreCase("region"))
        {
            return region;
        }
        else if (info.trim().equalsIgnoreCase("country"))
        {
            return country;
        }
        else if (info.trim().equalsIgnoreCase("location"))
        {
            return (city + ", " + region + ", " + country);
        }
        else if (info.trim().equalsIgnoreCase("likes"))
        {
            return name;
        }
        else if (info.trim().equalsIgnoreCase("dislikes"))
        {
            return name;
        }
        else if (info.trim().equalsIgnoreCase("age"))
        {
            return ("" + age); //age as a String, they're going to have to use Double.parseDouble(age)
        }
        else 
            System.out.println("Error: Incorrect MyID parameter found. ");
    }
    public void set(String info)
    {
        
    }
}
