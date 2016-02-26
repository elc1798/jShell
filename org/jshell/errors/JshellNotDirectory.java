package org.jshell.errors;

@SuppressWarnings("serial")
public class JshellNotDirectory extends JshellError {

    private static String message = "Not a directory.";
    
    public JshellNotDirectory() {
        super(message);
    }

}
