package org.reflection.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GetConstructorDemo {

	public static void main(String[] args) {
		 
		 SimpleTest s = new SimpleTest(80);
		
           Class<?> c = SimpleTest.class;

		  Constructor<?>[] cons =c.getDeclaredConstructors();
		  
		  
		  try {
			SimpleTest simpleTest= (SimpleTest) cons[0].newInstance("Suraj");
			simpleTest.demo();
			
			// clone object
			try {
				SimpleTest simpleTestClone=(SimpleTest) simpleTest.clone();
				
				simpleTestClone.hello();
				
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  for(Constructor<?> b :cons) {
			  System.out.println(b);
		  }
		  System.out.println("==============");
		  
		Method[] methods=  c.getDeclaredMethods();
		
		  for(Method m :methods) {
			  System.out.println(m);
		  }
		  System.out.println("==============");
	}

}

class SimpleTest implements Cloneable{
	int a;
	int p;
	
	SimpleTest(int k){
		this.a=k;
	}
	
	SimpleTest(int k ,int r){
		this.a=k;
		this.p=r;
	}
	
	SimpleTest(String name){
     System.out.println(name);
	}
	public void hello() {
		System.out.println("hello");
	}
	
	public void demo() {
		System.out.println("demo");
	}
	
	public void disp() {
		System.out.println("disp");
	}
	
	 // Overriding the clone() method
    @Override
    public Object clone() throws CloneNotSupportedException {
        // Returning a clone of the current object
        return super.clone(); 
    }
	
}
