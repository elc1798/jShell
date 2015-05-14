package com.jshell;

public class CONSTANTS {

    public static final String DIRMARKER    = System.getProperty("file.separator");
    public static final String OSNAME       = System.getProperty("os.name");
    public static final String HOMEDIR      = System.getProperty("user.home");
    public static final String OSARCH       = System.getProperty("os.arch");
    public static final String JRE_VER      = System.getProperty("java.version");
    public static final String ROOT_DIR     = "/";
    public static final String HOMEMARKER   = "~";
    public static final String ASCEND_DIR   = "..";
    public static final String JSHRT        = HOMEDIR + DIRMARKER + ".jsh" + DIRMARKER;
    public static final String JSHRC        = HOMEDIR + DIRMARKER + ".jshrc";
    public static final String JVTMP        = JSHRT + "Tmp.java";
    public static final String JVCLASS      = JSHRT + "Tmp.class";
    public static final String JVRUN        = JSHRT + "Tmp";

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final int JSHELL_TERMINAL_CONSOLE = 0;
    public static final int JSHELL_JAVA_SWING_CONSOLE = 1;

}