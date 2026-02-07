package org.collection.demo;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListDemo {

	public static void main(String[] args) {
		 System.out.println("ArrayList demo");
		 ArrayList<Integer> al= new ArrayList<>();
		 al.add(10);
		 al.add(30);
		 al.add(30);
		 al.add(40);
		 al.add(null);
		 
		 for(Integer i: al) {
			 System.out.println(i);
		 }

		 System.out.println(al.get(1));		
		 System.out.println(al.get(4));	
		 
		 Iterator<Integer> it = al.iterator();

	        // Iterate through the elements and print each one
	        while (it.hasNext()) {
	          
	            // Get the next element
	            int n = it.next(); 
	            System.out.println(n);      
	        } 
	}

}
