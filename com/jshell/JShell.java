package com.jshell;

import static com.jshell.CONSTANTS.JSH_TEMP;

import java.io.File;

import com.jshell.filesystem.DirectoryTree;

public class JShell {

    private static String prompt = System.getProperty("user.name") + ":%s $ ";
    private static String[] known_fs_commands = {
        "cd", "ls", "cp", "mv", "touch"
    };

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

        DirectoryTree fs = new DirectoryTree();
        while (true) {
            System.out.printf(prompt, fs.getWorkingDirectory());

        }
    }
}
