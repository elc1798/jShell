import java.util.*;
import java.io.*;

public class JShell{

//              System Variables

	ArrayList<Process> procQueue = new ArrayList<Process>();

	public static Runtime console = Runtime.getRuntime();

	public static void subprocess(String command){
		Process p = null;
		String stdout = "";
		try {
			p = console.exec(command);
//			The below is the equivalent to System.console(), but I wanted it to work in IDEs as well so I used BufferedReader
			BufferedReader stdin = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((stdout = stdin.readLine()) != null) {
				System.out.println(stdout);
			}
			System.out.println("\b");
			stdin.close();
		} catch(Exception e){
			System.out.println("Subprocess '" + command + "' could not be executed.");
			command = "compgen -ac | grep " + command.charAt(0);
			try {
				p = console.exec(command);
	                        BufferedReader stdin = new BufferedReader(new InputStreamReader(p.getInputStream()));
				System.out.println("Did you mean: ");
	                        while ((stdout = stdin.readLine()) != null) {
	                                System.out.println(stdout);
	                        }
	                        stdin.close();
			} catch(Exception ex) {
				System.out.println("Possible command listing failed.");
			}
		}
	}

	public static ArrayList<String> subprocessOutput(String command){
		Process p = null;
		String stdout = "";
		ArrayList<String> give = new ArrayList<String>();
		try {
			p = console.exec(command);
//			The below is the equivalent to System.console(), but I wanted it to work in IDEs as well so I used BufferedReader
			BufferedReader stdin = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((stdout = stdin.readLine()) != null) {
				give.add(stdout);
			}
			System.out.println("\b");
			stdin.close();
		} catch(Exception e){
			System.out.println("Subprocess '" + command + "' could not be executed.");
			command = "compgen -ac | grep " + command.charAt(0);
			try {
				p = console.exec(command);
	                        BufferedReader stdin = new BufferedReader(new InputStreamReader(p.getInputStream()));
				System.out.println("Did you mean: ");
	                        while ((stdout = stdin.readLine()) != null) {
	                                System.out.println(stdout);
	                        }
	                        stdin.close();
			} catch(Exception ex) {
				System.out.println("Possible command listing failed.");
			}
		}
		return give;
	}

	
	/**
	 * @param buffer: The command to pass
	 * @param instance: Shell System Access instance to modify
	 * @param cmd: Java Virtual Console instance to use
	 */

	public static void processCommand(String buffer , JShellSystem instance , JVConsole cmd) {
		if (buffer.length() >= 5 && buffer.substring(0 , 5).equalsIgnoreCase("JSH~ ")) {
			boolean compileJSHVC = true;
			buffer = buffer.substring(5);
			if (buffer.length() >= 13 && buffer.substring(0 , 13).equals("--no-compile ")) {
				compileJSHVC = false;
				buffer = buffer.substring(13);
			}
			if (buffer.charAt(buffer.length() - 1) != ';') {
				buffer += ";";
			}
			cmd.console(buffer);
			if (compileJSHVC) {
				cmd.compile();
			}
		} else if (buffer.equalsIgnoreCase("JSHVC --flush")) {
			cmd.JVCFlush();
		} else if (buffer.equalsIgnoreCase("quit") || buffer.equalsIgnoreCase("exit")) {
			System.exit(0);
		} else if (buffer.equalsIgnoreCase("cd") || buffer.equalsIgnoreCase("cd ")) {
			instance.cd(CONSTANTS.HOMEDIR);
		} else if (buffer.contains("cd ")){
			instance.cd(buffer.substring(3));
		} else if (buffer.equalsIgnoreCase("ls")) {
			instance.ls(instance.currDir);
		} else if (buffer.equalsIgnoreCase("ls -a")) {
			instance.lsShowHidden(instance.currDir);
		} else if (buffer.startsWith("ls -a ")) {
			instance.lsShowHidden(buffer.substring(6));
		} else if (buffer.startsWith("ls ") && buffer.contains("..")) {

		} else if (buffer.startsWith("ls ")) {
			instance.ls(buffer.substring(3));
		} else if (buffer.equalsIgnoreCase("clear")) {
			instance.clear();
		} else {
			JShell.subprocess(buffer);
		}
	}

}
