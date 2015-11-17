package com.jshell.IO;

import com.jshell.CONSTANTS;

public class STDOUT {

    public static void print(String MESSAGE, int mode) {
        if (mode == CONSTANTS.JSHELL_TERMINAL_CONSOLE) {
            System.out.print(MESSAGE);
        } else if (mode == CONSTANTS.JSHELL_JAVA_SWING_CONSOLE) {

        }
    }
    
    public static void println(String MESSAGE, int mode) {
        print(MESSAGE + "\n" , mode);
    }
}
