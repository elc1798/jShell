package org.jshell;

import static org.jshell.Constants.rows;
import static org.jshell.Constants.userHomeDir;

import org.jshell.commands.JshellDirectoryChanger;
import org.jshell.commands.JshellLeftPrompt;
import org.jshell.commands.JshellUpdateTermDims;

import static org.jshell.Constants.columns;

import org.jshell.utils.JshellCommandScheduler;
import org.jshell.utils.TerminalUtils;

public class Jshell {

    public static JshellCommandScheduler scheduler;
    public static JshellEnvVarTracker env;

    public static void updateTermDimensions() {
        if (env == null) {
            return;
        }
        int newCols = TerminalUtils.getCols();
        int newRows = TerminalUtils.getRows();
        if (newCols < 0 || newRows < 0) {
            return;
        }
        env.set(columns, new JshellObject(newCols));
        env.set(rows, new JshellObject(newRows));
    }

    public static void scheduleRequiredCommands() {
        scheduler.schedule(new JshellUpdateTermDims());
        scheduler.schedule(new JshellLeftPrompt());
    }

    public static void main(String[] args) {
        scheduler = new JshellCommandScheduler();
        env = new JshellEnvVarTracker();

        scheduler.schedule(new JshellDirectoryChanger(userHomeDir));
        scheduleRequiredCommands();

        while (!scheduler.isEmpty()) {
            scheduler.runNext();
        }

        System.out.println("No commands left in Scheduler. Exitting...");
    }

}
