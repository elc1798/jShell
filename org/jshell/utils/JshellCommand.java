package org.jshell.utils;

import org.jshell.JshellObject;

/**
 * Abstract class that all {@code JshellCommand} objects extend.
 *
 * @author elc1798
 *
 */
public abstract class JshellCommand extends JshellObject {

    private int EXIT_STATUS = 0;

    /**
     * Run once when the {@code JshellCommand} is first started.
     *
     * @throws Exception
     */
    public abstract void initialize() throws Exception;

    /**
     * Run until {@code isFinished} returns true.
     *
     * @throws Exception
     */
    public abstract void execute() throws Exception;

    /**
     * Checks for a condition to declare when the command should stop running.
     *
     * @return True if {@code execute} should stop running, false otherwise.
     * @throws Exception
     */
    public abstract boolean isFinished() throws Exception;

    /**
     * Run once when the {@code JshellCommand} stops running (immediately after
     * {@code isFinished} returns true.
     *
     * @throws Exception
     */
    public abstract void end() throws Exception;

    /**
     * Run if the {@code JshellCommand} throws an {@code Exception} or is sent
     * a kill signal. It is recommended that variable cleanup-code is put in
     * this function.
     */
    public abstract void interrupted();

    public void setExitValue(int val) {
        EXIT_STATUS = val;
    }

    /**
     * Starts running a {@code JshellCommand}.
     */
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
