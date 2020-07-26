/*
 * Wei-Shan Sun
 * Programming Assignment 2 - Advanced QuickSort
 * IDE: Eclipse Neon.3 Release (4.6.3)
 * Runtime Environment
 * Platform: Apple Mac OS
 * Compiler: JDK 1.8.0
 * Compilation Switches  : (developed in Forte for Java, Community Edition, v.1.0 (Build 842)
 */

import java.util.Arrays;

//This program implements QuickSort + Partition
//This program implements QiuckSort + median of three: given an array, a[i]...a[j], with j-i>=2, let k=((i+j)/2), choose median value among a[i], a[j], a[k]

public class Advanced_QuickSort {
	
	//to sort an entire array A, initial call is quickSort(A, 1, A.length)
	public static void quickSort(int[] array, int start, int end){
		if (start <(end)){
			int pivotIndex = partition(array, start, end);
			quickSort(array, start, pivotIndex-1);
			quickSort(array, pivotIndex, end);
			
		}
		
	}
	
	//Rearrange the sub array A[p...r] in place
	public static int partition(int[] array, int start, int end){
		int x = array[end];
		int i = start-1;
		for (int j=start; j<end; j++){
			if (array[j] <= x){
				i = i+1;
				int dummyJ = array[j];
				int dummyI = array[i];
				array[i] = dummyJ;
				array[j] = dummyI;
			}
			
		}
		// how many loops there are in each subarray
		System.out.println("Looping: " + (end-start));
		
		int dummyI = array[i+1];
		int dummyR = array[end];
		array[i+1] = dummyR;
		array[end] = dummyI;
		
		return i+1;
	}
	
	//given an array, a[i]...a[j], with j-i>=2, let k=((i+j)/2), choose median value among first, middle and last elements of the array
	public static int[] medianThree(int[] array, int start, int end) {
		
			int k = ((end+start)/2);
			int[] medianOfThree = {array[start], array[k], array[end]};
			Arrays.sort(medianOfThree);
			int middleValue = medianOfThree[1];
			
			//swap the middle value with the last one to serve as a pivot
			//referenced from: https://gist.github.com/epomp447/4c0d0676d9f013788647cbe6e60ae0df
			int dummy = array[end];
			array[end] = middleValue;
			if (array[start] == middleValue){
				array[start] = dummy;
			}
			else if (array[k] == middleValue){
				array[k] = dummy;
			}
		
		return array;
	}
	
	public static void medianThreeQuickSort(int[] array, int start, int end){
		if (start <(end)){
			//when the length between first and last element is >= 3, then call medianThree function to generate a new array
			if((end - start) >= 2){
				int[] newArray = medianThree(array, start, end);
				int pivotIndex = partition(newArray, start, end);
				medianThreeQuickSort(newArray, start, pivotIndex-1);
				medianThreeQuickSort(newArray, pivotIndex, end);
				
			}
			else{
				int pivotIndex = partition(array, start, end);
				medianThreeQuickSort(array, start, pivotIndex-1);
				medianThreeQuickSort(array, pivotIndex, end);
				
			}
		}
		
	}
	
	public static void main(String[] args){

		
		int[] intArray = {10,9,8,7,6,5,4,3,2,1};
		System.out.println("Unsorted Array: ");
		for (int i=0; i<intArray.length; i++){
			System.out.print(intArray[i] + " ");
		}
		System.out.println("");
		System.out.println("QuickSort + Partition:");
		quickSort(intArray, 0, intArray.length-1);
		for (int i=0; i<intArray.length; i++){
			System.out.print(intArray[i] + " ");
		}

		System.out.println();
		System.out.println();
		System.out.println("QuickSort + Median-of-3 Partition:");
		int[] intArray1 = {11,10,9,8,7,6,5,4,3,2,1};
		System.out.println("Unsorted Array: ");
		for (int i=0; i<intArray1.length; i++){
			System.out.print(intArray1[i] + " ");
		}
		System.out.println();
		medianThreeQuickSort(intArray1, 0, intArray1.length-1);
		for (int i=0; i<intArray1.length; i++){
			System.out.print(intArray1[i] + " ");
		}
		
		
		
	}
		
}