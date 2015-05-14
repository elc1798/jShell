package com.jshell.errors;

@SuppressWarnings("serial")
public class JShellDirectoryIsNotAFileException extends Exception {

    public JShellDirectoryIsNotAFileException() {
        super("JShellDirectoryIsNotAFileException: Attempt to use a directory as a filename");
    }
}
