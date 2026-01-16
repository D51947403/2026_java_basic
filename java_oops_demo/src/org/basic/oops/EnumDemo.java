package org.basic.oops;

public class EnumDemo {

	public static void main(String[] args) {  
        // Accessing a single enum value  
        Days today = Days.WEDNESDAY;  
        System.out.println("Today is: " + today);  
  
        // Accessing all enum values  
        System.out.println("All days of the week:");  
        for (Days d : Days.values()) {  
            System.out.println(d);  
        } 
        
        Direction  northDir=Direction.NORTH;
        System.out.println("North is: " + northDir); 
        
        Direction[] dirArray = Direction.values();
        
        System.out.println("0 " + dirArray[0]); 
        
        for(Direction dir: Direction.values()) {
        	 System.out.println(dir);  
        }
        
        // access to nested enum inside class
        Color red= Color.RED;
        System.out.println("red " +red); 
        
        for(Color color: Color.values()) {
       	 System.out.println(color);  
       }
        
        // access to nested enum inside enum
        Days.UNIT meter = Days.UNIT.METER;
        System.out.println("meter " +meter); 
        Days.UNIT[] unitArr=  Days.UNIT.values();
        for(Days.UNIT unit: unitArr) {
          	 System.out.println(unit);  
          }
        
        
    }  
	
	// nested enum or inner enum
	enum Color{
		RED, BLACK,WHITE,YELLO,GREEN,BLUE,ORANGE
	}
}

enum Days { 
	// public static final String SUNDAY="SUNDAY";
	//acts as array
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY  ;
    
	// nested enum or inner enum
    enum UNIT{
    	CENTIMETER, METER,KILOMETER,LIGHTYEAR
    }
}  
