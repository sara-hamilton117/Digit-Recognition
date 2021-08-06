package ml;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		// Initializing arraylists of data sets :)
		ArrayList<Digits> trainingDigits;
		ArrayList<Digits> testDigits;
	
		// Loading file using filepath
		Load loadFile = new Load();
		
		// Path string to be changed leading to data set
		String filePath = "C:\\...\\dataset1.csv";

		loadFile.loadFile(filePath);

		trainingDigits = (ArrayList<Digits>)loadFile.getDigitsList().clone();
		
		// Path string to be changed leading to data set
		filePath = "C:\\...\\dataset2.csv";
		
		loadFile.loadFile(filePath);
		
		// Cloning contents of file into arraylist
		testDigits =  (ArrayList<Digits>)loadFile.getDigitsList().clone();
		
		// Running KNN with train set and test set passed to it
		KNN knn = new KNN(trainingDigits, testDigits);
		//Initializing K
		knn.kNN(100);
		knn.accuracy();
		
		// Running KMeans with train set and test set passed to it
		KMeans kmeans = new KMeans(trainingDigits, testDigits);
		kmeans.findAvg();
		kmeans.kMeans();
		kmeans.accuracy();
	}

}
