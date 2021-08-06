package ml;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Load {
	
	// ArrayList of digits
	ArrayList<Digits> digitsList = new ArrayList<Digits>();
	
	// Generating Getter
	public ArrayList<Digits> getDigitsList() {
		return digitsList;
	}

	// Constructor
	public Load() {
	
	
	}
	
	// Stores digits from the file
	public void loadFile(String filePath) {
		
		Integer[] numbers;
		Integer attribute;
		
		// Clearing in case there was a loaded file beforehand
		digitsList.clear();
		
		// Loading and reading the file, then storing the digits and attributes
		try {
			
			// Creating File and Scanner object
			File csvFile = new File(filePath);
			
			Scanner scan = new Scanner(csvFile);
			
			// Delimiter to handle the file
			scan.useDelimiter("[ ,\r\n]");
			
			// Looping through each line in the file
			while (scan.hasNextLine()) {
				
				// Loop 64 times & store values in numbers[]
				numbers = new Integer[64];
				for (int i=0; i<64; i++) {
					numbers[i] = scan.nextInt();
				}
				// Store attribute
				attribute = scan.nextInt();
				
				// Go to next line
				scan.nextLine();
				
				// Creating a digit to store the bitmap and attribute
				Digits digit = new Digits(numbers, attribute);
				
				digitsList.add(digit);
			}
		}
		
		catch (FileNotFoundException e) {
		    System.out.println("An error occurred loading the file.");
		    e.printStackTrace();
		}
	}
}

