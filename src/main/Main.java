package main;


//Libraries
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

//objects
import objects.BubbleThread;
import objects.Entry;
import objects.ExcelReader;
import objects.FileWriteObject;

public class Main {

	static ExcelReader er = new ExcelReader(); //This object will read through our excel file and populate our list of entries
	static LinkedList<Entry> entries = new LinkedList<Entry>(); // This list will contain the entries from the excel reader
	static LinkedList<Entry> sortedEntries = new LinkedList<Entry>(); //This list will hold final sorted list of all elements in the excel folder.
	static LinkedList<Entry>[] results; //This list of arrays will hold the sorted sublists of each thread.

	static BubbleThread[] threads; //This array will contain our threads.

	static int numberOfThreads; //Determines how many threads there are
	static int threadnum = 0; //This controls which thread an element from entries will be sent to for sorting
	static String sortingParam;
	
	static long startTime, endTime;


	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		numberOfThreads = 5;
		threads = new BubbleThread[numberOfThreads];
		results = new LinkedList[numberOfThreads];
		sortingParam = "TotalPop";

		//Initialize our threads
		for(int i = 0; i < threads.length; i++) {
			threads[i] = new BubbleThread();
		}

		//initialize our results List
		for (int i = 0; i < results.length; i++) {
			results[i] = new LinkedList<Entry>();
		}

		er.readExcelFile(entries);

		System.out.println("What parameter would you like to sort the entries by?\n" 
				+ "1. TotalPop\n"
				+ "2. State\n"
				+ "3. County\n"
				+ "4. Income\n"
				+ "5. Poverty\n");

		System.out.print("Parameter: ");
		Scanner input = new Scanner(System.in);
		sortingParam = input.next();
		input.close();
		
		//Once we have user Input, Start the timer.
		startTime = System.currentTimeMillis();
		
		

		//Here, we assign the elements of entries from the excel sheet and to a thread.
		int entriesIterator = 0;
		for(int i = 0; i < entries.size(); i++) {
			threads[threadnum].getEntryList().add(entries.get(entriesIterator++));
			updateThreadNumber();
		}
		threadnum = 0;


		//Start the threads
		for(int i = 0; i < threads.length; i++) {
			String tName = "Thread-" + threadnum;
			int tNum = threadnum;
			threads[i].setThreadName(tName);
			threads[i].setThreadNumber(tNum);
			updateThreadNumber();
			threads[i].setResultsList(results);
			threads[i].Start(sortingParam);
		}
		
		//After the threads run, we want continuously check to see if they're null so we can continue with the program as fast as possible.
		

		TimeUnit.SECONDS.sleep(1); //program sleeps for a few seconds after starting threads to give them time to run.
		merge();

		//Once the merging is complete, we get the system time again to calculate the total time of the sort.  
		endTime = System.currentTimeMillis();
		
		System.out.println("sortedEntries has " + sortedEntries.size() + " elements");
		
		//write to file
		writeFile();
		
		
		System.out.println("The Program runtime parallel bubble sort is: " + ((endTime - startTime)) + " milliseconds");
		
	}
	
	

	public static void merge() {
		if(sortingParam.compareTo("TotalPop") == 0) {
			mergeByTotalPopulation();

		} else if(sortingParam.compareTo("State") == 0) {
			mergeByState();

		} else if(sortingParam.compareTo("County") == 0) {
			mergeByCounty();
			
		} else if(sortingParam.compareTo("Income") == 0) {
			mergeByIncome();
			
		} else if(sortingParam.compareTo("Poverty") == 0) {
			mergeByPoverty();
			
		} else {
			System.out.println("Invalid Merge Parameter! Merge by TotalPop");
			mergeByTotalPopulation();
		}

	}

	public static void mergeByTotalPopulation() {
		//Since sorted entries is empty, we can add the first results list with no issue
		sortedEntries = results[0];
		//System.out.println("results[0] size: " + results[0].size());

		//now we need merge the rest of the results with sorted entries.
		int entriesIterator;
		int resultsIterator;

		for(int i = 1; i < numberOfThreads; i++) {

			entriesIterator = 0;
			resultsIterator = 0;

			while(entriesIterator != sortedEntries.size()) {
				/*
				 * If we get through every element of results we can break the loop since there are no more elements to add.
				 */
				if(resultsIterator == results[i].size()) {
					break;
				}

				if(results[i].get(resultsIterator).getTotalPop() <= sortedEntries.get(entriesIterator).getTotalPop()) {
					sortedEntries.add(entriesIterator, results[i].get(resultsIterator++));
					entriesIterator--; // We go back by one to account for the newly added value in sorted entries.	
				}
				entriesIterator++;	
			}

			if(entriesIterator == sortedEntries.size()) {
				for(int j = resultsIterator; j < results[i].size(); j++) {
					sortedEntries.add(results[i].get(j));
				}
			}
		}
	}

	public static void mergeByState() {
		//Since sorted entries is empty, we can add the first results list with no issue
		sortedEntries = results[0];

		//now we need merge the rest of the results with sorted entries.
		int entriesIterator;
		int resultsIterator;


		for(int i = 1; i < numberOfThreads; i++) {
			entriesIterator = 0;
			resultsIterator = 0;

			while(entriesIterator != sortedEntries.size()) {
				/*
				 * If we get through every element of results we can break the loop since there are no more elements to add.
				 */
				if(resultsIterator == results[i].size()) {
					break;
				}

				if(results[i].get(resultsIterator).getState().compareTo(sortedEntries.get(entriesIterator).getState()) < 0) {
					sortedEntries.add(entriesIterator, results[i].get(resultsIterator++));
					entriesIterator--; // We go back by one to account for the newly added value in sorted entries.	
				}

				entriesIterator++;
			}
			if(entriesIterator == sortedEntries.size()) {
				for(int j = resultsIterator; j < results[i].size(); j++) {
					sortedEntries.add(results[i].get(j));
				}
			}
		}
	}

	public static void mergeByCounty() {
		//Since sorted entries is empty, we can add the first results list with no issue
		sortedEntries = results[0];

		//now we need merge the rest of the results with sorted entries.
		int entriesIterator;
		int resultsIterator;


		for(int i = 1; i < numberOfThreads; i++) {
			entriesIterator = 0;
			resultsIterator = 0;

			while(entriesIterator != sortedEntries.size()) {
				/*
				 * If we get through every element of results we can break the loop since there are no more elements to add.
				 */
				if(resultsIterator == results[i].size()) {
					break;
				}

				if(results[i].get(resultsIterator).getCounty().compareTo(sortedEntries.get(entriesIterator).getCounty()) < 0) {
					sortedEntries.add(entriesIterator, results[i].get(resultsIterator++));
					entriesIterator--; // We go back by one to account for the newly added value in sorted entries.	
				}

				entriesIterator++;
			}
			if(entriesIterator == sortedEntries.size()) {
				for(int j = resultsIterator; j < results[i].size(); j++) {
					sortedEntries.add(results[i].get(j));
				}
			}
		}
	}

	public static void mergeByIncome() {
		//Since sorted entries is empty, we can add the first results list with no issue
		sortedEntries = results[0];
		//System.out.println("results[0] size: " + results[0].size());

		//now we need merge the rest of the results with sorted entries.
		int entriesIterator;
		int resultsIterator;

		for(int i = 1; i < numberOfThreads; i++) {

			entriesIterator = 0;
			resultsIterator = 0;

			while(entriesIterator != sortedEntries.size()) {
				/*
				 * If we get through every element of results we can break the loop since there are no more elements to add.
				 */
				if(resultsIterator == results[i].size()) {
					break;
				}

				if(results[i].get(resultsIterator).getIncome() <= sortedEntries.get(entriesIterator).getIncome()) {
					sortedEntries.add(entriesIterator, results[i].get(resultsIterator++));
					entriesIterator--; // We go back by one to account for the newly added value in sorted entries.	
				}
				entriesIterator++;	
			}

			if(entriesIterator == sortedEntries.size()) {
				for(int j = resultsIterator; j < results[i].size(); j++) {
					sortedEntries.add(results[i].get(j));
				}
			}
		}
	}

	public static void mergeByPoverty() {
		//Since sorted entries is empty, we can add the first results list with no issue
		sortedEntries = results[0];
		//System.out.println("results[0] size: " + results[0].size());

		//now we need merge the rest of the results with sorted entries.
		int entriesIterator;
		int resultsIterator;

		for(int i = 1; i < numberOfThreads; i++) {

			entriesIterator = 0;
			resultsIterator = 0;

			while(entriesIterator != sortedEntries.size()) {
				/*
				 * If we get through every element of results we can break the loop since there are no more elements to add.
				 */
				if(resultsIterator == results[i].size()) {
					break;
				}

				if(results[i].get(resultsIterator).getPoverty() <= sortedEntries.get(entriesIterator).getPoverty()) {
					sortedEntries.add(entriesIterator, results[i].get(resultsIterator++));
					entriesIterator--; // We go back by one to account for the newly added value in sorted entries.	
				}
				entriesIterator++;	
			}

			if(entriesIterator == sortedEntries.size()) {
				for(int j = resultsIterator; j < results[i].size(); j++) {
					sortedEntries.add(results[i].get(j));
				}
			}
		}
	}
	
	public static void writeFile() {
		if(sortingParam.compareTo("TotalPop") == 0) {
			FileWriteObject fileWrite = new FileWriteObject();
			fileWrite.writeToFile(sortedEntries, "Sorted Entries by TotalPop.txt", sortingParam);
			
			System.out.println("Finished Writing to file");

		} else if(sortingParam.compareTo("State") == 0) {
			FileWriteObject fileWrite = new FileWriteObject();
			fileWrite.writeToFile(sortedEntries, "Sorted Entries by State.txt", sortingParam);
			
			System.out.println("Finished Writing to file");

		} else if(sortingParam.compareTo("County") == 0) {
			FileWriteObject fileWrite = new FileWriteObject();
			fileWrite.writeToFile(sortedEntries, "Sorted Entries by County.txt", sortingParam);
			
			System.out.println("Finished Writing to file");
			
		} else if(sortingParam.compareTo("Income") == 0) {
			FileWriteObject fileWrite = new FileWriteObject();
			fileWrite.writeToFile(sortedEntries, "Sorted Entries by Income.txt", sortingParam);
			
			System.out.println("Finished Writing to file");
			
		} else if(sortingParam.compareTo("Poverty") == 0) {
			FileWriteObject fileWrite = new FileWriteObject();
			fileWrite.writeToFile(sortedEntries, "Sorted Entries by Poverty.txt", sortingParam);
			
			System.out.println("Finished Writing to file");
			
		} else {
			System.out.println("Invalid Parameter! Writing to file by TotalPop");
			
			FileWriteObject fileWrite = new FileWriteObject();
			fileWrite.writeToFile(sortedEntries, "Sorted Entries by TotalPop.txt", sortingParam);
			
			System.out.println("Finished Writing to file");
		}
	}
	
	public static void updateThreadNumber() {
		if(threadnum == (numberOfThreads - 1)) {
			threadnum = 0;
			return;

		} else {
			threadnum++;
		}

	}

	public static void printEntryList(LinkedList<Entry> E) {
		if (E.size() == 0) {
			System.out.println("List is Empty!");
		}

		for(int i = 0; i < E.size(); i++) {
			System.out.println("Entry(" + i + "): " + "Total Pop: " + E.get(i).getTotalPop() + ", State: " + E.get(i).getState() + ", County: " + E.get(i).getCounty() + ", Income: " + E.get(i).getIncome() + ", Poverty: " + E.get(i).getPoverty());
		}
	}

	public static void printEntryListSegment(LinkedList<Entry> E, int start, int end) {
		if (E.size() == 0) {
			System.out.println("List is Empty!");
		}

		if(start < 0 || end < 0 || start > E.size() || end > E.size()) {
			System.out.println("Invalid Start or End Value. Please try again");
		}

		for(int i = start; i < end; i++) {
			System.out.println("Entry(" + i + "): " + "Total Pop: " + E.get(i).getTotalPop() + ", State: " + E.get(i).getState() + ", County: " + E.get(i).getCounty() + ", Income: " + E.get(i).getIncome() + ", Poverty: " + E.get(i).getPoverty());
		}

	}
}
