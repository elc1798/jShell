package org.jshell.proc;

public abstract class JshellCommand {

    private int EXIT_STATUS = 0;

    public void initialize() throws Exception {
    }

    public void execute() throws Exception {
    }

    public boolean isFinished() throws Exception {
        return true;
    }

    public void end() throws Exception {
    }

    public void interrupted() {
    }

    public void setExitValue(int val) {
        EXIT_STATUS = val;
    }

    public final int start() {
        try {
            initialize();
            while (!isFinished()) {
                execute();
            }
            end();
        } catch (Exception e) {
            interrupted();
            EXIT_STATUS = 1;
        }
        return EXIT_STATUS;
    }
}
