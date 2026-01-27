package org.fileread.demo;

public class PrimitiveToStringConversion {

	public static void main(String[] args) {
	
	            int intNum = 1234;
	            String intStr=String.valueOf(intNum);
	            System.out.println("String value: " + intStr);
	            
	            long longNum = 123478;
	            String longStr=String.valueOf(longNum);
	            System.out.println("String value: " + longStr);
	            
	            float floatNum = 123478.89f;
	            String floatStr=String.valueOf(floatNum);
	            System.out.println("String value: " + floatStr);
	            
	            boolean booleanValue =true;
	            String booleanStr=String.valueOf(booleanValue);
	            System.out.println("String value: " + booleanStr);

	}

}
