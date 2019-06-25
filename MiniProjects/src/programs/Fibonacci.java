package programs;

public class Fibonacci {
	
	public static long fibonacciRecursive(long a, long b, long limit) {
		
		System.out.println(b);
		
		if (b + (a+b) > limit) {
			System.out.println(a + b);
			System.out.println("\n(limit was: " + limit + ")");
			return a + b;
		}
		
		return fibonacciRecursive(b, (a+b), limit);
	}
	
	public static long fibonacciLoop(long a, long b, long limit) {
		
		while (b + (a+b) < limit) {
			System.out.println(b);
			
			long temp = a + b;
			a = b;
			b = temp;
		}
		
		System.out.println(a + b);
		System.out.println("\n(limit was: " + limit + ")");
		
		return a + b;
	}
}
