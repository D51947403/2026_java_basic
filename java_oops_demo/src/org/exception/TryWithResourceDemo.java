package org.exception;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
// introduced in JDK 1.7 version
public class TryWithResourceDemo {

	public static void main(String[] args) throws IOException {
		 BufferedReader reader = null;
		 try{
	             reader = new BufferedReader(
	                new FileReader("sample.txt"));
	            String line;

	            while ((line = reader.readLine()) != null) {
	                System.out.println(line);
	            }

	        }
	        catch (IOException e) {
	            System.err.println(
	                "An error occurred while reading the file: "
	                + e.getMessage());
	        }finally {
	        	 reader.close();
	        }
		 
		 writeFile();
		 readFile();
	}
	
	public static void readFile() {
		// try --with resource
		try( BufferedReader reader = new BufferedReader( new FileReader("sample.txt"))){
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
           // reader.close(); not required with try--with resource 
        }
        catch (IOException e) {
            System.err.println(
                "An error occurred while reading the file: "
                + e.getMessage());
        }

	}
	public static void writeFile() throws FileNotFoundException {
		
		 try (FileOutputStream fos
	             = new FileOutputStream("outputfile.txt");
	             // Adding resource
	             // Reading the stream of character from
	             BufferedReader br = new BufferedReader(
	                 new FileReader("gfgtextfile.txt"))) {
			 
			 String text;
	            while ((text = br.readLine()) != null) {

	                byte arr[] = text.getBytes();
	                fos.write(arr);
	            }
	            // Display message when
	            // file is successfully copied
	            System.out.println(
	                "File content copied to another one.");
			 
		 }catch(IOException ex) {
			 System.err.print(ex);
		 }
//		 finally {
//			fos.close();
//			br.close();
//		}
	}

}
