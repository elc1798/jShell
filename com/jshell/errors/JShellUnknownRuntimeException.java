package com.jshell.errors;

@SuppressWarnings("serial")
public class JShellUnknownRuntimeException extends Exception {

    public JShellUnknownRuntimeException() {
        super("JShellUnknownRuntimeException");
        this.printStackTrace();
    }

}
