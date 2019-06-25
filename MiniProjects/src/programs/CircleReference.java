package programs;

class MyClass {
	public int number = 1;

	public MyClass otherInstance = null;
}

public class CircleReference {

	public void doStuff() {

		MyClass object1 = new MyClass();
		MyClass object2 = new MyClass();
		
		object1.number = 1;
		object2.number = 2;
		
		object1.otherInstance = object2;
		object2.otherInstance = object1;
		
		object1.otherInstance.otherInstance.otherInstance.otherInstance.number = 99999;
		
		System.out.println("object1.number = " + object1.number);
		System.out.println("object2.number = " + object2.number);
	}
}
