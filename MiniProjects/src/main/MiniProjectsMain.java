package main;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;

public class MiniProjectsMain {
	
	private static ArrayList<Class<?>> programList = null;
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to Joeri's mini projects. \n");
		System.out.println("Please enter one of the projects below: \n");
		
		try {
			// Get the package with name "programs" and loop through the first result
			Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources("./programs");
			
		    while (resources.hasMoreElements()) {
		        URL url = resources.nextElement();
		        //System.out.println(url);
		        //System.out.println(new Scanner((InputStream) url.getContent()).useDelimiter("\\A").next());
		        
		        // Setup a Scanner object to get all public classes from the "programs" package
		        InputStream inputStream = (InputStream) url.getContent();
		        Scanner classScanner = new Scanner(inputStream);
		        classScanner.useDelimiter("\\A");
		        
		        programList = new ArrayList<Class<?>>();
		        
		        while (classScanner.hasNext()) {
		        	String className = "programs." + classScanner.nextLine();
		        	className = className.replaceAll(".class", "");
		        	
		        	Class<?> myClass = Class.forName(className);
		        	
		        	// Add only Classes that are public
		        	if (Modifier.isPublic(myClass.getModifiers())) {
		        		programList.add(myClass);
		        		//System.out.println("class added with name: " + className);
		        	}
		        }
		        
		        classScanner.close();
		        inputStream.close();
		        
		        Scanner inputScanner = new Scanner(System.in);
		        
		        // MAIN PROGRAM LOOP
		        while (true) {
		        	
		        	Class<?> chosenClass = letUserPickOneClass(inputScanner, programList);
		        	
		        	Method chosenMethod = letUserPickOneMethod(inputScanner, chosenClass.getDeclaredMethods());
		        	
		        	Constructor<?>[] constructors = chosenClass.getConstructors();
		        	boolean containsOnlyParameterlessConstructor = true;
		        	
		        	Constructor<?> constructorWithoutParams = null;
 		        	
		        	for (int i = 0; i < constructors.length; ++i) {
		        		if (constructors[i].getParameterCount() == 0) {
		        			constructorWithoutParams = constructors[i];
		        		}
		        		else {
		        			containsOnlyParameterlessConstructor = false;
		        		}
		        	}
		        	
		        	
		        	Object instance = null;
		        	
		        	if (containsOnlyParameterlessConstructor) {
		        		System.out.println("Class only contains a constructor without Parameters");
		        		System.out.println("An instance has been generated\n");
		        		instance = constructorWithoutParams.newInstance(new Object[0]);
		        	}
		        		
		        	else {
		        		Constructor<?> chosenConstructor = letUserPickOneConstructor(inputScanner, constructors);
		        		instance = buildObject(chosenConstructor);
		        	}
		        	
		        	
		        	
		        	// get and handle parameters 
		        	Parameter[] parameters = chosenMethod.getParameters();
		        	Object[] paramValues = new Object[parameters.length];
		        	
		        	@SuppressWarnings("resource")
					Scanner scanner = new Scanner(System.in);
		        	int counter = 0;
		        	
		        	for (Parameter param : parameters) {
		        		System.out.print("enter value for parameter (" + param.getType().getSimpleName() + ") " + param.getName() + " = ");
		        		
		        		Object paramValue = null;
		        		
		        		if (param.getType() == String.class)
		        			paramValue = scanner.next();
		        		else if (param.getType() == int.class || param.getType() == Integer.class)
		        			paramValue = scanner.nextInt();
		        		else if (param.getType() == long.class || param.getType() == Long.class)
		        			paramValue = new Long(scanner.nextLong());
		        		else if (param.getType() == double.class || param.getType() == Double.class)
		        			paramValue = new Double(scanner.nextDouble());
		        		else {
		        			Class<?> paramClass = param.getType();
		        			
		        			Constructor<?> constructor = letUserPickOneConstructor(inputScanner, paramClass.getConstructors());
		        			
		        			paramValue = buildObject(constructor);
		        		}
		        		
		        		paramValues[counter] = paramValue;
		        		++counter;
		        	}
		        	
		        	System.out.println("\n******************************");
		        	Object callResult = chosenMethod.invoke(instance, paramValues);
		        	System.out.println("******************************");
		        	
		        	if (chosenMethod.getReturnType() != void.class)
		        		System.out.println(String.format("\nMethod returned: (%s) %s", callResult.getClass().getSimpleName(), callResult));
		        	
		        	System.out.println("\nMethod execution has finished. \nPress ENTER to return to the Classes list...");
		        	
		        	System.in.read();
		        	System.out.println("\n\n\n");
		        }

		        //Object o = url.getResource();
		        
		        // TODO: vind een manier om ook de package name voor de classname te krijgen of gebruik default package
		        //Class<?> myClass = Class.forName(className);
		        /*
		        Class<?> myClass = Class.forName("programs.Fibonacci");
		        Method[] methods = myClass.getMethods();
		        
		        Method[] declaredMethods = myClass.getDeclaredMethods();
		        
		        for (Method method : declaredMethods)
		        	System.out.println(method.getName());
		        	*/
		    }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	private static Class<?> letUserPickOneClass(Scanner inputScanner, ArrayList<Class<?>> programList) {
		
		// list all classes and let the user pick an option
		System.out.println("Programs: \n");
		
		for (Class<?> program : programList) {
			System.out.println("  " + programList.indexOf(program) + ". " + program.getSimpleName());
		}
		
		System.out.print("\nEnter a number: ");
		int userInput = inputScanner.nextInt();
		
		if (userInput > -1 && userInput < programList.size()) {
			Class<?> selectedClass = programList.get(userInput);
			
			System.out.println("\n\nPublic methods of class " + selectedClass.getSimpleName() + ":");
			
			return selectedClass;
		}
		else {
			System.out.println("\ninvalid number, please choose a number between (0 and " + (programList.size() - 1) + ") \n");
			
			// recursively call this function untill the user has chosen a valid class
			return letUserPickOneClass(inputScanner, programList);
		}
	}
	
	
	private static Method letUserPickOneMethod(Scanner inputScanner, Method[] methods) {
		// list all methods and let the user pick an option
		System.out.println("Methods: \n");

		for (int i = 0; i < methods.length; ++i) {
			System.out.print(String.format("  %d. %s", i, methods[i].getName()));
			
			String parameters = "";
			try {
				parameters = Utils.generateParameterString(methods[i].getParameters());
			} catch (Exception e) {
				System.out.print("(parameters contain a malformed parameter, can't be shown)");
			}
			
			System.out.print(parameters + " : " + methods[i].getReturnType().getSimpleName() + "\n");
		}

		System.out.print("\nEnter a number: ");
		int userInput = inputScanner.nextInt();

		if (userInput > -1 && userInput < methods.length) {
			Method selectedMethod = methods[userInput];

			System.out.println("\n\nYou chose method: " + selectedMethod.getName());

			return selectedMethod;
		} 
		else {
			System.out.println(
					"\ninvalid number, please choose a number between (0 and " + (methods.length - 1) + ") \n");

			// recursively call this function untill the user has chosen a valid method
			return letUserPickOneMethod(inputScanner, methods);
		}
	}
	
	private static Constructor<?> letUserPickOneConstructor(Scanner inputScanner, Constructor<?>[] constructors) {
		// if there is only one constructor, return it
		if (constructors.length == 1) {
			return constructors[0];
		}
		
		// list all constructors and let the user pick an option
		System.out.println("Constructors: \n");
		
		String className = constructors[0].getClass().getSimpleName();
		String parameterString;
		
		for (int i = 0; i < constructors.length; ++i) {
			parameterString = Utils.generateParameterString(constructors[i].getParameters());
			System.out.println(String.format("  %d. %s %s", i, className, parameterString));
		}

		System.out.print("Enter a number: ");
		int userInput = inputScanner.nextInt();

		if (userInput > -1 && userInput < constructors.length) {
			Constructor<?> selectedConstructor = constructors[userInput];

			System.out.println("\n\nYou chose method: " + selectedConstructor.getName());

			return selectedConstructor;
		} 
		else {
			System.out.println(
					"\ninvalid number, please choose a number between (0 and " + (constructors.length - 1) + ") \n");

			// recursively call this function untill the user has chosen a valid constructor
			return letUserPickOneConstructor(inputScanner, constructors);
		}
	}
	
	private static Object buildObject(Constructor<?> constructor) {
		
		Parameter[] parameters = constructor.getParameters();
    	Object[] paramValues = new Object[parameters.length];
    	
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
    	int counter = 0;
    	
    	for (Parameter param : parameters) {
    		System.out.print("enter value for parameter (" + param.getType().getSimpleName() + ") " + param.getName() + " = ");
    		
    		Object paramValue = null;
    		
    		if (param.getType() == String.class)
    			paramValue = scanner.next();
    		else if (param.getType() == int.class || param.getType() == Integer.class)
    			paramValue = scanner.nextInt();
    		else if (param.getType() == long.class || param.getType() == Long.class)
    			paramValue = new Long(scanner.nextLong());
    		else if (param.getType() == double.class || param.getType() == Double.class)
    			paramValue = new Double(scanner.nextDouble());
    		
    		paramValues[counter] = paramValue;
    		++counter;
    	}
    	
    	try {
			Object createdObject = constructor.newInstance(paramValues);
			return createdObject;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
