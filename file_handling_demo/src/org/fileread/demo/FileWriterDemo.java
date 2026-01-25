package org.fileread.demo;

import java.io.FileWriter;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.IOException;

public class FileWriterDemo {

	public static void main(String[] args) throws IOException {
	
		        try {
		          
		            // Create a FileWriter to write to a file named "example.txt"
		          //  FileWriter w = new FileWriter("D:\\2026_java_notes\\example.txt");

		        	 Writer w = new FileWriter("D:\\2026_java_notes\\example.txt");
		        	 
		            // Write a simple message to the file
		            w.write("Hello, World! This is my program of file writer");

		            // Close the writer
		            w.close();

		            System.out.println("Message written");
		        }
		        catch (IOException e) {
		            System.out.println("An error occurred: " + e.getMessage());
		        }
		        
		        // try --with resource --jdk1.7
		        try (Writer writer = new FileWriter("D:\\2026_java_notes\\buffered.txt");
		        	BufferedWriter bufferWriter = new BufferedWriter(writer )) {
		        	bufferWriter.write(
		                "BufferedWriter makes writing more efficient.");
		        	bufferWriter.write(
		                "\nIt reduces disk I/O by using a buffer.");
		            System.out.println(
		                "Data written using BufferedWriter.");
		    }
		
	}
	

}
