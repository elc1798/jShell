package org.jshell.commands;

import java.io.InputStream;
import java.util.Scanner;

import org.jshell.utils.JshellCommand;

/**
 * Runs a process provided arguments in an array of strings. For example,
 * {@code ls -al} will have the array {@code ["ls", "-al"] }. The command will
 * not exit until the subprocess has stopped running.
 *
 * @author elc1798
 *
 */
public class JshellProcRunner extends JshellCommand {

    private Runtime runtime;
    private Process subprocess;
    private String[] args;

    public JshellProcRunner(String[] _args) {
        args = _args;
    }

    @Override
    public void initialize() throws Exception {
        runtime = Runtime.getRuntime();
        subprocess = runtime.exec(args);
        subprocess.waitFor();
        InputStream processOut = subprocess.getInputStream();
        // Create a temporary Scanner object and store in a variable so we
        // can close it later
        Scanner s1 = new Scanner(processOut);
        // Create a scanner with the delimiter "\\A" (Beginning of Input)
        Scanner s2 = s1.useDelimiter("\\A");
        if (s2.hasNext()) {
            System.out.println(s2.next().trim());
        }
        // Close the scanners: s2 first, because s2 is a wrapper around s1
        // Then close s1. Duh. :D
        s2.close();
        s1.close();
    }

    @Override
    public void execute() throws Exception {
    }

    @Override
    public boolean isFinished() throws Exception {
        return !subprocess.isAlive();
    }

    @Override
    public void end() throws Exception {
        super.setExitValue(subprocess.exitValue());
    }

    @Override
    public void interrupted() {
        if (subprocess != null) {
            subprocess.destroyForcibly();
            subprocess = null;
        }
    }
}
