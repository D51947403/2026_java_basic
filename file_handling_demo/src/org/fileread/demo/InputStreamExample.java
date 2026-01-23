package org.fileread.demo;
import java.io.FileInputStream;  
  
public class InputStreamExample {  
    public static void main(String[] args) throws Exception {  
        FileInputStream fin = new FileInputStream("D:\\2026_java_notes\\answer.txt");  
        int i;  
        while ((i = fin.read()) != -1) { 
        	 //System.out.println( i);  
           // System.out.println((char) i); 
            System.out.print((char) i);  
        }  
        fin.close();  
    }  
}  