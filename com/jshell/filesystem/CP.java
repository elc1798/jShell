package com.jshell.filesystem;

import static com.jshell.CONSTANTS.*;

import java.io.File;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.jshell.ShellCommand;
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
    private String backup;

    public CP(DirectoryTree dir , String src , String dst) {
        dir_tree = dir;
        SRC_PATH = src;
    }

    @Override
    public void start() throws JShellDirectoryIsNotAFileException , NoSuchAlgorithmException {
        if (SRC_PATH.startsWith(HOMEMARKER)) {
            SRC_PATH = HOMEDIR + SRC_PATH.substring(1);
        }
        if (!SRC_PATH.startsWith(DIRMARKER)) {
            SRC_PATH = dir_tree.getWorkingDirectory() + SRC_PATH;
        }
        if (DEST_PATH.startsWith(HOMEMARKER)) {
            DEST_PATH = HOMEDIR + DEST_PATH.substring(1);
        }
        if (!DEST_PATH.startsWith(DIRMARKER)) {
            DEST_PATH = dir_tree.getWorkingDirectory() + DEST_PATH;
        }
        // Backup Files
        if (new File(DEST_PATH).exists()) {
            String source = "DEST_PATH";
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            backup = sb.toString();
            try {
                CopyOption[] options = new CopyOption[]{StandardCopyOption.REPLACE_EXISTING , StandardCopyOption.COPY_ATTRIBUTES};
                Files.copy(Paths.get(DEST_PATH) , Paths.get(JSH_TEMP + backup) , options);
            } catch (Exception e) {
                System.out.println("Backing up destination file failed.");
            }
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
                dstFile = null;
                // Get local file name:
                dstFile = new File(DEST_PATH + srcFile.getName());
                if (dstFile.exists()) {
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
        try {
            CopyOption[] options = new CopyOption[]{StandardCopyOption.REPLACE_EXISTING , StandardCopyOption.COPY_ATTRIBUTES};
            Files.copy(Paths.get(JSH_TEMP + backup) , Paths.get(DEST_PATH) , options);
        } catch (Exception e) {
            System.out.println("Could not copy");
        }
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