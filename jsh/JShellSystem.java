import java.util.*;
import java.io.File;

public class JShellSystem {

	// Container for a session of JShell, accessing system for directory moving and access

	public static String currDir = System.getProperty("user.dir");		//User starts in current working directory, defined by .jshrc
	public static String OSNAME = System.getProperty("os.name");		//User OS
	
	public static boolean checkPathExistence(String path) {
		File f = new File(path);
		return (f.exists() && f.isDirectory());
	}
	
	public void cd(String path) {

		String dirBackup = currDir;
		String[] splitPath = path.split(System.getProperty("file.separator"));	//Split path based on the marker directory marker
		if (path.charAt(0) == System.getProperty("file.separator").charAt(0)) {
			currDir = System.getProperty("file.separator");
		}

		try {
			for (int i = 0; i < splitPath.length; i++) {
				if (splitPath[i].equals("..")) {
					currDir = currDir.substring(0 , currDir.length() - 1);
					for (int k = currDir.length() - 1; !currDir.substring(k , k + 1).equals(System.getProperty("file.separator")); k--) {
						currDir = currDir.substring(0 , k);
					}
//				} else if () {

				} else {
					currDir += splitPath[i] + System.getProperty("file.separator");
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

}
