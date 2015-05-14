package com.jshell.filesystem;

import static com.jshell.CONSTANTS.*;

import java.io.File;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.jshell.IO.STDIN;
import com.jshell.IO.STDOUT;
import com.jshell.errors.JShellDirectoryIsNotAFileException;
import com.jshell.errors.JShellFileIsNotADirectoryException;
import com.jshell.errors.JShellInvalidPathException;
import com.jshell.errors.JShellUnknownRuntimeException;

public class CP extends ShellCommand {

    private DirectoryTree dir_tree;
    private String SRC_PATH;
    private String DEST_PATH;
    private boolean isDirectory;

    public CP(DirectoryTree dir , String src , String dst , boolean isDir) {
        dir_tree = dir;
        SRC_PATH = src;
        DEST_PATH = dst;
    }

    @Override
    public void start() throws JShellDirectoryIsNotAFileException {
        if (SRC_PATH.startsWith(HOMEMARKER)) {
            SRC_PATH = HOMEDIR + SRC_PATH.substring(1);
        }
        if (!SRC_PATH.startsWith(DIRMARKER)) {
            SRC_PATH = dir_tree.getWorkingDirectory() + SRC_PATH;
        }
        if (!isDirectory && SRC_PATH.endsWith(DIRMARKER)) {
            throw new JShellDirectoryIsNotAFileException();
        }
        if (isDirectory && !SRC_PATH.endsWith(DIRMARKER)) {
            SRC_PATH += DIRMARKER;
        }
        if (DEST_PATH.startsWith(HOMEMARKER)) {
            DEST_PATH = HOMEDIR + DEST_PATH.substring(1);
        }
        if (!DEST_PATH.startsWith(DIRMARKER)) {
            DEST_PATH = dir_tree.getWorkingDirectory() + DEST_PATH;
        }
        if (!isDirectory && DEST_PATH.endsWith(DIRMARKER)) {
            throw new JShellDirectoryIsNotAFileException();
        }
        if (isDirectory && !DEST_PATH.endsWith(DIRMARKER)) {
            DEST_PATH += DIRMARKER;
        }
    }

    @Override
    public void run() throws JShellInvalidPathException , JShellUnknownRuntimeException , JShellDirectoryIsNotAFileException , JShellFileIsNotADirectoryException {
        File srcFile = new File(SRC_PATH);
        File dstFile = new File(DEST_PATH);
        boolean confirm = true;
        if (!srcFile.exists()) {
            throw new JShellInvalidPathException();
        }
        if (srcFile.isFile()) {
            if (dstFile.exists() && dstFile.isDirectory()) {
                throw new JShellDirectoryIsNotAFileException();
            } else if (dstFile.exists() && dstFile.isFile()) {
                STDOUT.print("File \"" + dstFile.getName() + "\" already exists. Replace? (Y/N): " , JSHELL_TERMINAL_CONSOLE);
                String response = STDIN.readUserInput(JSHELL_TERMINAL_CONSOLE);
                if (response.toUpperCase().equals("Y")) {
                    confirm = true;
                } else if (response.toUpperCase().equals("N")) {
                    STDOUT.println(ANSI_RED + "Cancelled." + ANSI_RESET, JSHELL_TERMINAL_CONSOLE);
                    confirm = false;
                } else {
                    STDOUT.println(ANSI_RED + "Invalid response." + ANSI_RESET, JSHELL_TERMINAL_CONSOLE);
                    confirm = false;
                }
            }
        } else if (srcFile.isDirectory()) {
            if (dstFile.exists() && dstFile.isFile()) {
                throw new JShellFileIsNotADirectoryException();
            } else if (dstFile.exists() && dstFile.isDirectory()) {

            }
        } else {
            throw new JShellUnknownRuntimeException();
        }

        if (confirm) {
            copy();
        }
    }

    @Override
    public void end() throws Exception {

    }

    @Override
    public void interrupt() {

    }

    public void copy() {
        try {
            CopyOption[] options = new CopyOption[]{StandardCopyOption.REPLACE_EXISTING , StandardCopyOption.COPY_ATTRIBUTES};
            Files.copy(Paths.get(SRC_PATH) , Paths.get(DEST_PATH) , options);
        } catch (Exception e) {
            System.out.println("Could not copy");
        }
    }
}
