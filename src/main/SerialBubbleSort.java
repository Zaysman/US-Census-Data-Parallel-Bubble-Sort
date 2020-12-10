package main;


import java.util.LinkedList;
import java.util.Scanner;

//objects
import objects.Entry;
import objects.ExcelReader;


/*
 * This class is for using serial bubble sort on our dataset to compare the runtimes.
 */
public class SerialBubbleSort {

	static ExcelReader er = new ExcelReader(); //This object will read through our excel file and populate our list of entries
	static String sortingParam;
	static long startTime, endTime;
	static LinkedList<Entry> entries = new LinkedList<Entry>(); // This list will contain the entries from the excel reader




	public static void main (String[] args) {
		er.readExcelFile(entries);

		System.out.println("What parameter would you like to serially sort the entries by?\n" 
				+ "1. TotalPop\n"
				+ "2. State\n"
				+ "3. County\n"
				+ "4. Income\n"
				+ "5. Poverty\n");

		System.out.print("Parameter: ");
		Scanner input = new Scanner(System.in);
		sortingParam = input.next();
		input.close();

		startTime = System.currentTimeMillis();
		
		bubbleSort();
		
		endTime = System.currentTimeMillis();
		
		System.out.println("Runtime for serial bubblesort is: " + (endTime - startTime) + " milliseconds");

	}


	public static void bubbleSort() {
		if(sortingParam.compareTo("Total Population") == 0 || sortingParam.compareTo("TotalPop") == 0 ) {
			bubbleSortByTotalPopulation(entries);

		} else if (sortingParam.compareTo("State") == 0) {
			bubbleSortByState(entries);

		} else if (sortingParam.compareTo("County") == 0) {
			bubbleSortByCounty(entries);

		} else if (sortingParam.compareTo("Income") == 0) {
			bubbleSortByIncome(entries);

		} else if (sortingParam.compareTo("Poverty") == 0) {
			bubbleSortByPoverty(entries);

		} else {
			System.out.println("No Valid sorting parameter specified, sorting by TotalPop");
			bubbleSortByTotalPopulation(entries);
		}
	}

	public static void bubbleSortByTotalPopulation(LinkedList<Entry> E) {
		int n = E.size();

		for(int i = 0; i < (n-1); i++) {
			for(int j = 0; j < (n-i)-1; j++) {
				if(E.get(j).getTotalPop() > E.get(j+1).getTotalPop()) {
					swap(E, j, j+1);	
				}
			}
		}

	}

	public static void bubbleSortByState(LinkedList<Entry> E) {
		int n = E.size();

		for(int i = 0; i < (n-1); i++) {
			for(int j = 0; j < (n-i)-1; j++) {
				if(E.get(j).getState().compareTo(E.get(j+1).getState()) > 0) {
					swap(E, j, j+1);
				}
			}
		}

	}

	public static void bubbleSortByCounty(LinkedList<Entry> E) {
		int n = E.size();

		for(int i = 0; i < (n-1); i++) {
			for(int j = 0; j < (n-i)-1; j++) {
				if(E.get(j).getCounty().compareTo(E.get(j+1).getCounty()) > 0) {
					swap(E, j, j+1);
				}
			}
		}
	}

	public static void bubbleSortByIncome(LinkedList<Entry> E) {
		int n = E.size();

		for(int i = 0; i < (n-1); i++) {
			for(int j = 0; j < (n-i)-1; j++) {
				if(E.get(j).getIncome() > E.get(j+1).getIncome()) {
					swap(E, j, j+1);	
				}
			}
		}

	}

	public static void bubbleSortByPoverty(LinkedList<Entry> E) {
		int n = E.size();

		for(int i = 0; i < (n-1); i++) {
			for(int j = 0; j < (n-i)-1; j++) {
				if(E.get(j).getPoverty() > E.get(j+1).getPoverty()) {
					swap(E, j, j+1);	
				}
			}
		}
	}

	public static void swap(LinkedList<Entry> E, int index1, int index2) {
		Entry temp = E.get(index1);
		E.set(index1, E.get(index2));
		E.set(index2, temp);
	}


}
