package objects;
/*
For the final project, we'll focus on 5 attributes:
Total Population - Column D, Int, Column index 3
State - Column B, String, Column index 1
County - Column C, String, Column index 2
Income - Column N, Int, Column index 14
Poverty - Column R, Float, Column index 18

We want our excel reader to focus on these columns.
 */

//Libraries
import java.io.File;  
import java.io.FileInputStream;  
import java.util.Iterator;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;   

import java.util.LinkedList;

//imports
import objects.Entry;

public class ExcelReader {


	public void readExcelFile(LinkedList<Entry> List) {

		try {
			File file = new File("US Census data 2017.xlsx"); //Create a new file instance
			FileInputStream fis = new FileInputStream(file); //obtain bytes from file.

			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0); // create Sheet object to retrieve sheet
			Iterator<Row> itr = sheet.iterator(); //iterating over excel file  

			for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) { //starting on the first row with values...
				Row row = sheet.getRow(rowIndex);
				if (row != null) { //if the row exists...

					//Get the values from the excel sheet at these specific columns
					Cell totalpop = row.getCell(3); //Total pop is in column D
					Cell state = row.getCell(1); //State is in column B
					Cell county = row.getCell(2); // County is in Column C
					Cell income = row.getCell(13); // Income is in column N
					Cell poverty = row.getCell(17); // Poverty is in column R

					//Convert Cell values to entry objects
					Entry E = new Entry();
					E.setTotalPop((int) totalpop.getNumericCellValue());
					E.setState(state.getStringCellValue());
					E.setCounty(county.getStringCellValue());
					E.setIncome((int) income.getNumericCellValue());
					E.setPoverty((float) poverty.getNumericCellValue());
					List.add(E);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}






}
