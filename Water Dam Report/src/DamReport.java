/*
 * Name:      Wei-Shan Sun
 *
 * Course:    CS-12, Spring 2018
 *
 * Date:      05/21/18
 *
 * Filename:  DamReportWS.java
 *
 * Purpose:   Practice using object array and reusing previous classes inclusing DamWS, ChessTutor and UtilsWS to create a dam report.
 */

import java.util.Scanner;
import java.io.File; 
import java.io.IOException;

public class DamReport {
 
    public static void main(String [] args) throws IOException {
 
        // variable declarations/initializations
        final int MAX_DAMS = 50;
        DamWS [] dams = new DamWS[MAX_DAMS];
        int count = 0;
        char ch;
        boolean flag = false;
        String menu = "------------------------------\n" +
                      "DAM OPTIONS:\n" +
                      "------------------------------\n" +
                      "Read data from file          [R]\n" +
                      "Print dam summaries          [S]\n" +
                      "Print dam details            [D]\n" +
                      "Overall water status         [W]\n" +
                      "Quit                         [Q]\n";
                
        //print option menu              
        System.out.print(menu);
      
        // priming read
        ch = UtilsWS.readChar("Enter option: ", flag);
        
        // loop until terminating char(s) NOT encouontered
        while ((ch != 'Q') && (ch != 'q')) {
            
            switch (ch) {
            
                // case R or r 
                case 'R':
                case 'r':
                    count = readDataFromFile(dams);
                    break;
                
                // case S or s    
                case 'S':
                case 's':
                    printDamSummaries(dams, count);
                    break;
                
                // case D or d    
                case 'D':
                case 'd':
                    printDamDetails(dams, count);
                    break;
                    
                // case W or w    
                case 'W':
                case 'w':
                    showWaterStatus(dams, count);
                    break;
                
                // if enter other than above options, display below message   
                default:
                    System.out.println("Unrecognized option " + ch + ", please try again\n");
                    break;
            }
            
            // update read
            System.out.print(menu);
            ch = UtilsWS.readChar("Enter option: ", flag);
        }
        
        // termination action(s)
        System.out.println("Exit upon user request");
        
    } // end main
    
    // method to convert an input String to a Dam object
    private static DamWS convertString2Dam(String data) {
        
        // declarations
        String name;      
        int year;       
        double storage;
        double capacity;
        double inflow;
        double outflow;
        CS12Date date;

        // split original line 
        String [] tokens1 = data.split(",");
        for (int i=0; i < tokens1.length; i++) {
            tokens1[i] = tokens1[i].trim();
        }
        
        // secondary split on date token 
        String [] tokens2 = tokens1[6].split("/");
        for (int i=0; i < tokens2.length; i++) {
            tokens2[i] = tokens2[i].trim();
        }
        
        // extract scalar values and assemble object
        name = tokens1[0];
        year = Integer.parseInt(tokens1[1]);
        storage = Double.parseDouble(tokens1[2]);
        capacity = Double.parseDouble(tokens1[3]);
        inflow = Double.parseDouble(tokens1[4]);
        outflow = Double.parseDouble(tokens1[5]);
        date = new CS12Date(Integer.parseInt(tokens2[0]), Integer.parseInt(tokens2[1]), Integer.parseInt(tokens2[2]));
        
        // use all data to create a new DamWS object
        return new DamWS(name, year, storage, capacity, inflow, outflow, date);
    }
    
    // method to read text file and insert Dams into array
    private static int readDataFromFile(DamWS [] dams) throws IOException{
        
        // declarations
        String filename, text;
        int count =0;
        DamWS damtext;
        final int MAX_DAMS = 50;
        
        // nulling entire dams array
        for (int i =0; i< MAX_DAMS; i++) {
            dams[i] = null;
        }
        
        filename = UtilsWS.readString("Enter text file name: ", false);
        File infile = new File(filename);
        Scanner fileInput = new Scanner(infile);
        
        while (fileInput.hasNext()) {
            text = fileInput.nextLine();
            damtext = convertString2Dam(text);
            dams[count] = damtext;
            count++;
        }
        
        System.out.println(count + " dams read from file: " + filename);
        System.out.println(" ");
        
        return count;
    }
    
    // method to print all used Dams in a tubular format
    private static void printDamSummaries(DamWS [] dams, int count) {
        
        if (count == 0) {
            System.out.println("Error: no Dams currently exist! Must import from file.");
            System.out.println(" ");
        }
        else {
            System.out.println(" ");
            System.out.printf("%5s%11s%22s%16s%16s%16s%15s\n", "Name ", "Year ", "Storage ", "Capacity ", "Inflow ", "Outflow ", "Date");
            for (int i = 0; i < count; i++) {
                System.out.println(dams[i].toString());
            }
        }
    }
    
    // method to print all used Dams using their own print()
    private static void printDamDetails(DamWS [] dams, int count) {
            
        if (count == 0) {
            System.out.println("Error: no Dams currently exist! Must import from file.");
            System.out.println(" ");
        }
        
        else {
            System.out.println(" ");
            
            for (int i = 0; i < count; i++){
                System.out.println("========");
                System.out.println(dams[i].getName());
                System.out.println("========");
                dams[i].print();
                System.out.println(" ");
            }
        }
    }
    
    // method to loop thru used Dams, sum all water data, create new "super Dam" and print() it
    private static void showWaterStatus(DamWS [] dams, int count) {
        
        DamWS superDam = new DamWS();
                
        if (count == 0) {
            System.out.println("Error: no Dams currently exist! Must import from file.");
            System.out.println(" ");
        }
        
        else {
            System.out.println("====================");
            System.out.println("OVERALL WATER HEALTH");
            System.out.println("====================");
            
            superDam.setName("Super Dam");
            superDam.setDate(dams[0].getDate());
            superDam.setYear((UtilsWS.today()).getYear());
            double totalStorage =0;
            double totalCapacity =0;
            double totalInflow =0;
            double totalOutflow =0;

            for (int i=0; i < count; i++) {
                totalCapacity += dams[i].getCapacity();
                totalStorage += dams[i].getStorage();               
                totalInflow += dams[i].getInflow();
                totalOutflow += dams[i].getOutflow();
            }
            
            superDam.setCapacity(totalCapacity);
            superDam.setInflow(totalInflow);
            superDam.setOutflow(totalOutflow);
            superDam.setStorage(totalStorage); 
            superDam.print();
            System.out.println("\n");
        }
    }
    
    
} // end class