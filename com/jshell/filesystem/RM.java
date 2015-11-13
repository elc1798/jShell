package com.jshell.filesystem;

import static com.jshell.CONSTANTS.DIRMARKER;
import static com.jshell.CONSTANTS.HOMEDIR;
import static com.jshell.CONSTANTS.HOMEMARKER;

import java.io.File;
import java.io.IOException;

import com.jshell.ShellCommand;
import com.jshell.errors.JShellDirectoryIsNotAFileException;
import com.jshell.errors.JShellInvalidPathException;

public class RM extends ShellCommand {

    private DirectoryTree dir_tree;
    private String absolute_path;
    private File new_file;
    private boolean isDirectory;

    public RM(DirectoryTree dir , String filename , boolean _isDirectory) {
        dir_tree = dir;
        absolute_path = filename;
        isDirectory = _isDirectory;
    }

    public RM(DirectoryTree dir , String filename) {
        dir_tree = dir;
        absolute_path = filename;
        isDirectory = false;
    }

    @Override
    public void start() throws JShellDirectoryIsNotAFileException {
        if (absolute_path.startsWith(HOMEMARKER)) {
            absolute_path = HOMEDIR + absolute_path.substring(1);
        }
        if (!absolute_path.startsWith(DIRMARKER)) {
            absolute_path = dir_tree.getWorkingDirectory() + absolute_path;
        }
        if (!isDirectory && absolute_path.endsWith(DIRMARKER)) {
            throw new JShellDirectoryIsNotAFileException();
        }
        if (isDirectory && !absolute_path.endsWith(DIRMARKER)) {
            absolute_path += DIRMARKER;
        }
    }

    @Override
    public void run() throws JShellInvalidPathException , IOException {
        new_file = new File(absolute_path);
        if (new_file.exists()) {
            throw new JShellInvalidPathException();
        } else {
            new_file.createNewFile();
        }
    }

    @Override
    public void end() {
    }

    @Override
    public void interrupt() {
        // Clean up file if interrupted
        new_file = null;
        File CLEAN = new File(absolute_path);
        if (CLEAN.exists() && CLEAN.isFile()) {
            CLEAN.delete();
        }
        CLEAN = null;
    }

}
