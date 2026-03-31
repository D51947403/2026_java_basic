package main.com.library.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {

    public static Date getDateFromCustomString(String customDate) {
        if (customDate == null || customDate.trim().isEmpty()) {
            return new Date();
        }
        
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(customDate);
        } catch (ParseException e) {
            System.err.println("Error parsing date: " + customDate + ". Using current date instead.");
            return new Date();
        }
    }

    public static String getCustomStringFromDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static void main(String[] args) {
        System.out.println(getDateFromCustomString("2023-01-01"));
        System.out.println(getCustomStringFromDate(new Date()));
    }
}
