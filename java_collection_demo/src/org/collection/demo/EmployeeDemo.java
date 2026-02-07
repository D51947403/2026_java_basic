package org.collection.demo;

public class EmployeeDemo implements Comparable<EmployeeDemo>{
  private int empId;
 
  private String empname;
  
   int Age;
  
  
  public EmployeeDemo(int empId, String empname, int age) {
	super();
	this.empId = empId;
	this.empname = empname;
	this.Age = age;
}
  
  public int getEmpId() {
	return empId;
  }
  public void setEmpId(int empId) {
	this.empId = empId;
  }
  public String getEmpname() {
	return empname;
  }
  public void setEmpname(String empname) {
	this.empname = empname;
  }
  
  @Override
  public int compareTo(EmployeeDemo empObj) {
	 return this.empId - empObj.empId ;
	 // + Ve  greater 
	 // - Ve smaller 
	 //0  equal
  }

  @Override
  public String toString() {
	return "EmployeeDemo [empId=" + empId + ", empname=" + empname + ", Age=" + Age + "]";
  }
  
  
  
}
