package com.jshell.filesystem;

import static com.jshell.CONSTANTS.DIRMARKER;
import static com.jshell.CONSTANTS.HOMEDIR;
import static com.jshell.CONSTANTS.HOMEMARKER;

import java.io.File;
import java.io.IOException;

import com.jshell.errors.JShellFileIsNotADirectoryException;
import com.jshell.errors.JShellInvalidPathException;

public class MKDIR extends ShellCommand {

    private DirectoryTree dir_tree;
    private String absolute_path;
    private File new_file;

    public MKDIR(DirectoryTree dir , String filename) {
        dir_tree = dir;
        absolute_path = filename;
    }

    @Override
    public void start() {
        if (absolute_path.startsWith(HOMEMARKER)) {
            absolute_path = HOMEDIR + absolute_path.substring(1);
        }
        if (!absolute_path.startsWith(DIRMARKER)) {
            absolute_path = dir_tree.getWorkingDirectory() + absolute_path;
        }
        if (!absolute_path.endsWith(DIRMARKER)) {
            absolute_path += DIRMARKER;
        }
    }

    @Override
    public void run() throws JShellInvalidPathException , IOException {
        new_file = new File(absolute_path);
        if (new_file.exists()) {
            throw new JShellInvalidPathException();
        } else {
            new_file.mkdir();
        }
    }

    @Override
    public void end() throws JShellFileIsNotADirectoryException {
        if (!new_file.isDirectory()) {
            throw new JShellFileIsNotADirectoryException();
        }
    }

    @Override
    public void interrupt() {
        // Clean up file if interrupted
        new_file = null;
        File CLEAN = new File(absolute_path);
        if (CLEAN.exists() && CLEAN.isDirectory()) {
            CLEAN.delete();
        }
        CLEAN = null;
    }
}