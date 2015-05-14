package com.jshell.errors;

@SuppressWarnings("serial")
public class JShellInvalidPathException extends Exception {

    public JShellInvalidPathException() {
        super("JShellInvalidPathException: Attempt to use an inexistent path as working directory");
    }
}
