package org.jshell.errors;

public class JshellError extends Exception {

    private static final long serialVersionUID = 7200674458414029193L;

    public JshellError(String message) {
        super(message);
        this.printStackTrace();
    }
}
