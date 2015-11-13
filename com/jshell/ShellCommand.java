package com.jshell;

public abstract class ShellCommand {

    private int EXIT_STATUS;

    public void start() throws Exception {}

    public void run() throws Exception {}

    public void end() throws Exception {}

    public void interrupt() {}

    public void setStatus(int status) {
        EXIT_STATUS = status;
    }

    private int getStatus() {
        return EXIT_STATUS;
    }

    public int EXECUTE() {
        try {
            start();
            run();
            end();
            EXIT_STATUS = 0;
        } catch (Exception e) {
            interrupt();
            EXIT_STATUS = 1;
        }
        return getStatus();
    }

}
