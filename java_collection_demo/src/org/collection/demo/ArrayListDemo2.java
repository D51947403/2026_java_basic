package org.collection.demo;

import java.util.ArrayList;

public class ArrayListDemo2 {

	public static void main(String[] args) {

		// Creating an Array of string type
        ArrayList<String> al = new ArrayList<>();

        // 1. Adding elements to ArrayList at the end
        al.add("Geeks");
        al.add("Ramendra");
        al.add("Geeks");
        al.add("Geeks");

      	System.out.println("Original List : "+al);
      	
      	// Adding Elements at the specific index
        al.add(1, "For");

      	System.out.println("After Adding element at index 1 : "+ al);
      	
      	// 2. Removing Element using index
      	al.remove(0);
      
      	System.out.println("Element removed from index 0 : "+ al);
      	
      	// Removing Element using the value
      	al.remove("Geeks");
      
      	System.out.println("Element Geeks removed : "+ al);
      	
      	// 3. Updating value at index 0
      	al.set(0, "GFG");
      
        System.out.println("List after updation of value : "+al);

        System.out.println("get(0): "+al.get(0));
	}

}
