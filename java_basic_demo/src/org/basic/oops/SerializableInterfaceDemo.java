package org.basic.oops;

import java.io.*;

class A implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -451861638946198105L;
	
	int i;
    String s;

    // A class constructor
    public A(int i,String s)
    {
        this.i = i;
        this.s = s;
    }
}

public class SerializableInterfaceDemo
{
    public static void main(String[] args)
      throws IOException, ClassNotFoundException
    {
        A a = new A(20,"GeeksForGeeks");

        // Serializing 'a'
        FileOutputStream fos = new FileOutputStream("xyz.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(a);

        // De-serializing 'a'
        FileInputStream fis = new FileInputStream("xyz.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        A b = (A)ois.readObject(); // down-casting object

        System.out.println(b.i+" "+b.s);

        // closing streams
        oos.close();
        ois.close();
    }
}