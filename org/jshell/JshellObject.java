package org.jshell;

import org.jshell.utils.JshellCommand;

/**
 * An object that Jshell can read. Among these object classes are {@code Integer},
 * {@code Double}, {@code String}, {@code Boolean}, and {@code JshellCommand}.
 *
 * @author elc1798
 *
 */
public class JshellObject {

    private Object value;

    /**
     * Creates a new JshellObject with value of {@code null}.
     */
    public JshellObject() {
        value = null;
    }

    /**
     * Creates a new JshellObject with value of {@code obj}. If the class type
     * of {@code obj} is an invalid type of JshellObject, the value will be set
     * to null instead.
     * @param obj - value to set the JshellObject to
     */
    public JshellObject(Object obj) {
        value = obj;
        if (!isValid()) {
            value = null;
        }
    }

    /**
     * Sets the value of this JshellObject to {@code obj}. If the class type of
     * {@code obj} is not valid, then the current value will NOT change.
     *
     * @param obj - value to set the JshellObject to
     */
    public void setValue(Object obj) {
        Object oldValue = value;
        value = obj;
        if (!isValid()) {
            value = oldValue;
        }
    }

    /**
     * @return The current value of this JshellObject
     */
    public Object getValue() {
        return value;
    }

    /**
     * A JshellObject is limited to certain types described in the Javadoc of
     * {@link JshellObject}
     *
     * @return True if the current value is a valid datatype, false otherwise
     */
    public boolean isValid() {
        return value == null ||
                value.getClass().equals(Integer.class) ||
                value.getClass().equals(Double.class) ||
                value.getClass().equals(String.class) ||
                value.getClass().equals(Boolean.class) ||
                value.getClass().equals(JshellCommand.class);
    }
}
