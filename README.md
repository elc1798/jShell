jShell
======

Shell Written In Java, For Java

# Features to be added:

  - Persistent subprocess support
  
  - Tab completion
  
  - Ctrl-C , Ctrl-D kill process key stroke
  
# Current Features:

## Version 0.11a

  - Added --show and --undo to JVC
  - Demo:
    ```
    JShell loading... 
    OS Detected: Linux running on amd64 architecture
    Home Directory: /home/sublimau5
    Java Version Detected: 1.7.0_65
    #HOME#/$ jsh~ --show
    JVC contents: 
    public class JVC {
    	public static void main(String[] args) {
    	}
    }
    #HOME#/$ jsh --import --no-compile java.util.*
    Subprocess 'jsh --import --no-compile java.util.*' could not be executed.
    Possible command listing failed.
    #HOME#/$ jsh~ --import --no-compile java.util.*
    Automatically adding `import` keyword
    #HOME#/$ jsh~ --show
    JVC contents: 
    import java.util.*;
    public class JVC {
    	public static void main(String[] args) {
    	}
    }
    #HOME#/$ jsh~ --no-compile --method public static void process(String s) {
    Warning: } missing from code block end
    #HOME#/$ jsh~ --no-compile --method for (int i = 0; i < s.length(); i++) {
    Warning: } missing from code block end
    #HOME#/$ jsh~ --show
    JVC contents: 
    import java.util.*;
    public class JVC {
    	public static void main(String[] args) {
    	}
    	public static void process(String s) {
    	for (int i = 0; i < s.length(); i++) {
    }
    #HOME#/$ jsh~ --no-compile --method System.out.println(s.charAt(i));
    Warning: { missing from code block start
    Warning: } missing from code block end
    #HOME#/$ jsh~ --show
    JVC contents: 
    import java.util.*;
    public class JVC {
    	public static void main(String[] args) {
    	}
    	public static void process(String s) {
    	for (int i = 0; i < s.length(); i++) {
    	System.out.println(s.charAt(i));
    }
    #HOME#/$ jsh~ --undo
    JVC last move undone
    #HOME#/$ jsh~ --show
    JVC contents: 
    import java.util.*;
    public class JVC {
    	public static void main(String[] args) {
    	}
    	public static void process(String s) {
    	for (int i = 0; i < s.length(); i++) {
    }
    #HOME#/$ jsh~ --no-compile --method System.out.println(s.charAt(i));
    Warning: { missing from code block start
    Warning: } missing from code block end
    #HOME#/$ jsh~ --show
    JVC contents: 
    import java.util.*;
    public class JVC {
    	public static void main(String[] args) {
    	}
    	public static void process(String s) {
    	for (int i = 0; i < s.length(); i++) {
    	System.out.println(s.charAt(i));
    }
    #HOME#/$ jsh~ --no-compile --method }}
    Warning: ( missing from parameter block
    Warning: ) missing from parameter block
    Warning: { missing from code block start
    #HOME#/$ jsh~ --show      
    JVC contents: 
    import java.util.*;
    public class JVC {
    	public static void main(String[] args) {
    	}
    	public static void process(String s) {
    	for (int i = 0; i < s.length(); i++) {
    	System.out.println(s.charAt(i));
    	}}
    }
    #HOME#/$ jsh~ --no-compile process("ABCDEFGHIJKLMNOPQRSTUVWXYZ") 
    #HOME#/$ jsh~
    Compiling Java Virtual Console contents...
    
    A
    B
    C
    D
    E
    F
    G
    H
    I
    J
    K
    L
    M
    N
    O
    P
    Q
    R
    S
    T
    U
    V
    W
    X
    Y
    Z

    #HOME#/$ 
    ```

## Version 0.10a

  - Added more stability and exception pkills
  - JVC now automatically adds 'import' at the beginning of the --import flag if it isn't there
  - Added JVC file writeout to save JVC contents (--dump flag)
  - Fixed bug where jsh~ --flush wasn't working, when jshvc --flush was
  - No longer tries to execute commands of 0 length strings

## Version 0.03a

  - JVC now has --import, --no-compile, and --method flags:
      ```
      JShell loading... 
      OS Detected: Linux running on amd64 architecture
      Home Directory: /home/sublimau5
      Java Version Detected: 1.7.0_65
      #HOME#/$ jsh~ --no-compile int[] i = new int[]{5 , 10 , 1 , -75}
      #HOME#/$ jsh~ --import import java.util.Arrays
      #HOME#/$ jsh~ --no-compile Arrays.sort(i)
      #HOME#/$ jsh~ System.out.println(Arrays.toString(i))
      
      [-75, 1, 5, 10]
      
      #HOME#/$ 
      ```

## Version 0.02a

  - Added Persistent Java console, that resembles a Python console

  - Text Highlighting

## Version 0.01a

  - BASH command support
  
  - Directory access

  - Non - persistent process support
