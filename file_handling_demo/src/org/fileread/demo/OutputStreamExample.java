package org.fileread.demo;

import java.io.FileOutputStream;

public class OutputStreamExample {  
    public static void main(String[] args) throws Exception {  
        FileOutputStream fout = new FileOutputStream("D:\\2026_java_notes\\answer.txt");  
        String content = "Student exam answers";  
        fout.write(content.getBytes());  
        fout.close();  
        System.out.println("Data written successfully");  
    }  
    
     
}  