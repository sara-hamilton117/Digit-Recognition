package ml;

import java.util.ArrayList;

public class KNN {
	
	// ArrayList of training and test set
	ArrayList<Digits> trainingSet = new ArrayList<Digits>();
	ArrayList<Digits> testSet = new ArrayList<Digits>();
	
	// Getters and Setters
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
	public KNN(ArrayList<Digits> trainingSet, ArrayList<Digits> testSet) {
		this.trainingSet = trainingSet;
		this.testSet = testSet;
	}
	
	// Code used for debugging
	
//	public void printSize() {
//		System.out.println("training: " + trainingSet.size());
//		System.out.println("test: " + testSet.size());
//		
//		System.out.print("training set first digit: " );
//		Integer[] bitmap = trainingSet.get(0).getNumbers();
//		
//		for (int i = 0; i < bitmap.length; i++) {
//			System.out.print(bitmap[i] + ", ");
//		}
//		System.out.println();
//		
//		System.out.print("test set first digit: " );
//		bitmap = testSet.get(0).getNumbers();
//		
//		for (int i = 0; i < bitmap.length; i++) {
//			System.out.print(bitmap[i] + ", ");
//		}
//		System.out.println();
//			
//	}

	// Guessing the attribute for 1 nearest neighbour
	public void NN() {
		
		// Looping through test set
		for (int i=0; i<testSet.size(); i++) {
			
			// Setting distance to max value and guess to -1
			Double distance = Double.MAX_VALUE;
			Integer guess = -1;
			
			// Looping through training set
			for (int j=0; j<trainingSet.size(); j++) {
				
				// Calculating distance between current digit from test set and training set
				Double tempDist = eDist(testSet.get(i),trainingSet.get(j));
						
				// If distance is smaller than previous smallest distance, store new distance and attribute guess
				if (tempDist < distance) {
					distance = tempDist;
					guess = trainingSet.get(j).getAttribute();
				}	
			}
			
			// Set attribute of closest neighbour
			testSet.get(i).setGuess(guess);
		}
	}
	
	// Guessing the attribute for k nearest neigbours
	public void kNN(Integer k) {
		
		// Variables
		Integer[] attributesCounter = new Integer[10];
		Integer[] attributes = new Integer[k];
		Double[] distances = new Double[k];
		Integer guessedAttribute;
		Integer mostGuessedAttribute;

		// Looping through the test set
		for (int i=0; i<testSet.size(); i++) {
			
			// Initializing array of nearest attributes
			for (int p=0; p<attributes.length; p++) {
				attributes[p] = -1;
			}
			
			// Initializing array of distances
			for (int p=0; p<distances.length; p++) {
				distances[p] = Double.MAX_VALUE;
			}	
		
			// Looping through training set
			for (int j=0; j<trainingSet.size(); j++) {
				
				// Calculating distance between current digit from test set and training set
				Double tempDist = eDist(testSet.get(i),trainingSet.get(j));
				
				// Storing attribute from training set digit
				Integer tempAttr = trainingSet.get(j).getAttribute();
				
				// Looping through array of closest distances
				for (int l=0; l<distances.length; l++) {
					
					// If temp distance is smaller than distance at current index, swap them and the attributes
					if (tempDist<distances[l]) {
						
						// Storing distance and attribute at current index
						Double nextDist = distances[l];
						Integer nextAttribute = attributes[l];
						
						// Storing temp distance and attribute 
						distances[l] = tempDist;
						attributes[l] = tempAttr;
						
						// Storing distance and attribute at current index into temp distance and attribute
						tempDist = nextDist;
						tempAttr = nextAttribute;
					}
				}					
			}	
			
			// Looping through all attributes counter
			for (int p=0; p<attributesCounter.length; p++) {
				
				// Initializing counter
				attributesCounter[p] = 0;
			}
			
			// Looping through all attributes
			for (int j=0; j<attributes.length; j++) {
				
				// Incrementing counter at attribute index
				attributesCounter[attributes[j]]++;
			}
			
			// Resetting the variables
			mostGuessedAttribute = -1;
			guessedAttribute = -1;
			
			// Looping through attribute counters
			for (int j=0; j<attributesCounter.length;j++) {
				
				// If attribute at current index is bigger than most guessed, store it as most guessed
				if (mostGuessedAttribute<attributesCounter[j]) {
					mostGuessedAttribute=attributesCounter[j];
					guessedAttribute=j;
				}
			}
			
			// Set digit's attribute as most guessed
			testSet.get(i).setGuess(guessedAttribute);
		}	
	}
	
	
	// Euclidian Distance between 2 digits
	public Double eDist(Digits firstNum, Digits secondNum) {
		
		// Variable
		Double eDist = 0.0;
		
		// Looping through the bitmap
		for (int k=0; k<64; k++) {
			
			// Subtracting attributes at the same position and squaring the result
			eDist += Math.pow(firstNum.getNumbers()[k] - secondNum.getNumbers()[k], 2) ;
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
		
		System.out.println("Accuracy for KNN = " + accuracy + "%");
	}
}
