package org.collection.demo;

import java.util.ArrayList;

public class ArrayListDemo {

	public static void main(String[] args) {
		 System.out.println("ArrayList demo");
		 ArrayList<Integer> al= new ArrayList<>();
		 al.add(10);
		 al.add(20);
		 al.add(30);
		 al.add(40);
		 
		 for(Integer i: al) {
			 System.out.println(i);
		 }

	}

}
