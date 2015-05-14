package com.jshell.filesystem;

import static com.jshell.CONSTANTS.*;

import com.jshell.errors.JShellInvalidPathException;

import java.io.File;

public class CD extends ShellCommand {

    private DirectoryTree dir_tree;

    private String OLD_WORKING_DIR;
    private String NEW_WORKING_DIR;

    public CD(DirectoryTree dir , String origin , String destination) {
        dir_tree = dir;
        NEW_WORKING_DIR = destination;
        OLD_WORKING_DIR = origin;
    }

    @Override
    public void start() {
        if (NEW_WORKING_DIR.startsWith(HOMEMARKER)) {
            NEW_WORKING_DIR = HOMEDIR + NEW_WORKING_DIR.substring(1);
        }
        if (!NEW_WORKING_DIR.endsWith(DIRMARKER)) {
            NEW_WORKING_DIR += DIRMARKER;
        }
        if (!NEW_WORKING_DIR.startsWith(DIRMARKER)) {
            NEW_WORKING_DIR = OLD_WORKING_DIR + NEW_WORKING_DIR;
        }
    }

    @Override
    public void run() throws JShellInvalidPathException {
        int top_level_dir_name_length = 0;

        NEW_WORKING_DIR = NEW_WORKING_DIR.replace(HOMEMARKER + DIRMARKER , HOMEDIR + DIRMARKER);
        String[] PATH_PARTS = NEW_WORKING_DIR.split(DIRMARKER);
        NEW_WORKING_DIR = ROOT_DIR;
        for (String PART : PATH_PARTS) {
            if (PART.equals(ASCEND_DIR)) {
                NEW_WORKING_DIR = NEW_WORKING_DIR.substring(0 , NEW_WORKING_DIR.length() - top_level_dir_name_length);
                top_level_dir_name_length = 0;
                for (int i = 1 ; NEW_WORKING_DIR.charAt(NEW_WORKING_DIR.length() - 1 - i) != DIRMARKER.charAt(0) ; i++) {
                    top_level_dir_name_length = i;
                }
            } else {
                NEW_WORKING_DIR += PART + DIRMARKER;
                top_level_dir_name_length = PART.length() + 1;
            }
        }

        checkPath(NEW_WORKING_DIR);
    }

    @Override
    public void end() {
        dir_tree.setWorkingDirectory(NEW_WORKING_DIR);
    }

    @Override
    public void interrupt() {
        dir_tree.setWorkingDirectory(OLD_WORKING_DIR);
    }

    private void checkPath(String path) throws JShellInvalidPathException {
        File checker = new File(path);
        if (!checker.exists() || !checker.isDirectory()) {
            throw new JShellInvalidPathException();
        }
    }
}
