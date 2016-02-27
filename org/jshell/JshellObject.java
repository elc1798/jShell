package org.jshell;

public class JshellObject {

    Object value;

    public JshellObject() {
    }

    public JshellObject(Object obj) {
        value = obj;
    }

    public void setValue(Object obj) {
        value = obj;
    }

    public Object getValue() {
        return value;
    }

}
