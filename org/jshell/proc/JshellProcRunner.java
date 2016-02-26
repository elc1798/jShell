package org.jshell.proc;

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
