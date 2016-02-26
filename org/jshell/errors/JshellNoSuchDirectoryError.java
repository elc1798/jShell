package org.jshell.errors;

@SuppressWarnings("serial")
public class JshellNoSuchDirectoryError extends JshellError {

    private static String message = "Directory not found.";

    public JshellNoSuchDirectoryError() {
        super(message);
    }
}
