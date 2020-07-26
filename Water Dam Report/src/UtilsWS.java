/*
 * Name:       Wei-Shan Sun   
 *
 * Course:     CS-12, Spring 2018
 *
 * Date:       04/09/2018
 *
 * Filename:   UtilsWS.java
 *
 * Purpose:    Starter CS-12 utilities file.
 *             This class is a collection of useful utility methods
 *              which are called statically:    UtilsWS.method()
 *
 *             THIS FILE RELIES UPON HAVING CS12Date.java IN LOCAL DIRECTORY
 */
 
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class UtilsWS {

    //---------------------------------------------------------------------------
    // user input methods, by datatype
    //---------------------------------------------------------------------------
    
    // get an int value
    public static int readInt(String prompt, boolean flag) {
        
        // user input prompts
        Scanner input = new Scanner(System.in);
        
        // variable declaration
        int data = 0;
        String garbage;
        
        System.out.print(prompt);
                
        // if it is false, use Scanner read the input prompt
        if (flag == false) { 

            // loop until getting suitable input      
            while (!input.hasNextInt()) {
            
                // flush input buffer
                garbage = input.nextLine();
                System.out.print("\nPlease enter an interger number > ");
            }
            
            //at this point, data is int type
            data = input.nextInt();
        }
        
        // if it is true, use popup GUI read the input prompt
        else {    
        
            // variable declarations and initializations                 
            String data1;
            boolean failed = true;
                  
            // loop until we get suitable input
            while (failed == true) {
                try {
                
                    // this is fine: anything we enter can become a String
                    data1 = JOptionPane.showInputDialog(null, prompt);
                    
                    // this next statment could possibly fail
                    data = Integer.parseInt(data1);
                    
                    // if we get here, good input, so toggle the flag
                    failed = false;
                    
                }
                catch(NumberFormatException nfe) {
                    //intercepts a failed attempt to convert to an int
                }
                
            }
                    
        }
           
        return data; // regardless of whether obtained by Scanner or JOptionPane
        
    }
    
    // get a double value
    public static double readDouble(String prompt, boolean flag) {
        
        // user input prompts
        Scanner input = new Scanner(System.in);
        
        // variable declaration
        double data = 0.0;
        String garbage;
        
        System.out.print(prompt);
                
        // if it is false, use Scanner read the input prompt
        if (flag == false) { 

            // loop until getting suitable input      
            while (!input.hasNextDouble()) {
            
                // flush input buffer
                garbage = input.nextLine();
                System.out.print("\nPlease enter an interger number > ");
            }
            
            //at this point, data is int type
            data = input.nextDouble();
        }
        
        // if it is true, use popup GUI read the input prompt
        else {    
        
            // variable declarations and initializations                 
            String data1;
            boolean failed = true;
                  
            // loop until we get suitable input
            while (failed == true) {
                try {
                
                    // this is fine: anything we enter can become a String
                    data1 = JOptionPane.showInputDialog(null, prompt);
                    
                    // this next statment could possibly fail
                    data = Double.parseDouble(data1);
                    
                    // if we get here, good input, so toggle the flag
                    failed = false;
                    
                }
                catch(NumberFormatException nfe) {
                    //intercepts a failed attempt to convert to an int
                }
                
            }
                    
        }
           
        return data; // regardless of whether obtained by Scanner or JOptionPane
        
    }

    // get a char value
    public static char readChar(String prompt, boolean flag) {
    
        // user input prompts
        Scanner input = new Scanner(System.in);
        
        // variable declaration
        char data;
        
        // if it is false, use Scanner read the input prompt
        if (flag == false) {
            System.out.print(prompt);
            data = input.nextLine().charAt(0);   //only read first letter
        }
        
        // if it is true, use popup GUI read the input prompt
        else {                     
            String data1;
            data1 = JOptionPane.showInputDialog(null, prompt);
            data = data1.charAt(0);   //only read first letter
        }

        return data; // regardless of whether obtained by Scanner or JOptionPane
    }
    
    // get a string value
    public static String readString(String prompt, boolean flag) {
    
        // user input prompts
        Scanner input = new Scanner(System.in);
        
        // variable declaration
        String data;
        
        // if it is false, use Scanner read the input prompt
        if (flag == false) {
            System.out.print(prompt);
            data = input.nextLine();   
        }
        
        // if it is true, use popup GUI read the input prompt
        else {                     
            data = JOptionPane.showInputDialog(null, prompt);
        }

        return data; // regardless of whether obtained by Scanner or JOptionPane
    }
    
    // execution relative methods------------------------------------------------
    
    // pauses execution until anykey is pressed, specific prompt
    public static void pause(String message) {
    
        // ignores any returned value
        readString(message, false);
        System.out.println();
    }
    
    // pauses execution until any key is pressed, generic prompt
    public static void pause() {
        pause("Press <Enter> to continue...");
    }
    
    // get an int random value
    public static int randomInt(int minValue, int maxValue) {
        Random rand = new Random();
        int randNum;
        randNum = rand.nextInt(maxValue - minValue + 1) + minValue;
        return randNum;
    }
    
    //---------------------------------------------------------------------------
    // age-related methods
    //---------------------------------------------------------------------------
    
    // returns the age as of some REFERENCE date (2-input overloaded form, MODS NEEDED)
    public static int getAge(CS12Date dateBd, CS12Date dateRef) {
        int age = -1; // starter output
        // variable declarations/initializations
        int bdDay, bdMonth, bdYear;
        int refDay, refMonth, refYear;
        bdDay = dateBd.getDay();
        bdMonth = dateBd.getMonth();
        bdYear = dateBd.getYear();
        refDay = dateRef.getDay();
        refMonth = dateRef.getMonth();
        refYear = dateRef.getYear();
        
        // If birth year is after reference year, the age will be -1 to show it hasn't happened yet.
        if (bdYear > refYear) {   
            age = -1;
            System.out.println("ERROR: provided birthdate " + dateBd + " is after current date");
        }
        // If birth year is in this year and the birth month is after reference month, the age will be -1.
        else if ((bdYear == refYear)&&(bdMonth > refMonth)) {    
            age = -1;
            System.out.println("ERROR: provided birthdate " + dateBd + " is after current date");
        }
        // If birth month and year is in this month this year and the day is after the reference day, the age will be -1.
        else if ((bdYear == refYear)&&(bdMonth == refMonth)&&(bdDay > refDay)) {    
            age = -1;
            System.out.println("ERROR: provided birthdate " + dateBd + " is after current date");
        }
        // If birthdate is on reference day, the age will be 0.
        else if ((bdYear == refYear)&&(bdMonth == refMonth)&&(bdDay == refDay)) {    
            age = 0;
        }
        // If birth year is before reference year and birth month is after the reference month, age will be ref. year - birth year -1.
        else if ((bdYear < refYear)&&(bdMonth > refMonth)) {    
            age = refYear - bdYear -1;
        }
        // If birth year is before reference year and birth month is in this month but birth day is after ref. day, age will be ref. year - birth year -1.
        else if ((bdYear < refYear)&&(bdMonth == refMonth)&&(bdDay > refDay)) {    
            age = refYear - bdYear -1;
        }
        // other than above conditions, age is ref. year - birth year
        else {    
            age = refYear - bdYear;
        }
        return age;
        
    } // end 2-input overloaded version
    
    //---------------------------------------------------------------------------

    // returns the age as of TODAY'S date (1-input overloaded form, NO CHANGES NEEDED)
    public static int getAge(CS12Date dateBd) {
        int age;
        CS12Date dateToday = today();
        
        // calls overloaded version of above method, using TODAY as the reference date
        age = getAge(dateBd, dateToday);  
        return age;
        
    } // end 1-input overloaded version
    
    
    //---------------------------------------------------------------------------
    // date-related methods 
    //---------------------------------------------------------------------------
    
    // returns today's date as a CS12Date (NO CHANGES NEEDED)
    public static CS12Date today() {
    
        return new CS12Date();
        
    }
       
} // end class