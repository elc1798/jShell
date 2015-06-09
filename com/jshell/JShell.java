package com.jshell;

import java.io.File;

import static com.jshell.CONSTANTS.JSH_TEMP;

public class JShell {

    public static void main(String[] args) {
        // Setup JShell temporary directory for backup files
        File JSHELL_TEMP_DIRECTORY = new File(JSH_TEMP);
        if (JSHELL_TEMP_DIRECTORY.exists()) {
            // Delete in order to reset
            JSHELL_TEMP_DIRECTORY.delete();
        }
        JSHELL_TEMP_DIRECTORY.mkdir();
        JSHELL_TEMP_DIRECTORY = null;
    }
}
