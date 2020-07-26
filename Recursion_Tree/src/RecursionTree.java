/*
 * Wei-Shan Sun
 * Programming Assignment 0 - Closest Pairs 
 * This Program uses Brute Force to solve it
 * IDE: Eclipse Neon.3 Release (4.6.3)
 * Runtime Environment
 * Platform: Apple Mac OS
 * Compiler: JDK 1.8.0
 * Compilation Switches  : (developed in Forte for Java, Community Edition, v.1.0 (Build 842)
 */

import java.util.Scanner;
import java.util.Random;

public class RecursionTree{
	
	//-----------------------------------------------------------------
	// DivideandConquer
	// This function accepts input as T(n)=aT(n/b)+f(n)
	// For f(n), it only accepts polynomial c(n^d)
	// It will compute the total nonrecursive cost at each depth of the recursion tree and print out result
	//-----------------------------------------------------------------
	public static void DivideandConquer(int a, Rational b, Rational c, Rational d, int level, int stopLevel){
		
		//declare a Rational variable “eachLevel” for below calculation
		Rational eachLevel = new Rational(level,1);
		
			//root node 
			if (level == 0){
				int nodeNum = 1; //root has 1 node
				
				//print out number of nodes, recursive forms and non-recursive cost for root level
				System.out.print("Depth = " + level+" | ");
				System.out.println("Number of Nodes = " + nodeNum);
				System.out.print("Expanded Node Form: ");
				System.out.println("T(n)" + " | " + c+"n^"+d);
				System.out.println("Total Work at Depth " + level + ": = " + c+"n^"+d);
				System.out.println();
			}
			else if(level!=0 && level <stopLevel){
				//declare a Rational variable “node” for below calculation
				Rational node = new Rational(a,1);
				//calculate number of nodes in each level
				Rational nodeNum = node.expon(eachLevel);
				//calculate the exponent of inverse of 'b'--> (1/b)^level
				Rational expInverseB = b.reciprocal().expon(eachLevel);
				//calculate ((1/b)^level)^d
				Rational expInverseExpond = (expInverseB.expon(d));
				
				//calculate "number of nodes * c * ((1/b)^level)^d" for total work at depth
				Rational totalWorkDepth = nodeNum.multiply(c.multiply(expInverseB.expon(d)));
				
				//print out number of nodes, recursive forms and non-recursive cost for each level
				System.out.print("Depth = " + level+" | ");
				System.out.println("Number of Nodes = " + nodeNum);
				System.out.print("Expanded Node Form: ");
				System.out.println("T(" + "(" + expInverseB + ")" +"n" +")" + " | " + c+ "("+ expInverseExpond + ")" +"n^"+ d);
				System.out.println("Total Work at Depth " + level + ": = " + totalWorkDepth +"n^"+ d);
				System.out.println();
				
			}
			else{
				System.exit(0);
			}
			level++;
			DivideandConquer(a,b,c,d,level,stopLevel);
		
		
	}//end DivideandConquer
	
	
	//-----------------------------------------------------------------
	// ChipandBeConquer
	// This function accepts input as T(n)=aT(n-b)+f(n)
	// For f(n), it only accepts polynomial c(n^d)
	// It will compute the total nonrecursive cost at each depth of the recursion tree and print out result
	//-----------------------------------------------------------------
	public static void ChipandBeConquer(int a, int b, Rational c, Rational d, int level, int stopLevel){
		
		//declare a Rational variable “eachLevel” for below calculation
		Rational eachLevel = new Rational(level,1);
		
		//root node
		if(level == 0){
			int nodeNum = 1; //root has 1 node
			System.out.print("Depth = " + level+" | ");
			System.out.println("Number of Nodes = " + nodeNum);
			System.out.print("Expanded Node Form: ");
			System.out.println("T(n)" + " | " + c+"n^"+d);
			System.out.println("Total Work at Depth " + level + ": = " + c+"n^"+d);
			System.out.println();
		}
		else if(level!=0 && level <stopLevel){
			//declare a Rational variable “node” for below calculation
			Rational node = new Rational(a,1);
			//calculate number of nodes in each level
			Rational nodeNum = node.expon(eachLevel);
			//calculate 'b' value at each level
			int levelb = b * level;
			
			//calculate total work at depth total number of nodes * c
			Rational totalWorkDepth = nodeNum.multiply(c);
			
			System.out.print("Depth = " + level+" | ");
			System.out.println("Number of Nodes = " + nodeNum);
			System.out.print("Expanded Node Form: ");
			System.out.println("T(n-" + levelb + ")" + " | " + c+ "(n-"+ levelb + ")^" + d);
			System.out.println("Total Work at Depth " + level + ": = " + totalWorkDepth + "(n-" + levelb + ")^" + d);
			System.out.println();
		}
		else{
			System.exit(0);
		}
		
		level++;
		ChipandBeConquer(a, b, c, d, level, stopLevel);
		
	}//end ChipandBeConquer
	
	//-----------------------------------------------------------------
	// AdvanceDivideandConquer
	// This function accepts input as T(n)=aT(n/b)+f(n)
	// For f(n), it only accepts logarithmic c*log(base e)^d(n)
	// It will compute the total nonrecursive cost at each depth of the recursion tree and print out result
	//-----------------------------------------------------------------
	public static void AdvanceDivideandConquer(int a, Rational b, Rational c, Rational d, Rational e, int level, int stopLevel){
		
		//declare a Rational variable “eachLevel” for below calculation
		Rational eachLevel = new Rational(level,1);
		
		//root node 
		if (level == 0){
			int nodeNum = 1; //root has 1 node
			System.out.print("Depth = " + level+" | ");
			System.out.println("Number of Nodes = " + nodeNum);
			System.out.print("Expanded Node Form: ");
			System.out.println("T(n)" + " | " + c+"*log(base " + e + ")^" + d + "(n)");
			System.out.println("Total Work at Depth " + level + ": = " + c +"*log(base " + e + ")^(" + d + ")(n)");
			System.out.println();
		}
		else if(level!=0 && level <stopLevel){
			//declare a Rational variable “node” for below calculation
			Rational node = new Rational(a,1);
			//calculate number of nodes in each level
			Rational nodeNum = node.expon(eachLevel);
			//calculate the exponent of inverse of 'b'--> (1/b)^level
			Rational expInverseB = b.reciprocal().expon(eachLevel);
			
			//calculate "number of nodes * c * ((1/b)^level)^d" for total work at depth
			Rational totalWorkDepth =  nodeNum.multiply(c);
			
			System.out.print("Depth = " + level+" | ");
			System.out.println("Number of Nodes = " + nodeNum);
			System.out.print("Expanded Node Form: ");
			System.out.println("T(" + "(" + expInverseB + ")" +"n" +")" + " | " + c + "*log(base " + e + ")^(" + d + ")(n" + expInverseB+ ")");
			System.out.println("Total Work at Depth " + level + ": = " + totalWorkDepth + "*log(base " + e + ")^(" + d + ")(n" + expInverseB+ ")");
			System.out.println();
		}
		else{
			System.exit(0);
		}
		
		level++;
		AdvanceDivideandConquer(a, b, c, d, e, level, stopLevel);
	}//end AdvanceDivideandConquer
	
	//-----------------------------------------------------------------
	// AdvanceChipandBeConquer
	// This function accepts input as T(n)=aT(n-b)+f(n)
	// For f(n), it only accepts logarithmic c*log(base e)^d(n)
	// It will compute the total nonrecursive cost at each depth of the recursion tree and print out result
	//-----------------------------------------------------------------
	public static void AdvanceChipandBeConquer(int a, int b, Rational c, Rational d, Rational e, int level, int stopLevel){
		
		//declare a Rational variable “eachLevel” for below calculation
		Rational eachLevel = new Rational(level,1);
		
		//root node
		if(level == 0){
			int nodeNum = 1; //root has 1 node
			System.out.print("Depth = " + level+" | ");
			System.out.println("Number of Nodes = " + nodeNum);
			System.out.print("Expanded Node Form: ");
			System.out.println("T(n)" + " | " + c+"*log(base " + e + ")^" + d + "(n)");
			System.out.println("Total Work at Depth " + level + ": = " + c+"*log(base " + e + ")^" + d + "(n)");
			System.out.println();
		}
		else if(level!=0 && level <stopLevel){
			//declare a Rational variable “node” for below calculation
			Rational node = new Rational(a,1);
			//calculate number of nodes in each level
			Rational nodeNum = node.expon(eachLevel);
			//calculate 'b' value at each level
			int levelb = b * level;
			
			//calculate total work at depth total number of nodes * c
			Rational totalWorkDepth = nodeNum.multiply(c);
			
			System.out.print("Depth = " + level+" | ");
			System.out.println("Number of Nodes = " + nodeNum);
			System.out.print("Expanded Node Form: ");
			System.out.println("T(n-" + levelb + ")" + " | " + c +"*log(base " + e + ")^" + d + "(n-" + levelb + ")");
			System.out.println("Total Work at Depth " + level + ": = " + totalWorkDepth + "*log(base " + e + ")^" + d + "(n-" + levelb + ")");
			System.out.println();
		}
		else{
			System.exit(0);
		}
		
		level++;
		AdvanceChipandBeConquer(a, b, c, d, e, level, stopLevel);
	}//end AdvanceChipandBeConquer
	
	//-----------------------------------------------------------------
	// main function
	// Input required data values and Output result for chosen recursion algorithm
	//-----------------------------------------------------------------
	public static void main(String[] args){
		
		System.out.println("=========Input==========");
		
		System.out.println("Following are available recursion tree models (Enter 0 or 1): ");
		System.out.println("0- Divide-and-Conquer:  T(n) = aT(n/b) + f(n)");
		System.out.println("1- Chip-and-Be-Conquer: T(n) = aT(n-b) + f(n)");
		System.out.print("Select a recursion tree model: ");
		Scanner choice = new Scanner(System.in);
		int myChoice = choice.nextInt();
		System.out.println("your choice for recursive tree model is " + myChoice);
		
		System.out.println("Following are available non-recursive cost models for f(n) (Enter 0 or 1): ");
		System.out.println("0-polynomial:  c*n^d ");
		System.out.println("1-logarithmic: c*log(base e)^d(n)");
		System.out.print("Select a non-recursive cost model: ");
		Scanner nonRecursiveChoice = new Scanner(System.in);
		int myNonRecurChoice = nonRecursiveChoice.nextInt();
		System.out.println("your choice for f(n) is " + myNonRecurChoice);
		
		System.out.print("Generate integer parameter 'a' randomly (branching factor at range (2,4)): ");
		Random random = new Random();
		int a = random.nextInt(3)+2;
		System.out.println(a);
		
		System.out.print("Generate rational parameter 'b' randomly (subproblem size): ");
		Rational b = new Rational ((random.nextInt(20)+1), (random.nextInt(20)+1));
		System.out.println(b);
		
		System.out.print("Generate an integer 'b' randomly for Chip-and-Be-Conquer: ");
		int intB = random.nextInt(100)+1;
		System.out.println(intB);
		
		System.out.print("Generate rational constant term 'c' randomly: ");
		Rational c = new Rational ((random.nextInt(20)+1), (random.nextInt(20)+1));
		System.out.println(c);
		
		System.out.print("Generate rational exponent term 'd' randomly: ");
		Rational d = new Rational ((random.nextInt(20)+1), (random.nextInt(20)+1));
		System.out.println(d);
		
		Rational e = new Rational ((random.nextInt(20)+1), (random.nextInt(20)+1));
		if (myNonRecurChoice == 1){
			System.out.print("Generate rational base term 'e' randomly for log: ");
			System.out.println(e);
		}
		
		System.out.print("Recursion model specified: ");
		if (myChoice ==0 && myNonRecurChoice == 0){
			System.out.println("T(n) = " + a + "T(n" + b.getDenominator() + "/" + b.getNumerator() + ") + " + c + "*n^" + d);
		}
		else if(myChoice ==0 && myNonRecurChoice == 1){
			System.out.println("T(n) = " + a + "T(n" + b.getDenominator() + "/" + b.getNumerator() + ") + " + c + "*log(base " + e + ")^" + d + "(n)");
		}
		else if(myChoice ==1 && myNonRecurChoice == 0){
			System.out.println("T(n) = " + a + "T(n-" + b + ") + " + c + "*n^" + d);
		}
		else if(myChoice ==1 && myNonRecurChoice == 1){
			System.out.println("T(n) = " + a + "T(n-" + b + ") + " + c + "*log(base " + e + ")^" + d + "(n)");
		}
		
		System.out.print("Please type the depth level for the recursion tree:");
		choice = new Scanner(System.in);
		int level = choice.nextInt();
		System.out.println();
		
		System.out.println("=========Output==========");
		if (myChoice ==0 && myNonRecurChoice == 0){
			DivideandConquer(a, b,c, d, 0, level);
		}
		else if(myChoice ==0 && myNonRecurChoice == 1){
			AdvanceDivideandConquer(a, b, c, d, e, 0, level);
		}
		else if(myChoice ==1 && myNonRecurChoice == 0){
			ChipandBeConquer(a, intB, c, d, 0, level);
		}
		else if(myChoice ==1 && myNonRecurChoice == 1){
			AdvanceChipandBeConquer(a, intB, c, d, e, 0, level);
		}
		
	
		
		
		
	}// end main
}//end class