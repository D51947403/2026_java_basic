package org.fileread.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFileDemo {
	public static void main(String[] args) {
	    File myObj = new File("D:\\2023-interview\\interview-2023.txt");

	    // try-with-resources: Scanner will be closed automatically
	    try (Scanner myReader = new Scanner(myObj)) {
	      while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();
	        System.out.println(data);
	      }
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	  }

}
