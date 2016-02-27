package org.jshell;

import java.util.concurrent.ConcurrentHashMap;

/**
 * A HashMap to keep track of Jshell environment variables, aliases, functions,
 * etc. At its core, {@code JshellEnvVarTracker} is a {@link ConcurrentHashMap}.
 *
 * @author photoXin
 *
 */
public class JshellEnvVarTracker {

    private ConcurrentHashMap<String, JshellObject> dictionary;

    /**
     * Creates a new {@code JshellEnvVarTracker} with key type {@link String}
     * and value type {@link JshellObject}.
     */
    public JshellEnvVarTracker() {
        dictionary = new ConcurrentHashMap<String, JshellObject>();
    }

    /**
     * @param key - A String that represents a key for the HashMap
     * @return True if the key exists, false otherwise
     */
    public boolean contains(String key) {
        return dictionary.containsKey(key);
    }

    /**
     * @param key - A String that represents a key for the HashMap
     * @return The value that is represented by the provided key, {@code null}
     *         otherwise
     */
    public JshellObject get(String key) {
        return dictionary.get(key);
    }

    /**
     * Inserts a new JshellObject ({@code jobj}) into the HashMap, with the
     * provided key.
     *
     * @param key - A String that represents a key for the HashMap
     * @param jobj - A JshellObject
     */
    public void set(String key, JshellObject jobj) {
        dictionary.put(key, jobj);
    }
}
