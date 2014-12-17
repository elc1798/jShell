import java.util.*;
import java.io.PrintWriter;
import java.io.File;

public class JShellSystem {

	// Container for a session of JShell, accessing system for directory moving and access
	
	public String currDir = System.getProperty("user.dir");
	
	public static boolean checkPathExistence(String path) {
		File f = new File(path);
		return (f.exists() && f.isDirectory());
	}
	
	public void cd(String path) {

		String dirBackup = currDir;
		String[] splitPath = path.split(CONSTANTS.DIRMARKER);	//Split path based on the marker directory marker
		if (path.charAt(0) == CONSTANTS.DIRMARKER.charAt(0)) {
			currDir = "";
		}

		try {
			for (int i = 0; i < splitPath.length; i++) {
				if (splitPath[i].equals("..")) {
					currDir = currDir.substring(0 , currDir.length() - 1);
					for (int k = currDir.length() - 1; !currDir.substring(k , k + 1).equals(CONSTANTS.DIRMARKER); k--) {
						currDir = currDir.substring(0 , k);
					}
//				} else if () {

				} else {
					currDir += splitPath[i] + CONSTANTS.DIRMARKER;
				}
				if (!checkPathExistence(currDir)) {
					throw new Exception();
				}
			}
		} catch(Exception e) {
			System.out.println(path + " does not exist");
			currDir = dirBackup;
		}

	}
	
	public void ls(String path) {
		JShell.subprocess("ls " + path);
	}

	public void crtFile(String path , String fileName){
		try {
			File NEWFILE = new File(path + CONSTANTS.DIRMARKER + fileName);
			if (!NEWFILE.exists()) {
				NEWFILE.createNewFile();
			} else {
				System.out.println("File already exists. Cannot Create.");
			}
		} catch(Exception e){
			System.out.println("Creating file failed.");
		}
	}
	
	public void mv(String src , String dst){
		JShell.subprocess("mv " + src + " " + dst);
	}
	
}
