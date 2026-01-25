package org.fileread.demo;
import java.io.Reader;
import java.io.FileReader;

public class FileReaderDemo1 {

	 public static void main(String[] args) {  
	        try {  
	            Reader reader = new FileReader("D:\\2026_java_notes\\answer.txt");  
	        	//FileReader reader2 = new FileReader("D:\\2026_java_notes\\file.txt"); 
	            int data = reader.read();  
	            while (data != -1) {  
	                System.out.print((char) data);  
	                data = reader.read();  
	            }  
	           reader.close();  
	        } catch (Exception ex) {  
	            System.out.println(ex.getMessage());  
	        }  
	 }
}
