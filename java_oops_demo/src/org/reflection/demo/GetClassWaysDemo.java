package org.reflection.demo;

import java.lang.reflect.Constructor;

public class GetClassWaysDemo {

	 public static <T> void main(String args[]) throws Exception {   
		 System.out.print("ForNameDemo");
		 
		  Class<?> c1 = Class.forName("java.lang.String");

	        System.out.println("Class represented by c1: "
	                         + c1.toString());
	        
	        // Class.forName
		  Class<?> c=Class.forName("org.reflection.demo.SimpleDemo");    
		  System.out.println(c.getName());   
		  System.out.println( c.getDeclaredConstructors()); 
		  SimpleDemo sd=(SimpleDemo) c.newInstance();
		  sd.hello();
		  
		  Constructor<?>[] cons =c.getDeclaredConstructors();
		  
		  System.out.println("==========");
		  for(Constructor<?> b :cons) {
			  System.out.println(b);
		  }
		  System.out.println("==============");
		 
		  // Object class method 
		  SimpleDemo simpleDemo=new SimpleDemo();
		  simpleDemo.hello();
		  Class<?> c2=simpleDemo.getClass();    
		  System.out.println(c2.getName());  
		  System.out.println("c2 "+c2.isInterface());
		  
		  
		  
		  // .class syntax
		  Class<?> c3 =HelloDemo.class;
		  System.out.println(c3.getName());
		  
		  System.out.println("c3 "+c3.isInterface());  
		 }    
}

class SimpleDemo{
	
	public void hello() {
		System.out.println("hello");
	}
	
}

interface HelloDemo{
	
	void helloPrint();
}

 