package org.collection.demo;

import java.util.HashSet;

public class HashSetDemo {
	public static void main(String[] args) 
    {
        // Instantiate an object of HashSet
        HashSet<Integer> hs = new HashSet<>();

      	// Adding elements 
        hs.add(1);
        hs.add(2);
        hs.add(1);
        hs.add(null);

        System.out.println("HashSet Size: " + hs.size());
        System.out.println("Elements in HashSet: " + hs);
    }
}
