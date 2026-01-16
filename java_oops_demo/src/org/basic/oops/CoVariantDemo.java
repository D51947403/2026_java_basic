package org.basic.oops;

public class CoVariantDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class EngStudent {

	static String collegeName;
	String name;

	CSStudent getStudent() {

		return new CSStudent();

	}
	
	CSStudent getStudentInfo() {

		return new CSStudent();

	}

}

class StudentInfo extends EngStudent {

	
	 
	// covariant return type
	@Override
	CSStudent2023 getStudent() {

		return new CSStudent2023();

	}
	// covariant return type
	@Override
	CSStudent2024 getStudentInfo() {

		return new CSStudent2024();

	}

}

//parent
class CSStudent {
  int mark;
}

//child
class CSStudent2023 extends CSStudent {

}

// child
class CSStudent2024 extends CSStudent {

}