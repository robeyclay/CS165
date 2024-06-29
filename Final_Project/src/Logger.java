/*
 * Copyright (c) 2020. 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License. To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/4.0/ or send a letter to Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.
 */

/**
 * This Logger helps log information about the process you are doing, including writing to the console. Many
 * programs write to system logs in the background, even if they are gui based programs or web based
 * programs. There is a whole suit of prebuilt loggers for Java for that reason. 
 * 
 * @author Albert Lionelle <br> 
 *         lionelle@colostate.edu <br> 
 *         Computer Science Department <br> 
 *         Colorado State University
 * @version 202010
 */
public class Logger {
    private static boolean verbose  = false;
    private static int debug_level = 0;
    
    static final class DebugLevel {
        static final int LEVEL1 = 1;
        static final int LEVEL2 = 2;
        static final int LEVEL3 = 3;
        static final int LEVEL4 = 4;
        static final int NO_DEBUG = 0;
        
        static String getString(int level) {
           return String.format("DEBUG(%d):", level);
        }
    }
    

    /**
     * Logs to System.out if verbose is set to true. Uses a parameter list (...), 
     * to allow transparent work with printf. 
     * @param toLog The string to print or the string format to evaluate
     * @param args optional arguments used in the string format 
     */
    public static void logDetails(String toLog, Object... args) { 
        if(verbose) console(toLog, args);
    }

    
    public static void debug(String toLog, Object... args) {
        debug(DebugLevel.LEVEL1, toLog, args);
    }

    public static void debugTwo(String toLog, Object... args) {
        debug(DebugLevel.LEVEL2, toLog, args);
    }

    public static void debugThree(String toLog, Object... args) {
        debug(DebugLevel.LEVEL3, toLog, args);
    }

    public static void debugFour(String toLog, Object... args) {
        debug(DebugLevel.LEVEL4, toLog, args);
    }
    
    /**
     * Logs to System.out if debug is set to true. Uses a parameter list (...), 
     * to allow transparent work with printf. 
     * @param toLog The string to print or the string format to evaluate
     * @param args optional arguments used in the string format
     */
    public static void debug(int level, String toLog, Object... args) {
        if((debug_level - level) > -1) console(DebugLevel.getString(level) + " " + toLog +"%n", args);
    }

    /**
     * Logs to System.out in all cases. Uses a parameter list (...), 
     * to allow transparent work with printf. 
     * @param toLog The string to print or the string format to evaluate
     * @param args optional arguments used in the string format
     */
    public static void console(String toLog, Object... args) {
        System.out.printf(toLog, args);
    }

   

    /**
     * Sets the debug value
     */
    public static void enableDebug() {
        debug_level = DebugLevel.LEVEL1;
        setVerbose(true);
    }
    
    /**
     * Sets the debug value
     */
    public static void enableDebug(int level) {
        debug_level = level;
        setVerbose(true);
    }
    
    public static void setVerbose(boolean flag) {
        verbose = flag;
    }
}
