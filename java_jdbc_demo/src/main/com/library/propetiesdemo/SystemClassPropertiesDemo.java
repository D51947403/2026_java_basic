package main.com.library.propetiesdemo;// Java program to demonstrate Properties class to get all
// the system properties

import java.util.*;

public class SystemClassPropertiesDemo {
    public static void main(String[] args) throws Exception
    {
        // get all the system properties
        Properties p = System.getProperties();

        // stores set of properties information
        Set set = p.entrySet();

        // iterate over the set
        Iterator itr = set.iterator();
        while (itr.hasNext()) {

            // print each property
            Map.Entry entry = (Map.Entry)itr.next();
            System.out.println(entry.getKey() + " = "
                               + entry.getValue());
        }

        // print the property
        System.out.println("The property value is: "
                           + System.getProperty("java.version"));
        System.out.println("The property value is: "
                           + System.getProperty("os.name"));
    }
}