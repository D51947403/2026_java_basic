package org.basic.oops;

public class InstanceBlockDemo {

	public static void main(String[] args) {
		InstanceBlockDemo insBlock= new InstanceBlockDemo();
		insBlock.show();
		InstanceBlockDemo insBlock2= new InstanceBlockDemo();
		insBlock2.show();
	}

	static int b;

	// static block  --1
	// call only once for all object
	static {
		b = 100;
		System.out.println("=======static ==="+b);
	}

	int a;
	// instance block ---2
	// call for each object
	{
		a = 200;
		System.out.println("=======instance ==="+a);
	}
	
	int c;
	// constructor ---3 or on object creation
	// call for each object
	InstanceBlockDemo(){
		c=300;
		System.out.println("=======constructor ==="+c);
	}
	
	int d;
	//concrete method --on call
	void show() {
		d=400;
		System.out.println("=======show ==="+d);
	}

}
