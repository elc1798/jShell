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
//			System.out.println("Subprocess opened");
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
	
	/**
	 * @param buffer: The command to pass
	 * @param instance: Shell System Access instance to modify
	 */
	
	public static void processCommand(String buffer , JShellSystem instance) {
		if (buffer.equalsIgnoreCase("quit") || buffer.equalsIgnoreCase("exit")) {
			System.exit(0);
		} else if (buffer.equalsIgnoreCase("cd")) {
			instance.cd(CONSTANTS.HOMEDIR);
		} else if (buffer.contains("cd ")){
			instance.cd(buffer.substring(3));
		} else if (buffer.equalsIgnoreCase("ls")) {
			instance.ls(instance.currDir);
		} else if (buffer.contains("ls") && buffer.contains("..")) {
			
		} else if (buffer.contains("ls ")) {
			instance.ls(buffer.substring(3));
		} else if (buffer.length() >= 4) {
			if (buffer.substring(0 , 4).toUpperCase().equals("JSH~")) {
				buffer = buffer.substring(4);
			} else {
				JShell.subprocess(buffer);
			}
		} else {
			JShell.subprocess(buffer);
		}
	}

}
