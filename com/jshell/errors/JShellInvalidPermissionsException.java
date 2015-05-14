package com.jshell.errors;

@SuppressWarnings("serial")
public class JShellInvalidPermissionsException extends Exception {

    public JShellInvalidPermissionsException() {
        super("JShellInvalidPermissionsException: Attempt to execute command without proper user permissions");
    }
}
