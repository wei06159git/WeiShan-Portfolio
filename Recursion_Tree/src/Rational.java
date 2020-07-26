//********************************************************************
//  Rational.java       Author: Lewis and Loftus
//                      Author: Lewis E. Hitchner
//                      Author: John E. Boon, Jr.
//
// http://www.csc.calpoly.edu/~hitchner/CSC101.F2002/RationalNumbers.html#RationalNumbers
// CSC/CPE 101 Fall 2002
// Fundamentals of Computer Science I
// Computer Science Dept., Cal Poly State University 
// San Luis Obispo, Calif., USA
// Prof. Lew Hitchner
//
// Rational and RationalNumbers classes
// Annotated and revised from: "Java Software Solutions, 2nd edition update",
// by John Lewis and William Loftus, pages 196-201 © 2001 by Addison Wesley Longman, Inc.
//
// John E. Boon, Jr. added update method November 2004
//                   added inverse method November 2004
//                   added mult method November 2004
// Represents one rational number with a numerator and denominator.
//********************************************************************

public class Rational extends Object
{
   //-----------------------------------------------------------------
   //  constructor:  Rational (int numer, int denom)
   //
   //  Method summary:
   //-----------------------------------------------------------------
   // ACCESSOR METHODS ("GETTERS")
   // Accessor methods return values of the instance variables, and may
   // also return values that use the instance variables.
   // NO MODIFICATIONS ARE MADE TO INSTANCE VARIABLE VALUES BY ACCESSORS.
   //-----------------------------------------------------------------
   //  public Rational add (Rational op2)
   //  public Rational subtract (Rational op2)
   //  public Rational multiply (Rational op2)
   //  public Rational divide (Rational op2)
   //  public Rational reciprocal ()
   //  public int getNumerator ()
   //  public int getDenominator ()
   //  public boolean equals (Rational op2)
   //  public String toString ()
   //
   //-----------------------------------------------------------------
   // MUTATOR METHODS ("SETTERS")
   // Mutator methods make modifications to instance variables of an object.
   //-----------------------------------------------------------------
   //
   //   This class has no mutator methods.
   //
   //-----------------------------------------------------------------
   // Private methods
   // These are methods that may only be used by members of this class.
   // They are private because they have preconditions that must be met.
   // If a private method was public and was called from a method of another
   // class without meeting the preconditions, the private method might fail,
   // e.g., produce an incorrect answer, go into an infinite loop, etc.
   //-----------------------------------------------------------------
   //  private void reduce ()
   //  private int gcd (int num1, int num2)
   //-----------------------------------------------------------------

   //-----------------------------------------------------------------
   //  Instance variables
   //
   //  Class invariants are:
   //    denominator != 0
   //    denominator is non-negative (but numerator may be negative)
   //    numerator and denominator are in reduced form
   //       i.e., there exists no integer n such that,
   //       numerator = n * denominator or denominator = n * numerator
   //
   //-----------------------------------------------------------------
   private int numerator, denominator;



   //-----------------------------------------------------------------
   //  Constructor
   //
   //  Sets up the rational number by ensuring a nonzero denominator,
   //  making only the numerator signed., and storing reduced values.
   //
   //  PARAMETERS: 
   //    numer (type int) - value of the numerator of a rational number
   //    denom (type int) - value of the denominator of a rational number
   //  POSTCONDITIONS:
   //    a new Rational object has been constructed and initialized with
   //    instance variable values that meet class invariant conditions.
   //-----------------------------------------------------------------
   public Rational (int numer, int denom)
   {
      // Ensure a nonzero denominator
      if (denom == 0)
         denom = 1;

      // Make the numerator "store" the sign
      if (denom < 0)
      {
         numer = -numer;
         denom = -denom;
      }

      // Store "invariant" versions of the parameters into instance vars
      numerator = numer;
      denominator = denom;

      // Call private class method to reduce numerator and denominator
      reduce();
   }

   //-----------------------------------------------------------------
   //  update
   //
   //  Changes the contents of an existing rational number
   //
   //  PARAMETERS:
   //    numer (type int) - value of the numerator of a rational number
   //    denom (type int) - value of the denominator of a rational number
   //  POSTCONDITIONS:
   //    The object's instance variables are changed
   //-----------------------------------------------------------------
   public void update (int numer, int denom)
   {
      this.numerator   = numer;
      this.denominator = denom;
      
      reduce();
   }


   //-----------------------------------------------------------------
   //  add
   //
   //  Adds this rational number to the one passed as a parameter.
   //  A common denominator is found by multiplying the individual
   //  denominators.
   //
   //  PARAMETERS:
   //    op2 (type ref. to Rational object) - number to be added to this one
   //  RETURNS: ref. to a new Rational object that has a numerator and
   //    denominator (in reduced form) representing the sum of this object
   //    plus op2.
   //  POSTCONDITIONS:
   //    A new Rational object has been constructed and a reference to
   //    it has been returned.
   //    (No change to either object's instance variables.)
   //-----------------------------------------------------------------
   public Rational add (Rational op2)
   {
      int commonDenominator = denominator * op2.getDenominator();
      int numerator1 = numerator * op2.getDenominator();
      int numerator2 = op2.getNumerator() * denominator;
      int sum = numerator1 + numerator2;

      return new Rational (sum, commonDenominator);
   }

   //-----------------------------------------------------------------
   //  sum
   //
   //  Adds this rational number to the one passed as a parameter.
   //  A common denominator is found by multiplying the individual
   //  denominators.
   //
   //  PARAMETERS:
   //    op2 (type ref. to Rational object) - number to be added to this one
   //  RETURNS: ref. to a new Rational object that has a numerator and
   //    denominator (in reduced form) representing the sum of this object
   //    plus op2.
   //  POSTCONDITIONS:
   //    A new Rational object has been constructed and a reference to
   //    it has been returned.
   //    (No change to either object's instance variables.)
   //-----------------------------------------------------------------
   public void sum (Rational op2)
   {
      int commonDenominator = this.denominator * op2.getDenominator();
      int numerator1 = this.numerator * op2.getDenominator();
      int numerator2 = op2.getNumerator() * this.denominator;
      int sum = numerator1 + numerator2;
      this.numerator   = sum;
      this.denominator = commonDenominator;
      
      reduce();
   }
   

   //-----------------------------------------------------------------
   //  subtract
   //
   //  Subtracts the rational number passed as a parameter from this
   //  rational number.
   //
   //  PARAMETERS:
   //    op2 (type ref. to Rational object) - number to be subtracted from
   //        this one
   //  RETURNS: ref. to a new Rational object that has a numerator and
   //    denominator (in reduced form) representing the difference of
   //    this object minus op2.
   //  POSTCONDITIONS:
   //    A new Rational object has been constructed and a reference to
   //    it has been returned.
   //    (No change to either object's instance variables.)
   //-----------------------------------------------------------------
   public Rational subtract (Rational op2)
   {
      int commonDenominator = denominator * op2.getDenominator();
      int numerator1 = numerator * op2.getDenominator();
      int numerator2 = op2.getNumerator() * denominator;
      int difference = numerator1 - numerator2;

      return new Rational (difference, commonDenominator);
   }

   //-----------------------------------------------------------------
   //  multiply
   //
   //  Multiplies this rational number by the one passed as a
   //  parameter.
   //
   //  PARAMETERS:
   //    op2 (type ref. to Rational object) - number to be multiplied times
   //        this one
   //  RETURNS: ref. to a new Rational object that has a numerator and
   //    denominator (in reduced form) representing the product of
   //    this object and op2.
   //  POSTCONDITIONS:
   //    A new Rational object has been constructed and a reference to
   //    it has been returned.
   //    (No change to either object's instance variables.)
   //-----------------------------------------------------------------
   public Rational multiply (Rational op2)
   {
      int numer = numerator * op2.getNumerator();
      int denom = denominator * op2.getDenominator();

      return new Rational (numer, denom);
   }
  
   //-----------------------------------------------------------------
   //  mult
   //
   //  Multiplies this rational number by the one passed as a
   //  parameter.
   //
   //  PARAMETERS:
   //    op2 (type ref. to Rational object) - number to be multiplied times
   //        this one
   //  RETURNS: ref. to a new Rational object that has a numerator and
   //    denominator (in reduced form) representing the product of
   //    this object and op2.
   //  POSTCONDITIONS:
   //    A new Rational object has been constructed and a reference to
   //    it has been returned.
   //    (No change to either object's instance variables.)
   //-----------------------------------------------------------------
   public void mult (Rational op2)
   {
      this.numerator   = this.numerator   * op2.getNumerator();
      this.denominator = this.denominator * op2.getDenominator();
      reduce();
   }

   //-----------------------------------------------------------------
   //  expon
   //
   //  Raises this rational number by the one passed as a
   //  parameter. Number passed must be an "integer"
   //
   //  PARAMETERS:
   //    op2 (type ref. to Rational object) - number to be multiplied times
   //        this one
   //  RETURNS: ref. to a new Rational object that has a numerator and
   //    denominator (in reduced form) representing the product of
   //    this object and op2.
   //  POSTCONDITIONS:
   //    A new Rational object has been constructed and a reference to
   //    it has been returned.
   //    (No change to either object's instance variables.)
   //-----------------------------------------------------------------

   public Rational expon (Rational exponent)
   {
   	double realExp = exponent.getNumerator() / exponent.getDenominator();
   	int num, den;
   	
   	num = (int) Math.pow(this.numerator, realExp);
   	den = (int) Math.pow(this.denominator, realExp);
   	
   	return new Rational (num, den);
   }	
   
   //-----------------------------------------------------------------
   //  divide
   //
   //  Divides this rational number by the one passed as a parameter
   //  by multiplying by the reciprocal of the second rational.
   //
   //  PARAMETERS:
   //    op2 (type ref. to Rational object) - number to be divided into
   //        this one
   //  RETURNS: ref. to a new Rational object that has a numerator and
   //    denominator (in reduced form) representing the quotient of
   //    this object and op2.
   //  POSTCONDITIONS:
   //    A new Rational object has been constructed and a reference to
   //    it has been returned.
   //    (No change to either object's instance variables.)
   //-----------------------------------------------------------------
   public Rational divide (Rational op2)
   {
      return multiply (op2.reciprocal());
   }

   //-----------------------------------------------------------------
   //  reciprocal
   //
   //  Returns the reciprocal of this rational number.
   //
   //  PARAMETERS: none
   //  RETURNS: ref. to a new Rational object that has a numerator and
   //    denominator (in reduced form) representing the reciprocal of
   //    this object.
   //  POSTCONDITIONS:
   //    A new Rational object has been constructed and a reference to
   //    it has been returned.
   //    (No change to the object's instance variables.)
   //-----------------------------------------------------------------
   public Rational reciprocal ()
   {
      return new Rational (denominator, numerator);
   }

   //-----------------------------------------------------------------
   //  inverse
   //
   //  Returns the inverse of this rational number.
   //
   //  PARAMETERS: none
   //  RETURNS: nothing
   //  POSTCONDITIONS:
   //          inverts the current rational number
   //-----------------------------------------------------------------
   public void inverse ()
   {
      int tempInt      = this.numerator;
      this.numerator   = this.denominator;
      this.denominator = tempInt;
      
      reduce();      
   }


   //-----------------------------------------------------------------
   //  getNumerator
   //
   //  Returns the numerator of this rational number.
   //  PARAMETERS: none
   //  RETURNS: value of numerator for this object.
   //  POSTCONDITIONS:
   //    The numerator value has been returned.
   //-----------------------------------------------------------------
   public int getNumerator ()
   {
      return numerator;
   }

   //-----------------------------------------------------------------
   //  getDenominator
   //
   //  Returns the denominator of this rational number.
   //
   //  PARAMETERS: none
   //  RETURNS: value of denominator for this object.
   //  POSTCONDITIONS:
   //    The denominator value has been returned.
   //-----------------------------------------------------------------
   public int getDenominator ()
   {
      return denominator;
   }


   //-----------------------------------------------------------------
   //  equals
   //
   //  Determines if this rational number is equal to the one passed
   //  as a parameter.  Assumes they are both reduced.
   //
   //  PARAMETERS:
   //    op2 (type ref. to Rational object) - number to be compared to
   //        this one
   //  RETURNS: true if they are the same values, else false.
   //  POSTCONDITIONS:
   //    true or false has been returned.
   //    (No change to either object's instance variables.)
   //-----------------------------------------------------------------
   public boolean equals (Rational op2)
   {
      return ( numerator == op2.getNumerator() &&
               denominator == op2.getDenominator() );
   }


   //-----------------------------------------------------------------
   //  toString
   //
   //  Returns this rational number as a string.
   //
   //  PARAMETERS: none
   //  RETURNS: String concatenation of numerator and denominator in
   //           fraction format, i.e. n/d  or just  n  when d = 1
   //  POSTCONDITIONS:
   //    String has been returned.
   //    (No change to object's instance variables.)
   //-----------------------------------------------------------------
   public String toString ()
   {
      String result;

      if (numerator == 0)
         result = "0";
      else
         if (denominator == 1)
            result = numerator + "";
         else
            result = numerator + "/" + denominator;
    
      return result;
   }


   //-----------------------------------------------------------------
   //  reduce
   //
   //  Reduces this rational number by dividing both the numerator
   //  and the denominator by their greatest common divisor.
   //
   //  PARAMETERS: none
   //  PRECONDITIONS: denominator > 0
   //  POSTCONDITIONS:
   //    If numerator is not zero, numerator and denominator values have
   //    been replaced by their reduced form values.
   //-----------------------------------------------------------------
   private void reduce ()
   {
      int common;

      if (numerator != 0)
      {
         common = gcd (Math.abs(numerator), denominator);

         numerator = numerator / common;
         denominator = denominator / common;
      }
   }


   //-----------------------------------------------------------------
   //  gcd
   //
   //  Computes and returns the greatest common divisor of the two
   //  positive parameters. Uses Euclid's algorithm.
   //
   //  PARAMETERS:
   //    num1 (type int) - any positive integer
   //    num2 (type int) - any positive integer
   //  RETURNS: greatest common divisor of the two numbers
   //  PRECONDITIONS: both parameters must be > 0
   //  POSTCONDITIONS:
   //    The greatest common divisor of the two numbers has bben returned.
   //-----------------------------------------------------------------
   private int gcd (int num1, int num2)
   {
      while (num1 != num2)
         if (num1 > num2)
            num1 = num1 - num2;
         else
            num2 = num2 - num1;

      return num1;
   }
}