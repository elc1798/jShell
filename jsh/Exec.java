import java.util.*;
import java.io.*;

public class Exec {

//		System Variables

	private static String OSNAME = System.getProperty("os.name");
	private static String OSARCH = System.getProperty("os.arch");
	private static String HOMEDIR = System.getProperty("user.home");
	private static Scanner inputStream = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("JShell loading... ");
		System.out.println("OS Detected: " + OSNAME + " running on " + OSARCH + " architecture");
		System.out.println("Home Directory: " + HOMEDIR);

		if (OSNAME.substring(0 , 3).toUpperCase().equals("WIN")){
			System.out.println("JShell is built for *nix systems and cannot be run on Windows");
			System.exit(0);
		}

		String buffer = null;
		JShellSystem instance = new JShellSystem();

		while (true) {
			System.out.print(instance.currDir + "$ ");
			//Build input
			buffer = "";
//			System.out.println("Checkpoint");
			buffer = inputStream.nextLine();
			JShell.processCommand(buffer , instance);
		}
	}
}
