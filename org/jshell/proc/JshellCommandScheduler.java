package org.jshell.proc;

import java.util.LinkedList;

/**
 * Class that schedules commands to be run. The Command Scheduler is a Queue at
 * its core. To run a command, input it in via the {@code schedule} method. The
 * command will run after all command scheduled before it have run to completion.
 *
 * @author elc1798
 *
 */
public class JshellCommandScheduler {

    private LinkedList<JshellCommand> commandQueue;

    /**
     * Constructor for an empty JshellCommand Scheduler.
     */
    public JshellCommandScheduler() {
        commandQueue = new LinkedList<JshellCommand>();
    }

    /**
     * Schedule a command to be run. The command will run when all commands that
     * have been scheduled before it have run to completion.
     *
     * @param command - A JshellCommand to schedule
     */
    public void schedule(JshellCommand command) {
        commandQueue.add(command);
    }

    /**
     * @return True if the Scheduler has no commands scheduled, false otherwise.
     */
    public boolean isEmpty() {
        return commandQueue.isEmpty();
    }

    /**
     * Runs the next command scheduled to run, and removes it from the Scheduler
     * after it runs to completion.
     */
    public void runNext() {
        commandQueue.getFirst().start();
        commandQueue.removeFirst();
    }

    /**
     * Cancels the next command scheduled to run. Useful in some circumstances.
     */
    public void cancelNext() {
        commandQueue.removeFirst();
    }
}
