package org.jshell;

/**
 * Interface containing project constant values for easy accessibility and
 * refactoring. This is an interface rather than a class so all the values are
 * inherently public, which saves typing.
 *
 * @author elc1798
 *
 */
public interface Constants {
    String cwdSystemProperty = "user.dir";
    String userHomeDir = System.getProperty("user.home");

    String columns = "COLS";
    String rows = "LINES";
}
