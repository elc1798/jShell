package org.jshell.utils;

import java.io.InputStream;
import java.util.Scanner;

/**
 * A class of utility functions to grab terminal properties, such as the
 * dimensions.
 *
 * @author elc1798
 *
 */
public class TerminalUtils {

    private static String getColCommand = "tput cols 2> /dev/tty";
    private static String getRowsCommand = "tput lines 2> /dev/tty";

    /**
     * @return The number of columns in the terminal
     */
    public static int getCols() {
        int cols = -1;
        try {
            Process p = Runtime.getRuntime()
                    .exec(new String[] { "bash", "-c", getColCommand });
            InputStream processOut = p.getInputStream();
            // Create a temporary Scanner object and store in a variable so we
            // can close it later
            Scanner s1 = new Scanner(processOut);
            // Create a scanner with the delimiter "\\A" (Beginning of Input)
            Scanner s2 = s1.useDelimiter("\\A");
            if (s2.hasNext()) {
                String out = s2.next().trim();
                cols = Integer.parseInt(out);
            }
            // Close the scanners: s2 first, because s2 is a wrapper around s1
            // Then close s1. Duh. :D
            s2.close();
            s1.close();
        } catch (Exception e) {
            System.err.println("Failed retrieving columns");
            e.printStackTrace(System.err);
        }
        return cols;
    }

    /**
     * @return The number of rows in the terminal
     */
    public static int getRows() {
        int rows = -1;
        try {
            Process p = Runtime.getRuntime()
                    .exec(new String[] { "bash", "-c", getRowsCommand });
            InputStream processOut = p.getInputStream();
            // Create a temporary Scanner object and store in a variable so we
            // can close it later
            Scanner s1 = new Scanner(processOut);
            // Create a scanner with the delimiter "\\A" (Beginning of Input)
            Scanner s2 = s1.useDelimiter("\\A");
            if (s2.hasNext()) {
                String out = s2.next().trim();
                rows = Integer.parseInt(out);
            }
            // Close the scanners: s2 first, because s2 is a wrapper around s1
            // Then close s1. Duh. :D
            s2.close();
            s1.close();
        } catch (Exception e) {
            System.err.println("Failed retrieving columns");
            e.printStackTrace(System.err);
        }
        return rows;
    }

}
