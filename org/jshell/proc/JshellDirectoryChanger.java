package org.jshell.proc;

import java.io.File;

import org.jshell.errors.JshellNoSuchDirectoryError;
import org.jshell.errors.JshellNotDirectory;

import static org.jshell.Constants.cwdSystemProperty;

public class JshellDirectoryChanger extends JshellCommand {

    String path;
    String oldWorkingDir;
    File dir;

    public JshellDirectoryChanger(String _path) {
        oldWorkingDir = System.getProperty(cwdSystemProperty);
        path = _path;
    }

    @Override
    public void initialize() throws Exception {
        try {
            dir = new File(path).getAbsoluteFile();
        } catch(Exception e) {
            throw new JshellNoSuchDirectoryError();
        }
        if (!dir.exists()) {
            throw new JshellNotDirectory();
        }
        System.setProperty(cwdSystemProperty, dir.getAbsolutePath());
    }

    @Override
    public void execute() throws Exception {
    }

    @Override
    public boolean isFinished() throws Exception {
        return true;
    }

    @Override
    public void end() throws Exception {
        dir = null;
        oldWorkingDir = null;
    }

    @Override
    public void interrupted() {
        dir = null;
        System.setProperty(cwdSystemProperty, oldWorkingDir);
    }

}
