package org.jshell.proc;

public abstract class JshellCommand {

    private int EXIT_STATUS = 0;

    public abstract void initialize() throws Exception;
    public abstract void execute() throws Exception;
    public abstract boolean isFinished() throws Exception;
    public abstract void end() throws Exception;
    public abstract void interrupted();

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
