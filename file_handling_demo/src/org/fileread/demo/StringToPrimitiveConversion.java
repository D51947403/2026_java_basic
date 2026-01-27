package org.fileread.demo;

public class StringToPrimitiveConversion {

	public static void main(String[] args) {
		
		// String to int
		 String intStr = "126";
		 String floatStr = "123.08";
		 String booleanStr="true";
	        try {
	            // Convert string to primitive number using Integer.parseInt()
	            int intNum = Integer.parseInt(intStr);
	            System.out.println("Converted int value: " + intNum);
	            
	            long longNum = Long.parseLong(intStr);
	            System.out.println("Converted int value: " + longNum);
	            
	            float floatNum = Float.parseFloat(floatStr);
	            System.out.println("Converted float value: " + floatNum);
	            
	            double doubleNum = Double.parseDouble(floatStr);
	            System.out.println("Converted doubleNum value: " + doubleNum);
	            
	            
	            boolean booleanValue = Boolean.parseBoolean(booleanStr);
	            System.out.println("Converted booleanValue value: " + booleanValue);
	            
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid number format: " + e.getMessage());
	        }
	    
	        

	}

}
