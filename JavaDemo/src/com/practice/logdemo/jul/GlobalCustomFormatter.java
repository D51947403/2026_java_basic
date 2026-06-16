package com.practice.logdemo.jul;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Standalone CustomFormatter for Java Util Logging
 * Provides consistent formatting across different logging configurations
 */
public class GlobalCustomFormatter extends Formatter {
    
    private static final String FORMAT = "[%1$tF %1$tT] [%2$-7s] %3$-20s: %4$s %n";
    
    @Override
    public String format(LogRecord record) {
        return String.format(FORMAT,
            new java.util.Date(record.getMillis()),
            record.getLevel().getLocalizedName(),
            record.getLoggerName(),
            record.getMessage());
    }
}
