package org.jshell.errors;

public class JshellError extends Error {

    private static final long serialVersionUID = 7200674458414029193L;

    public JshellError(String message) {
        super(message);
    }

    public JshellError(String message, Exception ex) {
        super(message, ex.getCause());
        ex.printStackTrace();
    }
}
