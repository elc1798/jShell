package com.jshell.filesystem;

import java.io.File;
import com.jshell.errors.JShellInvalidPathException;

public class MV extends ShellCommand {

    private DirectoryTree dir_tree;
    private String SRC_PATH;
    private String DEST_PATH;

    public MV(DirectoryTree _dir_tree , String src , String dst) {
        dir_tree = _dir_tree;
        SRC_PATH = src;
        DEST_PATH = dst;
    }

    public void start() throws JShellInvalidPathException {
        // Verify source file exists
        if (!new File(SRC_PATH).exists()) {
            throw new JShellInvalidPathException();
        }
    }

    public void run() throws Exception {
        dir_tree.cp(SRC_PATH , DEST_PATH);
        dir_tree.rm(SRC_PATH);
    }

    public void end() throws Exception {

    }

    public void interrupt() {

    }
}
