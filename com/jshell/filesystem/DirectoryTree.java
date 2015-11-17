package com.jshell.filesystem;

import static com.jshell.CONSTANTS.DIRMARKER;

public class DirectoryTree {

    private static String WORKING_DIR;

    public DirectoryTree() {
        // Ensures it starts with an absolute path
        setWorkingDirectory(System.getProperty("user.dir"));
    }

    public int cd(String dest) {
        return new CD(this, getWorkingDirectory(), dest).EXECUTE();
    }

    public String getWorkingDirectory() {
        return WORKING_DIR;
    }

    public void setWorkingDirectory(String _WORKING_DIR) {
        if (!_WORKING_DIR.endsWith(DIRMARKER)) {
            _WORKING_DIR += DIRMARKER;
        }
        WORKING_DIR = _WORKING_DIR;
    }
}
