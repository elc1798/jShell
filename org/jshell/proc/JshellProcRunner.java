package org.jshell.proc;

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
    }

    @Override
    public void execute() throws Exception {
        subprocess = runtime.exec(args);
        subprocess.waitFor();
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
