package org.fileread.demo;

import java.io.Console;  
// run this program on command prompt 
public class ReadPasswordTest {
	public static void main(String args[]){    
		Console c=System.console();    
		System.out.println("Enter password: ");    
		char[] ch=c.readPassword();    
		String pass=String.valueOf(ch);//converting char array into string    
		System.out.println("Password is: "+pass);    
		}    
}
