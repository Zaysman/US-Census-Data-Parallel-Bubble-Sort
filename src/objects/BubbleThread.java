package objects;

//Libraries
import java.util.LinkedList;

import objects.Entry;

public class BubbleThread implements Runnable {

	private Thread t; //The thread object that runs in each thread.
	private String ThreadName; //A bubble thread's name
	private int ThreadNumber; //A bubble threads ThreadNumber
	private LinkedList<Entry> list; //Each thread will have a list of the entries it's going to bubble sort. It will
	private LinkedList<Entry> result; //Once a thread is finished, it will send it's sorted list to the results so the main thread can perform the merge.
	private LinkedList<Entry>[] results; //every thread has a reference to where the final results are stored.

	private String parameter; //What parameter we'll perform bubblesort based on

	//Constructor
	public BubbleThread() {
		t = null;
		ThreadName = null;
		ThreadNumber = -1;
		list = new LinkedList<Entry>();

	}

	public BubbleThread(String name) {
		t = null;
		ThreadName = name;
		ThreadNumber = -1;
		list = new LinkedList<Entry>();
		result = new LinkedList<Entry>();

	}

	//accessor Methods
	public String getThreadName() {
		return ThreadName;
	}

	public int getThreadNumber() {
		return ThreadNumber;
	}

	public LinkedList<Entry> getEntryList() {
		return list;
	}

	public LinkedList<Entry> getResultsList() {
		return result;
	}


	//Modifier Methods
	public void setThreadName(String name) {
		ThreadName = name;
	}

	public void setThreadNumber(int num) {
		ThreadNumber = num;
	}

	public void setEntryList(LinkedList<Entry> E) {
		list = E;
	}

	public void setResultsList(LinkedList<Entry>[] R) {
		results = R;
	}

	public void setParameter(String para) {
		parameter = para;
	}



	//normal methods

	public void bubbleSort() {
		if(this.parameter.compareTo("Total Population") == 0 || this.parameter.compareTo("TotalPop") == 0 ) {
			bubbleSortByTotalPopulation(list);
			
		} else if (this.parameter.compareTo("State") == 0) {
			bubbleSortByState(list);

		} else if (this.parameter.compareTo("County") == 0) {
			bubbleSortByCounty(list);

		} else if (this.parameter.compareTo("Income") == 0) {
			bubbleSortByIncome(list);

		} else if (this.parameter.compareTo("Poverty") == 0) {
			bubbleSortByPoverty(list);
			
		} else {
			System.out.println("No Valid sorting parameter specified, sorting by TotalPop");
			bubbleSortByTotalPopulation(list);
		}
	}
	
	public void bubbleSortByTotalPopulation(LinkedList<Entry> E) {
		int n = E.size();

		for(int i = 0; i < (n-1); i++) {
			for(int j = 0; j < (n-i)-1; j++) {
				if(E.get(j).getTotalPop() > E.get(j+1).getTotalPop()) {
					swap(E, j, j+1);	
				}
			}
		}
		result = E;
		results[ThreadNumber] = E;
	}
	
	public void bubbleSortByState(LinkedList<Entry> E) {
		int n = E.size();

		for(int i = 0; i < (n-1); i++) {
			for(int j = 0; j < (n-i)-1; j++) {
				if(E.get(j).getState().compareTo(E.get(j+1).getState()) > 0) {
					swap(E, j, j+1);
				}
			}
		}
		result = E;
		results[ThreadNumber] = E;
	}
	
	public void bubbleSortByCounty(LinkedList<Entry> E) {
		int n = E.size();

		for(int i = 0; i < (n-1); i++) {
			for(int j = 0; j < (n-i)-1; j++) {
				if(E.get(j).getCounty().compareTo(E.get(j+1).getCounty()) > 0) {
					swap(E, j, j+1);
				}
			}
		}
		result = E;
		results[ThreadNumber] = E;
	}
	
	public void bubbleSortByIncome(LinkedList<Entry> E) {
		int n = E.size();

		for(int i = 0; i < (n-1); i++) {
			for(int j = 0; j < (n-i)-1; j++) {
				if(E.get(j).getIncome() > E.get(j+1).getIncome()) {
					swap(E, j, j+1);	
				}
			}
		}
		result = E;
		results[ThreadNumber] = E;
	}
	
	public void bubbleSortByPoverty(LinkedList<Entry> E) {
		int n = E.size();

		for(int i = 0; i < (n-1); i++) {
			for(int j = 0; j < (n-i)-1; j++) {
				if(E.get(j).getPoverty() > E.get(j+1).getPoverty()) {
					swap(E, j, j+1);	
				}
			}
		}
		result = E;
		results[ThreadNumber] = E;
	}
		
	public void swap(LinkedList<Entry> E, int index1, int index2) {
		Entry temp = E.get(index1);
		E.set(index1, E.get(index2));
		E.set(index2, temp);
	}


	
	@Override
	public void run() {
		// TODO Auto-generated method stub

		bubbleSort(); //Thread sorts its lists.
		result = list;
	}
	
	public void Start(String parameter) {
		setParameter(parameter);
		if(ThreadNumber < 0) {
			System.out.println("Thread " + ThreadNumber + " is Invalid. Thread cannot start.");
			return;
		}

		System.out.println("Starting Thread: " + ThreadName);
		if(t == null) {
			t = new Thread(this, ThreadName);
			t.start();
		}
	}
	
	
	

}
