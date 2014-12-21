jShell
======

Shell Written In Java, For Java

# Features to be added:

  - Persistent subprocess support
  
  - Tab completetion
  
  - Ctrl-C , Ctrl-D kill process key stroke
  
# Current Features:

## Version 0.03a

  - JVC now has --import, --no-compile, and --method flags:
      ```java
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
