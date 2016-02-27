package org.jshell;

import java.util.concurrent.ConcurrentHashMap;

public class JshellEnvVarTracker {

    private ConcurrentHashMap<String, JshellObject> dictionary;

    public JshellEnvVarTracker() {
        dictionary = new ConcurrentHashMap<String, JshellObject>();
    }

    public boolean contains(String key) {
        return dictionary.containsKey(key);
    }

    public JshellObject get(String key) {
        return dictionary.get(key);
    }

    public void set(String key, JshellObject jobj) {
        dictionary.put(key, jobj);
    }
}
