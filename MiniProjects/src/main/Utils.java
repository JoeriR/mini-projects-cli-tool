package main;

import java.lang.reflect.Parameter;

public class Utils {
	
	public static String generateParameterString(Parameter[] parameters) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		
		for (Parameter param : parameters)
			sb.append(String.format("%s %s, ", param.getType().getSimpleName(), param.getName()));
		
		if (sb.length() > 1)
			sb.setCharAt(sb.toString().length() - 1, ')');
		else
			sb.append(")");
		
		return sb.toString();
	}
	
}
