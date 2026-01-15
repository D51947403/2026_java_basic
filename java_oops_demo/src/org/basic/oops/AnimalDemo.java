package org.basic.oops;

public class AnimalDemo extends Living implements Cloneable{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
           String str1="java";
           String str2="java";
           System.out.println(str1.equals(str2));
           Living liv= new Living();
           liv.display();
           AnimalDemo animal = new AnimalDemo();
           animal.display();
           try {
			animal.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class Living // extends Object
{
	
//	Living(){
//		
//	}
	  public void display() {
		  System.out.println("display"); 
	  }
}