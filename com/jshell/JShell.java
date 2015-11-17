package com.jshell;

import static com.jshell.CONSTANTS.JSHELL_TERMINAL_CONSOLE;
import static com.jshell.CONSTANTS.JSHELL_JAVA_SWING_CONSOLE;
import static com.jshell.CONSTANTS.JSH_TEMP;
import static com.jshell.CONSTANTS.HOMEDIR;

import java.io.File;
import java.util.Arrays;

import com.jshell.IO.STDIN;
import com.jshell.IO.STDOUT;
import com.jshell.errors.JShellUnknownRuntimeException;
import com.jshell.filesystem.DirectoryTree;

public class JShell {

    private static String prompt = System.getProperty("user.name") + ":%s:$ ";
    
/*
 * TODO:
    private void loadConfigFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(JSHRC))) {
            String line;
            while ((line = br.readLine()) != null) {
               
            }
        } catch(Exception e) {
            
        }
    }
*/

    public static void main(String[] args) {
        // Setup JShell temporary directory for backup files
        File JSHELL_TEMP_DIRECTORY = new File(JSH_TEMP);
        if (JSHELL_TEMP_DIRECTORY.exists()) {
            // Delete in order to reset
            JSHELL_TEMP_DIRECTORY.delete();
        }
        JSHELL_TEMP_DIRECTORY.mkdir();
        JSHELL_TEMP_DIRECTORY = null;

        DirectoryTree fs = new DirectoryTree(); // The directory tree for the current session
        String command;                         // String buffer to store user input
        int mode = JSHELL_TERMINAL_CONSOLE;     // GUI for current session
        int exitCode = 0;                       // Variable to store exit code for commands

        if (Arrays.asList(args).contains("-g")) {
            mode = JSHELL_JAVA_SWING_CONSOLE;
        }
        
        while (true) {
            System.out.printf(prompt, fs.getWorkingDirectory());
            try {
                command = STDIN.readUserInput(mode);
                String[] commandArgs = command.split(" ");
                if (commandArgs[0].equals("cd")) {
                    if (commandArgs.length == 1) {
                        exitCode = fs.cd(HOMEDIR);
                    } else {
                        exitCode = fs.cd(commandArgs[1]);
                    }
                } else {
                    exitCode = new JSHProcMan(commandArgs).EXECUTE();
                }
            } catch (JShellUnknownRuntimeException e) {
                e.printStackTrace();
                STDOUT.println(e.getMessage(), mode);
                STDOUT.println("Exitting...", mode);
                System.exit(-1);
            }
        }
    }
}
