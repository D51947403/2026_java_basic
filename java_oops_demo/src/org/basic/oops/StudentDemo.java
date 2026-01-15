package org.basic.oops;

import java.util.Objects;

public class StudentDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BEStudent  s1 = new BEStudent(10, "Ashok");
		BEStudent  s2 = new BEStudent(10, "Ashok");
		BEStudent  s3 = new BEStudent(20, "Narendra");
		BEStudent  s4 = new BEStudent(30, "Narendra");
		
		System.out.println(s1.equals(s2));//
		System.out.println(s1.equals(s3));//
		System.out.println(s3.equals(s4));//
	}

}

class BEStudent {
	private int id;
	private String name;

	public BEStudent(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public int hashCode() {
		return 20+this.id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BEStudent other = (BEStudent) obj;
		return id == other.id && Objects.equals(name, other.name);
	}

	

}