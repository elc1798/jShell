package com.jshell;

public class JSHProcMan extends ShellCommand {

    Runtime command;
    String[] args;

    public JSHProcMan(String[] command) {
        args = command;
    }

    public void start() throws Exception {
        command = Runtime.getRuntime();
    }

    public void run() throws Exception {
        Process jsubproc = command.exec(args);
        jsubproc.waitFor();
        jsubproc = null;
    }

    public void end() throws Exception {
        args = null;
        command = null;
    }

    public void interrupt() {
        args = null;
        command = null;
    }
}
