package org.fileread.demo;
import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class BufferedReaderExample {

	public static void main(String[] args) {
		try {  
            Reader reader = new FileReader("D:\\2026_java_notes\\answer.txt");  
            BufferedReader br = new BufferedReader(reader);  
            String line;  
            while ((line = br.readLine()) != null) {  
                System.out.println(line);  
            }  
            br.close(); 
            reader.close();
        } catch (IOException e) {  
            System.out.println(e);  
        }  

	}

}
