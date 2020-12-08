package objects;
//libraries
import java.io.*;
import java.util.LinkedList;

//objects
import objects.Entry;



public class FileWriteObject {

	public void writeToFile(LinkedList<Entry> list, String fileName) {
		File file = new File(fileName);


		//If the file doesn't exist, we create a new one with the file name passed to the method.
		if(file.exists() == false) {
			try {
				file.createNewFile();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			///create a file writer object to write to the file
			FileWriter writer = new FileWriter(file);
			
			//create the string that we write to the file.
			String fileContents = new String();
			
			for(int i = 0; i < list.size(); i++) {
				fileContents += "Entry(" + i + "): " + list.get(i).toString() + "\n";
			}
			
			writer.write(fileContents);
			writer.flush();
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeToFile(LinkedList<Entry> list, String fileName, String sortingParam) {
		File file = new File(fileName);


		//If the file doesn't exist, we create a new one with the file name passed to the method.
		if(file.exists() == false) {
			try {
				file.createNewFile();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			///create a file writer object to write to the file
			FileWriter writer = new FileWriter(file);
			
			//create the string that we write to the file.
			String fileContents = new String();
			fileContents = createFileContents(list, fileContents, sortingParam);
			
			/*
			for(int i = 0; i < list.size(); i++) {
				fileContents += "Entry(" + i + "): " + list.get(i).toString() + "\n";
			}
			*/
			
			
			
			writer.write(fileContents);
			writer.flush();
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String createFileContents(LinkedList<Entry> list, String contents, String sortingParam) {
		if(sortingParam.compareTo("TotalPop") == 0) {
			for(int i = 0; i < list.size(); i++) {
				contents += "Entry(" + i + "): " + list.get(i).toString() + "\n";
			}
			
			return contents;
			
		} else if (sortingParam.compareTo("State") == 0) {
			for(int i = 0; i < list.size(); i++) {
				contents += "Entry(" + i + "): " + list.get(i).toStringByState() + "\n";
			}
			return contents;
			
		} else if (sortingParam.compareTo("County") == 0) {
			for(int i = 0; i < list.size(); i++) {
				contents += "Entry(" + i + "): " + list.get(i).toStringByCounty() + "\n";
			}
			return contents;
		} else if (sortingParam.compareTo("Income") == 0) {
			for(int i = 0; i < list.size(); i++) {
				contents += "Entry(" + i + "): " + list.get(i).toStringByIncome() + "\n";
			}
			return contents;
			
		} else if (sortingParam.compareTo("Poverty") == 0) {
			for(int i = 0; i < list.size(); i++) {
				contents += "Entry(" + i + "): " + list.get(i).toStringByPoverty() + "\n";
			}
			return contents;
			
		} else {
			for(int i = 0; i < list.size(); i++) {
				contents += "Entry(" + i + "): " + list.get(i).toString() + "\n";
			}
			
			return contents;
		}
		
		
	}
	
}
