package ml;

public class Digits {
	
	//Initializing variables
	private Integer[] numbers;
	private Integer attribute;
	private Integer guess;
	
	//Constructor
	public Digits (Integer[] numbers, Integer attribute) {
		this.numbers = numbers;
		this.attribute = attribute;
	}
	
	//Getters and setters
	public Integer getNumberAtIndex(Integer index) {
		return numbers[index];
	}
	
	public Integer[] getNumbers() {
		return numbers;
	}

	public void setNumbers(Integer[] numbers) {
		this.numbers = numbers;
	}

	public Integer getAttribute() {
		return attribute;
	}

	public void setAttribute(Integer attribute) {
		this.attribute = attribute;
	}

	public Integer getGuess() {
		return guess;
	}

	public void setGuess(Integer guess) {
		this.guess = guess;
	}
 
}