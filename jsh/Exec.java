import java.util.*;
import java.io.*;

public class Exec {

//		System Variables

	private static Scanner inputStream = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("JShell loading... ");
		System.out.println("OS Detected: " + CONSTANTS.OSNAME + " running on " + CONSTANTS.OSARCH + " architecture");
		System.out.println("Home Directory: " + CONSTANTS.HOMEDIR);

		if (CONSTANTS.OSNAME.substring(0 , 3).toUpperCase().equals("WIN")){
			System.out.println("JShell is built for *nix systems and cannot be run on Windows");
			System.exit(0);
		}

		String buffer = null;
		JShellSystem instance = new JShellSystem();
		JVConsole cmd = new JVConsole();
		String CONSOLEPREFIX = "";
		
		while (true) {
			CONSOLEPREFIX = instance.currDir + "$ ";
			System.out.print(CONSOLEPREFIX.replaceFirst(CONSTANTS.HOMEDIR, "#HOME#"));
			//Build input
			buffer = "";
//			System.out.println("Checkpoint");
			buffer = inputStream.nextLine();
			JShell.processCommand(buffer , instance , cmd);
		}
	}
}
