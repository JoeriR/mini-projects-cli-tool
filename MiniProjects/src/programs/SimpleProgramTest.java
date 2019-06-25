package programs;

import java.util.Scanner;

public class SimpleProgramTest {
	
	public void printTest() {
		System.out.println("Hello World, this is printTest!");
	}
	
	public void redPrintTest() {
		System.err.println("Do not panic, this is just red text \n");
	}
	
	public void readAndPrintTest() {
		System.out.println("You called the method: printTest");
		
		@SuppressWarnings("resource")
		Scanner inputScanner = new Scanner(System.in);
		
		System.out.print("Please enter an int: ");
		
		int number = inputScanner.nextInt();
		
		System.out.println("\nYou entered the number: " + number);
	}
	
	public int plus(int a, int b) {
		int result = a + b;
		System.out.println("" + a + " + " + b + " = " + result);
		return result;
	}

}
