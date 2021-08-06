package ml;

import java.util.ArrayList;

public class KMeans {
	
	// Array of counters and multi-dimension array of average digit (bitmapValues)
	Integer[] countersArr = new Integer[10];
	Integer[][] bitmapValues = new Integer[10][64];
	
	// ArrayList of training and test set
	ArrayList<Digits> trainingSet = new ArrayList<Digits>();
	ArrayList<Digits> testSet = new ArrayList<Digits>();
	
	// Getters and setters
	public ArrayList<Digits> getTrainingSet() {
		return trainingSet;
	}

	public void setTrainingSet(ArrayList<Digits> trainingSet) {
		this.trainingSet = trainingSet;
	}

	public ArrayList<Digits> getTestSet() {
		return testSet;
	}

	public void setTestSet(ArrayList<Digits> testSet) {
		this.testSet = testSet;
	}

	// Constructor
	public KMeans(ArrayList<Digits> trainingSet, ArrayList<Digits> testSet) {
		this.trainingSet = trainingSet;
		this.testSet = testSet;
		
		// Initiating counters and average arrays to 0
		for (int i=0; i<10; i++) {
			countersArr[i] = 0;
			
			for (int j=0; j<64; j++) {
				bitmapValues[i][j] = 0;
			}
		}
	}
	
	// Finds the average bitmap value for each attribute (0 to 9)
	public void findAvg() {
		
		// Looping through training set 
		for (int i=0; i<trainingSet.size(); i++) {
			
			// Incrementing counter at the attribute index
			countersArr[trainingSet.get(i).getAttribute()]++;
			
			// Adding the current bitmap values to the average
			for (int j=0; j<64; j++) {
				bitmapValues[trainingSet.get(i).getAttribute()][j] += trainingSet.get(i).getNumberAtIndex(j);
			}
		}
		
		// Calculating each average bitmap value
		for (int i=0; i<10; i++) {
			for (int j=0; j<64; j++) {
				
				// Dividing the total added average by the matching counter
				bitmapValues[i][j] = bitmapValues[i][j]/countersArr[i];
			}
		}
	}
	
	
	// Guessing the attribute of the bitmap with KMeans
	public void kMeans() {
		
		// Variables
		Integer closest;
		Double distance;
		Integer[] avgBitmap = new Integer[64];
		
		// Looping through test set
		for (int i=0; i<testSet.size(); i++) {
			
			// Resetting closest to -1 and setting the distance to the max value
			closest = -1;
			distance = Double.MAX_VALUE;
			
			// Finding closest average bitmap
			for (int j=0; j<10; j++) {
				for(int k=0; k<64; k++) {
					
					// Storing average bitmap for comparison
					avgBitmap[k] = bitmapValues[j][k];
				}
				
				// Comparing current closest distance to the distance between average and the digit
				if(distance > eDist(avgBitmap, testSet.get(i))) {
					closest = j;
					distance = eDist(avgBitmap, testSet.get(i));
				}
			}
			
			// Setting the attribute to the closest guess
			testSet.get(i).setGuess(closest);
		}
	}
	
	// Euclidian Distance between array of integers (average bitmap) and digit
	public Double eDist(Integer[] firstNum, Digits secondNum) {
		
		// Variable
		Double eDist = 0.0;
		
		// Looping through the bitmap
		for (int k=0; k<64; k++) {
			
			// Subtracting attributes at the same position and squaring the result
			eDist += Math.pow(firstNum[k] - secondNum.getNumbers()[k], 2) ;
		}
		
		// Square root of total distance
		eDist = Math.sqrt(eDist);
		return eDist;
	}
	
	
	// Calculates accuracy
	public void accuracy() {
		
		// Variables
		Double counter = 0.0;
		Double accuracy = 0.0;
		
		// Loops through test set
		for (Digits digit : testSet) {
			if (digit.getAttribute() == digit.getGuess()) {
				
				// Increment counter if guess is correct
				counter++;
			}
		}
		
		// Getting percentage value and printing result
		accuracy = counter / testSet.size() * 100;
		
		System.out.println("Accuracy for KMeans = " + accuracy + "%");
	}
	
}
