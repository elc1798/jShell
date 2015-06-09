package com.jshell.filesystem;

import static com.jshell.CONSTANTS.DIRMARKER;

public class DirectoryTree {

    private static String WORKING_DIR;

    public DirectoryTree() {
        setWorkingDirectory(System.getProperty("user.dir")); // Ensures it starts with an absolute path
    }

    public int cd(String dest) {
        return new CD(this , getWorkingDirectory() , dest).EXECUTE();
    }

    public int touch(String dest) {
        return new TOUCH(this , dest).EXECUTE();
    }

    public int rm(String file) {
        return new RM(this , file).EXECUTE();
    }

    public int cp(String src , String dst) {
        return new CP(this , src , dst).EXECUTE();
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
