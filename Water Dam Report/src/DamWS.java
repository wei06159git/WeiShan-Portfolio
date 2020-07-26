/*
 * Name:      Wei-Shan Sun
 *
 * Course:    CS-12, Spring 2018
 *
 * Date:      04/25/18
 *
 * Filename:  DamWS.java
 *
 * Purpose:   Creating a DamWS class and its API
 */
 
public class DamWS {
    
    // instance variables ------------
    private String name;      
    private int year;       
    private double storage;
    private double capacity;
    private double inflow;
    private double outflow;
    private CS12Date date;
    
    // constants
    private final int CUFT_PER_ACREFT = 43560;
    private final int SECS_PER_HR = 3600;
    private final int HRS_PER_DAY = 24;
    
    // formatting
    private String nameFmt = "%-10s";
    private String numFmt = "%-10d";
    private String waterFmt = "%,15.0f";
    private String dateFmt1 = "%15s";
    private String nameFmt1 = "%-30s%-15s\n";
    private String numFmt1 = "%-30s%-25d\n";
    private String nameFmt2 = "%-30s%-29s\n";
    private String ageFmt = "%-30s%-30d\n";
    private String waterFmt1 = "%-30s%-,9.0f\n";
    private String waterFmt2 = "%-30s%-,9.0f\n";
    private String waterFmt3 = "%-30s%-,9.0f\n";
    private String dayFmt = "%-30s%-,2d\n";
    private String dateFmt = "%-30s%-10s\n";
    private String percentFmt = "%-30s%-,1.1f%%\n";
    
    // data above here
    //===============================================
    // methods below here
    
    // constructors ------------------
    
    // default constructor
    public DamWS() {
        name = "<default name>";
        year = 1900;
        storage = 0.0;
        capacity = 0.0;
        inflow = 0.0;
        outflow = 0.0;
        date = new CS12Date();   
    }
    
    // full constructor
    public DamWS(String name, int year, double storage,
                 double capacity, double inflow, double outflow,
                 CS12Date date) {
        this();
        setName(name);
        setYear(year);
        setCapacity(capacity);
        setStorage(storage);
        setInflow(inflow);
        setOutflow(outflow);
        setDate(date);
    }
    
    // alternate constructor: dam names and opening year only
    public DamWS(String name, int year) {
        this();
        setName(name);
        setYear(year);
    }
    
    // alternate constructor: dam name and capacity only
    public DamWS(String name, double capacity) {
        this();
        setName(name);
        setCapacity(capacity);
    }
    
    // display methods ---------------
    
    // string version of object data
    public String toString() {
        return String.format(nameFmt, name)  + " " +
               String.format(numFmt, year)  + " " +
               String.format(waterFmt, storage) + " " +
               String.format(waterFmt, capacity) + " " +
               String.format(waterFmt, inflow) + " " +
               String.format(waterFmt, outflow) + " " +
               String.format(dateFmt1, date.toString());
    }
    
    // formatted version of object data
    public void print() {
        System.out.printf(nameFmt1, "name:", name);
        System.out.printf(numFmt1, "Year Opened:", year);
        System.out.printf(dateFmt, "date as of:", date.toString());
        System.out.printf(waterFmt3, "stroage [acre-ft]:", storage);
        System.out.printf(waterFmt2, "capacity [acre-ft]:", capacity);
        System.out.printf(waterFmt1, "inflow [cu-ft/s]:", inflow);
        System.out.printf(waterFmt1, "outflow [cu-ft/s]:", outflow);
        System.out.printf(ageFmt, "age [yrs]:", getAge());
        System.out.printf(nameFmt2, "status:", getStatus());
        System.out.printf(percentFmt, "%full:", getPercentFull());
        System.out.printf(dayFmt, "days until dam event:", getEventDays());
        System.out.printf(dateFmt, "date of dam event:", getEventDate().toString());
    }
    
    // overloaded version of print, accepts a label string
    public void print(String message) {
        System.out.println("======================");
        System.out.println(message);
        System.out.println("======================");
        print();
    }
    
    // accessors, mutators -----------
   
    // Dam name accessor
    public String getName() {
        return name;
    }
    
    // Dam name mutator
    public void setName(String name) {
        this.name = name;
    }
    
    // overloaded Dam name mutator version, prompts for data
    public void setName(boolean inputMode) {
        String data = UtilsWS.readString("Enter dam name > ", inputMode);
        setName(data);
    }

    // Dam year accessor
    public int getYear() {
        return year;
    }
    
    // Dam year mutator
    public void setYear(int year) {
    
        // if year >= 1900, set the value
        if (year >= 1900) {
            this.year = year;
        }
        
        // otherwise, show error message
        else {
            System.out.println("ERROR: year must be >= 1900, value unchanged");
        }
    }
    
    // overloaded Dam year mutator version, prompts for data
    public void setYear(boolean inputMode) {
        int data = UtilsWS.readInt("Enter Dam opening year> ", inputMode);
        setYear(data);
    }
    
    // current storage accessor
    public double getStorage() {
        return storage;
    }
    
    // current storage mutator
    public void setStorage(double storage) {
    
        // if storage is >=0.0 and storage is less than or equal to capacity, set the value
        if ((storage >= 0.0) && (storage <= capacity)) {
            this.storage = storage;
        }
        
        // otherwise, show error message
        else {
            System.out.println("ERROR: storage must be >= 0.0 and <= capacity, value unchanged");
        }
    }
    
    // overloaded current storage mutator version, prompts for data
    public void setStorage(boolean inputMode) {
        double data = UtilsWS.readDouble("Enter current dam water storage> ", inputMode);
        setStorage(data);
    }

    // dam water capacity accessor
    public double getCapacity() {
        return capacity;
    }
    
    // dam water capacity mutator
    public void setCapacity(double capacity) {
    
        // if capacity is >=0.0 and capacity is larger than or equal to capacity, set the value
        if ((capacity >= 0.0) && (capacity >= storage)) {
            this.capacity = capacity;
        }
        
        // otherwise, show error message
        else {
            System.out.println("ERROR: capacity must be >= 0.0 and >= storage, value unchanged"); 
        }
    }
    
    // overloaded dam water capacity mutator version, prompts for data
    public void setCapacity(boolean inputMode) {
        double data = UtilsWS.readDouble("Enter total dam water capacity> ", inputMode);
        setCapacity(data);
    }
    
    // water inflow rate accessor
    public double getInflow() {
        return inflow;
    }
    
    // water inflow rate mutator
    public void setInflow(double inflow) {
    
        // if inflow rate is >= 0.0, set the value
        if (inflow >= 0.0) {
            this.inflow = inflow;
        }
        
        // otherwise, show error message
        else {
            System.out.println("ERROR: inflow must be >= 0.0, value unchanged");
        }
    }
    
    // overloaded water inflow rate mutator version, prompts for data
    public void setInflow(boolean inputMode) {
        double data = UtilsWS.readDouble("Enter current water inflow rate> ", inputMode);
        setInflow(data);
    }
    
    // water outflow rate accessor
    public double getOutflow() {
        return outflow;
    }
    
    // water outflow rate mutator
    public void setOutflow(double outflow) {
    
        // if outflow rate is >= 0.0, set the value
        if (outflow >= 0.0) {
            this.outflow = outflow;
        }
        
        // otherwise, show error message
        else {
            System.out.println("ERROR: outflow must be >= 0.0, value unchanged");
        }
    }
    
    // overloaded water outflow rate mutator version, prompts for data
    public void setOutflow(boolean inputMode) {
        double data = UtilsWS.readDouble("Enter current water outflow rate> ", inputMode);
        setOutflow(data);
    }
    
    // date accessor
    public CS12Date getDate() {

        CS12Date temp = new CS12Date();
        temp.setMonth(this.date.getMonth());
        temp.setDay(this.date.getDay());
        temp.setYear(this.date.getYear());
        return temp;
    }
    
    // date mutator
    public void setDate(CS12Date date) {
    
        (this.date).setMonth(date.getMonth());
        (this.date).setDay(date.getDay());
        (this.date).setYear(date.getYear());
    }
    
    // overloaded date mutator version, prompts for data
    public void setDate(boolean inputMode) {
        int month = UtilsWS.readInt("Enter measurement date month: ", inputMode);
        int day = UtilsWS.readInt("Enter measurement date day: ", inputMode);
        int year = UtilsWS.readInt("Enter measurement date year: ", inputMode);
        
        // set up local date from inputs
        CS12Date data = new CS12Date(month, day, year);
        
        //call other mutator with date just created
        setDate(data);
    }
    
    // equivalance -------------------
    
    // this is the standard interface for equals()
    public boolean equals(Object obj) {
    
        final double TOL = 0.0001;
    
        // first, check whether objects of same type
        if (!(obj instanceof DamWS)) {
            return false;
        }
        
        else {
            // typecast into the intended object types
            DamWS p = (DamWS) obj;
            
            // check field-by-field on ALL fields
            if ( (p.getName().equals(this.name)) &&
                 (p.getYear() == (this.year)) &&
                 (Math.abs(p.getStorage() - this.storage) <= TOL) &&
                 (Math.abs(p.getCapacity() - this.capacity) <= TOL) &&
                 (Math.abs(p.getInflow() - this.inflow) <= TOL) &&
                 (Math.abs(p.getOutflow() - this.outflow) <= TOL) &&
                 (p.getDate().equals(this.date))
                 ) {
                return true;
            }
            else {
                return false;
            }
        } 
        
    } // end equals
    
    // derived data accessors --------
    
    // get dam's age by subtracting between dam's opening year and reference year
    public int getAge() {
    
        return UtilsWS.getAge( new CS12Date(1,1, year), date);
    }
    
    // get dam's status by comparing inflow amount and outflow amount
    public String getStatus() {
    
        // the dam is filling, if inflow rate is larger than outflow rate
        if (inflow > outflow) {
            return "FILLING";
        }
        
        // the dam is empyting, if inflow rate is smaller than outflow rate
        else if (inflow < outflow) {
            return "EMPTYING";
        }
        
        // the dam is holding, if inflow rate is equal to outflow rate
        else {
            return "HOLDING";
        }
    
    }
    
    // get percentage of full capacity for current dam water storage by computing ratio between storage and capacity and then scaled by 100
    public double getPercentFull() {
    
        // if capacity is larger than 0.0, do the computation
        if (capacity > 0.0) {
            return storage / capacity *100;
        }
        
        // otherwise, set to 0.0%
        else {
            return 0.0;
        }   
    } 
    
    // days until the dam event happens
    public int getEventDays() {
        
        // declare and initialize variable constant
        double constant = ((double)(CUFT_PER_ACREFT)/(SECS_PER_HR * HRS_PER_DAY));
        
        
        // if inflow rate is larger than outflow rate, do time1 computation
        if (inflow > outflow) {
            double time1 = ((capacity - storage)/(inflow - outflow)) * constant; 
            return (int) Math.round(time1 - 0.5);
        }
        
        // if inflow rate is smaller than outflow rate, do time2 computation
        else if (inflow < outflow) {
            double time2 = ((0 - storage)/(inflow - outflow)) * constant;
            return (int) Math.round(time2 - 0.5);
        }
        
        // otherwise inflow rate = outflow rate, set event day -1
        else {
            return -1;
        }
    }
    
    // get the actual calendar day of the above dam event
    public CS12Date getEventDate() {
        CS12Date temp = new CS12Date();
        temp.setDate(date.getMonth(), date.getDay(), date.getYear());
        
        // if event day > -1, add event day to current day to get future event date
        if (getEventDays() > -1) {
            temp.laterDate(getEventDays());
            return temp;
        }
        
        // if inflow rate = outflow rate, add 100 years to current year
        else {
            temp.setYear(date.getYear() + 100);
            return temp;
        }
    }
     
    // utility methods ---------------
    
    // update all instance variables
    public void update(boolean inputMode) {
        setName(inputMode);      
        setYear(inputMode);
        setCapacity(inputMode);       
        setStorage(inputMode);
        setInflow(inputMode);
        setOutflow(inputMode);
        setDate(inputMode);
    }
    
    // import water into storage and update new current amount of storage
    public void importWater(double acreFeet) {
        
        // if the import amount < 0, show error message 
        if ((acreFeet < 0.0)) {
            System.out.println("ERROR: acreFeet must be >= 0.0, no import");
        }
        
        // if the sum if the import amount and storage > capacity, show error message
        else if ((storage + acreFeet) > capacity) {
            System.out.println("ERROR: the sum of storage and acreFeet must be <= capacity, no import");
        }
        
        // otherwise, update new amount of storage
        else {
            storage += acreFeet;
        }
    }
    
    // release water, update new current amount of storage
    public void releaseWater(double acreFeet) {
        
        // if releasing amount of water is below 0, show error message
        if (acreFeet < 0.0){
            System.out.println("ERROR: acreFeet must be >= 0.0, no release");    
        }
        
        // if subtraction is below 0, show error message
        else if ((storage - acreFeet) < 0.0) {
            System.out.println("ERROR: the subtraction between storage and acreFeet must be >= 0.0, no release");
        }
        
        // otherwise, do the computation
        else {
            storage -= acreFeet;
        }
    }
    
    // increase outflow by specified amount, and then update new current outflow rate
    public void increaseOutflow(double cuFtSec) {
    
        // if increasing amount is below 0, show error message 
        if (cuFtSec < 0.0) {
            System.out.println("ERROR: cuFtSec must be >= 0.0, no changed");    
        }
        
        // if storage = 0, show error message
        else if (storage == 0.0) {
            System.out.println("ERROR: storage = 0.0, no changed");   
        }
        
        // otherwise, add increasing amount to outflow rate
        else {
            outflow += cuFtSec;
        }
    }
    
    // decrease outflow by specified amount, and then update new current outflow rate
    public void decreaseOutflow(double cuFtSec) {
    
        // if decreasing amount is below 0, show error message 
        if (cuFtSec < 0.0) {
            System.out.println("ERROR: cuFtSec must be >= 0.0, no changed");    
        }
        
        // if subtraction is below 0, show error message
        else if ((outflow - cuFtSec) < 0.0) {
            System.out.println("ERROR: the subtraction between utflow and cuFtSec must be >= 0.0, no changed");
        }
        
        // if storage = 0, show error message
        else if (storage == 0.0) {
            System.out.println("ERROR: storage = 0.0, no changed");
        }
        
        // otherwise, outflow rate is subtracted by decreasing amount
        else {
            outflow -= cuFtSec;    
        }
    }
    
    
    // test driver for this class
    public static void main(String [] args) {
    
        DamWS d1 = new DamWS();
        DamWS d2 = new DamWS("Fred", 1988, 9.0, 8.0, 7.0, 6.0, new CS12Date());
        DamWS d3 = new DamWS("Fred", 1988);
        DamWS d4 = new DamWS("Fred", 1234.56);
        boolean inputMode = false;
        
        // test display methods message
        System.out.println("======================");
        System.out.println("Testing display methods");
        System.out.println("======================");
        System.out.println();
        
        // test toString and overloaded print() methods; d4 tests toString, print() and overloaded print() 
        System.out.println(d1);  
        d1.print("Default constructor DamWS is:");
        System.out.println();
        UtilsWS.pause();
        
        System.out.println(d2);  
        d2.print("Full constructor DamWS is:");
        UtilsWS.pause();
        
        System.out.println(d3);  
        d3.print("Name/Year constructor DamWS is:");
        System.out.println();
        UtilsWS.pause();
        
        System.out.println(d4);  
        d4.print();
        d4.print("Name/Capacity constructor DamWS is:");
        System.out.println();
        UtilsWS.pause();
        
        // show d2 before any update
        d2.print("Before any update");
        System.out.println();
        UtilsWS.pause();
        
        //test mutators and accessors message
        System.out.println("======================");
        System.out.println("Testing mutators and accessors");
        System.out.println("======================");
        
        // test prompted mutators and accessors
        d2.setName(inputMode);
        System.out.println("dam name = " + d2.getName());
        d2.setYear(inputMode);
        System.out.println("dam opening year = " + d2.getYear());
        d2.setDate(inputMode);
        System.out.println("date = " + d2.getDate());
        d2.setStorage(inputMode);
        System.out.println("current storage = " + d2.getStorage());
        d2.setCapacity(inputMode);
        System.out.println("max capacity = " + d2.getCapacity());
        d2.setInflow(inputMode);
        System.out.println("inflow rate = " + d2.getInflow());
        d2.setOutflow(inputMode);
        System.out.println("outflow rate = " + d2.getOutflow());
        System.out.println();
        
        // show d2 after all above update
        d2.print("After all above update");
        System.out.println();
        UtilsWS.pause();
        
        // test equality
        System.out.println("======================");
        System.out.println("Testing equality");
        System.out.println("======================");
        
        // Object d2 against itself
        System.out.println("object d2 equals object d2?  " + d2.equals(d2));
        
        // two different objects d1 and d2
        System.out.println("object d1 equals object d2?  " + d1.equals(d2));

        // object d2 against CS12Date object dam
        CS12Date dam = new CS12Date(01,01,2018);
        System.out.println("d2 equals CS12Date object?   " + d2.equals(dam));
        
    } // end main

    
} // end class