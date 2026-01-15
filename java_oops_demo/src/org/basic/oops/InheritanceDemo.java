package org.basic.oops;
// is-a relationship
public class InheritanceDemo {

	    public static void main(String[] args) {
		
		//Dog wrongDog = new Animal();// wrong
		Animal animal = new Animal();
		animal.sleep();
		animal.eat();
		
		Animal myDog = new Dog();
		myDog.sleep();
		myDog.eat();
		//myDog.bark();//
		//String ok= myDog.type;
		
		Dog childDog = new Dog();

	        // Inherited methods (from Animal)
		childDog.eat();    
		childDog.sleep();  
	        // Child class method
		childDog.bark(); 
		String k=childDog.type;
	    }
	
}

//Superclass (Parent)
class Animal {
	String Name;
	int age;
	    void eat() {
	        System.out.println("Animal is eating...");
	    }

	    void sleep() {
	        System.out.println("Animal is sleeping...");
	    }
	}

	// Subclass (Child) - Inherits from Animal
	class Dog extends Animal {
		String type;
	    void bark() {
	        System.out.println("Dog is barking!");
	    }
	}

	// Subclass (Child) - Inherits from Animal
	class Cow extends Animal {
	    void grass() {
	        System.out.println("cow !");
	    }
	}

	// Subclass (Child) - Inherits from Animal
	class Cat extends Animal {
	    void mau() {
	        System.out.println("cat !");
	    }
	}