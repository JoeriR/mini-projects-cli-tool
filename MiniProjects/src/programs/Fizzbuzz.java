package programs;

public class Fizzbuzz {
	
	public static void fizzbuzz(int start, int end) {
		
		if (start > end) {
			System.out.println("start parameter is bigger than end parameter");
			return;
		}
		
		for (int i = start; i <= end; ++i) {
			if (i % 3 == 0 && i % 5 == 0)
				System.out.println("fizzbuzz");
			else if (i % 3 == 0)
				System.out.println("fizz");
			else if (i % 5 == 0)
				System.out.println("buzz");
			else if (i % 3 != 0 && i %5 != 0)
				System.out.println(i);
		}
	}
}
