package com.jshell.IO;

import static com.jshell.CONSTANTS.*;

import java.util.Scanner;

import com.jshell.errors.JShellUnknownRuntimeException;

public class STDIN {

    public static String readUserInput(int mode) throws JShellUnknownRuntimeException {
        if (mode == JSHELL_TERMINAL_CONSOLE) {
            Scanner user_in = new Scanner(System.in);
            String retVal = user_in.nextLine();
            user_in = null;
            return retVal;
        } else if (mode == JSHELL_JAVA_SWING_CONSOLE) {
            return "NOT_IMPLEMENTED_YET";
        } else {
            throw new JShellUnknownRuntimeException();
        }
    }
}
