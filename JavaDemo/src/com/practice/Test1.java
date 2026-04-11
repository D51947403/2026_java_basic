package com.practice;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();
        double d=scan.nextDouble();
        scan.next();
        String s=scan.nextLine();


        System.out.println("String: " + s);
        System.out.println("Double: " + d);
        System.out.println("Int: " + i);
    }
}
