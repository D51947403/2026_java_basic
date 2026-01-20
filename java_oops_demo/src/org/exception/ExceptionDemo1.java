package org.exception;
class ExceptionDemo1{
    public static void main(String[] args) throws CustomException {
        
        int n = 10;
        int m = 0;

        try {
            int ans = n / m;
            System.out.println("Answer: " + ans);
        } catch (ArithmeticException e){
            System.err.println("Error: Division by 0!");
           //  System.exit(0); // This will terminate JVM 
        } finally {
        	System.out.println("finally block");
        }
        
        try {
        	 System.out.println("try");
        }finally {
        	System.out.println("finally block 2");
        }
        // calling static method
        //ExceptionDemo1.checkAge(9);
         checkAge(29);
    }
  static void checkAge(int age) throws CustomException {
	  // multiple try catch block 
	  try {
		    
		   int d=10/0;
		   System.out.println(d);
		} catch (ArithmeticException e ) {
		    
			System.err.println("Error: Division by 0!");
		    
		} catch(ArrayIndexOutOfBoundsException e){
		    
			System.err.println("ArrayIndexOutOfBoundsException");
		    
		}catch(NumberFormatException e){
		    
			System.err.println("NumberFormatException");
		}
	// multiple exception on single catch block
	  try {
		    
		   int d=10/0;
		   System.out.println(d);
		} catch (ArithmeticException | ArrayIndexOutOfBoundsException |NumberFormatException
				ex) {
			System.err.println( ex.getMessage());
		}
	  
	  
        if (age < 18) {
            throw new CustomException("Age must be 18 or above");
        }else {
        	  throw new ArithmeticException("Age is 18 or above");
        }
    }
}