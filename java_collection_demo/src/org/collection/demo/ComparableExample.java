package org.collection.demo;
import java.util.*;

class Student implements Comparable<Student> {
     int id;
    String name;
    int age;
    
    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Student(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}


	@Override
    public int compareTo(Student other) {
        return this.id - other.id; // Sort by ID in ascending order
    }

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}

public class ComparableExample {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(3, "Alice"));
        students.add(new Student(1, "Bob"));
        students.add(new Student(2, "Charlie"));

        Collections.sort(students); // Sorts using compareTo()

        System.out.println(students);
        
       List <Integer> intList = new ArrayList<>();
       intList.add(20);
       intList.add(80);
       intList.add(50);
       intList.add(40);
       
       Collections.sort(intList);
       
       System.out.println(intList);
       

    }
}