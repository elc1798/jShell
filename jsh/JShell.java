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
			buffer = buffer.substring(5); //Remove the jsh~ prefix, it's unnecessary
			if (buffer.contains("--no-compile ")) {
				compileJSHVC = false;
				buffer = buffer.replace("--no-compile " , "");
			}
			if (buffer.contains("--flush")) {
				System.out.println(CONSTANTS.ANSI_CYAN + "JVC contents flushed" + CONSTANTS.ANSI_RESET);
				cmd.JVCFlush();
				compileJSHVC = false;
				buffer = "";
			} else if (buffer.contains("--show")) {
				System.out.println(CONSTANTS.ANSI_CYAN + "JVC contents: " + CONSTANTS.ANSI_RESET);
				cmd.show();
				compileJSHVC = false;
				buffer = "";
			} else if (buffer.contains("--undo")) {
				System.out.println(CONSTANTS.ANSI_CYAN + "JVC last move undone" + CONSTANTS.ANSI_RESET);
				cmd.rmLast();
				compileJSHVC = false;
				buffer = "";
			} else if (buffer.toLowerCase().contains("--dump ")) {
				System.out.println(CONSTANTS.ANSI_CYAN + "JVC contents dumped." + CONSTANTS.ANSI_RESET);
				buffer = buffer.replace("--dump " , "");
				buffer = buffer.replace("--DUMP " , "");
				compileJSHVC = false;
				if (buffer.charAt(0) == '/') {
					cmd.writeOut(new File(buffer));
				} else {
					cmd.writeOut(new File(instance.currDir + CONSTANTS.DIRMARKER + buffer));
				}
			} else if (buffer.contains("--import ") && !buffer.contains("--method ")) {
				//Add an import
				buffer = buffer.replace("--import ", "");
				if (buffer.charAt(buffer.length() - 1) != ';') {
					buffer += ";";
				}
				if (!buffer.startsWith("import ")) {
					System.out.println(CONSTANTS.ANSI_CYAN + "Automatically adding `import` keyword" + CONSTANTS.ANSI_RESET);
					buffer = "import " + buffer;
					cmd.addImport(buffer);
				} else {
					cmd.addImport(buffer);
				}
			} else if (!buffer.contains("--import ") && buffer.contains("--method ")) {
				//Add a method
				buffer = buffer.replace("--method " , "");
				if (!buffer.contains("(")) {
					System.out.println(CONSTANTS.ANSI_YELLOW + "Warning: ( missing from parameter block" + CONSTANTS.ANSI_RESET);
				}
				if (!buffer.contains(")")) {
					System.out.println(CONSTANTS.ANSI_YELLOW + "Warning: ) missing from parameter block" + CONSTANTS.ANSI_RESET);
				}
				if (!buffer.contains("{")) {
					System.out.println(CONSTANTS.ANSI_YELLOW + "Warning: { missing from code block start" + CONSTANTS.ANSI_RESET);
				}
				if (!buffer.contains("}")) {
					System.out.println(CONSTANTS.ANSI_YELLOW + "Warning: } missing from code block end" + CONSTANTS.ANSI_RESET);
				}
				cmd.addMethod(buffer);
			} else if (!buffer.contains("--import ") && !buffer.contains("--method ") && buffer.length() > 0) {
				if (buffer.charAt(buffer.length() - 1) != ';') {
					buffer += ";";
				}
				cmd.addToMain(buffer);
			}
			if (compileJSHVC) {
				cmd.compile();
			}
		} else if (buffer.equalsIgnoreCase("JSH~") || buffer.equalsIgnoreCase("JSH~ ")) {
			System.out.println(CONSTANTS.ANSI_CYAN + "Compiling Java Virtual Console contents..." + CONSTANTS.ANSI_RESET);
			cmd.compile();
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
		} else if (buffer.startsWith("cp ")) {
			//mv source destination
			String[] params = buffer.split(" ");
			if (params.length != 3) {
				System.out.println("Syntax: cp srcFile dstFile");
			} else {
				instance.cp(instance.currDir + params[1] , instance.currDir + params[2]);
			}
		} else if (buffer.startsWith("mv ")) {
			//mv source destination
			String[] params = buffer.split(" ");
			if (params.length != 3) {
				System.out.println("Syntax: mv srcFile dstFile");
			} else {
				instance.mv(instance.currDir + params[1] , instance.currDir + params[2]);
			}
		} else if (buffer.startsWith("touch ")) {
			//mv source destination
			String[] params = buffer.split(" ");
			for (int i = 1; i < params.length; i++) {
				instance.crtFile(instance.currDir , params[i]);
			}
		} else if (buffer.startsWith("mkdir ")) {
			//mv source destination
			String[] params = buffer.split(" ");
			for (int i = 1; i < params.length; i++) {
				instance.crtDir(instance.currDir , params[i]);
			}
		} else if (buffer.startsWith("rm ")) {
			//mv source destination
			String[] params = buffer.split(" ");
			for (int i = 1; i < params.length; i++) {
				instance.rmFile(instance.currDir , params[i]);
			}
		} else if (buffer.length() > 0){ //Do not execute a 0 length string
			JShell.subprocess(buffer);
		} else {
			// DO NOTHING!
		}
	}

}
