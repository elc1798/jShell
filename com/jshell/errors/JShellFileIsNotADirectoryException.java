package com.jshell.errors;

@SuppressWarnings("serial")
public class JShellFileIsNotADirectoryException extends Exception {

    public JShellFileIsNotADirectoryException() {
        super("JShellFileIsNotADirectoryException: Attempt to use a file as a directory");
    }
}
