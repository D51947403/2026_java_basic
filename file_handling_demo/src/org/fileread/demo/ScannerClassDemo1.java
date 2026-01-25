package org.fileread.demo;

import java.util.Scanner;

public class ScannerClassDemo1 {
	   public static void main(String args[]){                         
	          String s = "Hello, Welcome to TpointTech.";    
	          //Create a scanner Object and pass the string into it    
	          Scanner scan = new Scanner(s);    
	          //Check if the scanner has a token    
	          System.out.println("Boolean Result: " + scan.hasNext());    
	          //Print the string    
	          System.out.println("String: " +scan.nextLine());    
	          scan.close();             
	          System.out.println("--------Enter Your Details-------- ");    
	          Scanner in = new Scanner(System.in);    
	          System.out.print("Enter your name: ");      
	          String name = in.next();     
	          System.out.println("Name: " + name);             
	          System.out.print("Enter your age: ");    
	          int i = in.nextInt();    
	          System.out.println("Age: " + i);    
	          System.out.print("Enter your salary: ");    
	          double d = in.nextDouble();    
	          System.out.println("Salary: " + d);           
	          in.close();             
	          }  
}
