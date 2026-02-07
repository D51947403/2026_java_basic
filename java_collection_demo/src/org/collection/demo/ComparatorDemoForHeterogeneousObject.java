package org.collection.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorDemoForHeterogeneousObject {

	public static void main(String[] args) {

           List  list = new ArrayList();
           
          EmployeeDemo emp1=new EmployeeDemo(11, "Ram", 23);
          EmployeeDemo emp2=new EmployeeDemo(11, "Shyam", 26);
          EmployeeDemo emp3=new EmployeeDemo(11, "Namo", 20);
          
          list.add(emp1);
          list.add(emp2);
          list.add(emp3);
          list.add(new Student(3, "Alice",25));
          list.add(new Student(1, "Bob",33));
          list.add(new Student(2, "Charlie",21));
          
          Collections.sort(list ,new MyComparator());
          
          System.out.println(list);

	}

}

class MyComparator  implements Comparator<Object>{

	@Override
	public int compare(Object s, Object e) {
     
		
	  Student student = null;
	  EmployeeDemo employee =null;
	  int age1 ,age2;
	  
	  if(s instanceof Student ) {
		  student = (Student)s;
		  age1=student.age ;
	  }
	  else {
		  employee = (EmployeeDemo)s;
		  age1=employee.Age ;
	  }
		  
	  if(e instanceof EmployeeDemo ) {
		  employee = (EmployeeDemo)e;
		  age2=employee.Age ;
	  }
	  else {
		  student = (Student)e;
		  age2=student.age ;
	  }
		 

      return age1 - age2;
		
	}
	
}