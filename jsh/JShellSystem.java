import java.util.*;
import java.io.PrintWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.CopyOption;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class JShellSystem {

	// Container for a session of JShell, accessing system for directory moving and access
	
	public String currDir = System.getProperty("user.dir");
	
	public JShellSystem() {
		cd(CONSTANTS.HOMEDIR);
	}
	
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

		if (currDir.length() == 0) {
			currDir = CONSTANTS.DIRMARKER;
		}
		
	}
	
	/*
	 * The ls and lsShowHidden functions like ls -l or ls -al in bash
	 * Coloring and priority goes as the following:
	 * Hidden - Blue
	 * Directory - Green
	 * Executable - Yellow
	 * Images - Red
	 * Other Files - White
	 */
	public void ls(String path) {
		String backup = currDir;
		cd(path);
		File[] files = new File(currDir).listFiles();
		Arrays.sort(files);
		for (File file : files) {
		    if (file.isHidden()) {
		    	//Do nothing! Because we don't want to show hidden!
		    } else if (file.isDirectory()) {
		    	System.out.println(CONSTANTS.ANSI_GREEN + file.getName() + CONSTANTS.ANSI_RESET);
		    } else if (file.canExecute()) {
		    	System.out.println(CONSTANTS.ANSI_YELLOW + file.getName() + CONSTANTS.ANSI_RESET);
		    } else if (file.getName().endsWith(".png") || 
		    		file.getName().endsWith(".jpg") || 
		    		file.getName().endsWith(".jpeg") ||
		    		file.getName().endsWith(".mp4") ||
		    		file.getName().endsWith(".mp3") ||
		    		file.getName().endsWith(".mkv")
		    		) {
		    	System.out.println(CONSTANTS.ANSI_RED + file.getName() + CONSTANTS.ANSI_RESET);
		    } else {
		    	System.out.println(file.getName());
		    }
		}
		currDir = backup;
	}
	
	public void lsShowHidden(String path) {
		String backup = currDir;
		cd(path);
		File[] files = new File(currDir).listFiles();
		Arrays.sort(files);
		for (File file : files) {
		    if (file.isHidden()) {
		    	System.out.println(CONSTANTS.ANSI_BLUE + file.getName() + CONSTANTS.ANSI_RESET);
		    } else if (file.isDirectory()) {
		    	System.out.println(CONSTANTS.ANSI_GREEN + file.getName() + CONSTANTS.ANSI_RESET);
		    } else if (file.canExecute()) {
		        System.out.println(CONSTANTS.ANSI_YELLOW + file.getName() + CONSTANTS.ANSI_RESET);
		    } else if (file.getName().endsWith(".png") || 
		    		file.getName().endsWith(".jpg") || 
		    		file.getName().endsWith(".jpeg") ||
		    		file.getName().endsWith(".mp4") ||
		    		file.getName().endsWith(".mp3") ||
		    		file.getName().endsWith(".mkv")
		    		) {
		    	System.out.println(CONSTANTS.ANSI_RED + file.getName() + CONSTANTS.ANSI_RESET);
		    } else {
		    	System.out.println(file.getName());
		    }
		}
		currDir = backup;
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
	
	public void crtDir(String path , String dirName){
		try {
			File NEWDIR = new File(path + CONSTANTS.DIRMARKER + dirName);
			if (!NEWDIR.exists()) {
				NEWDIR.mkdir();
			} else {
				System.out.println("File already exists. Cannot Create.");
			}
		} catch(Exception e){
			System.out.println("Creating file failed.");
		}
	}
	
	public void rmFile(String path , String fileName){
		try {
			File TARGETFILE = new File(path + CONSTANTS.DIRMARKER + fileName);
			if (TARGETFILE.exists()) {
				TARGETFILE.delete();
			} else {
				System.out.println("File does not exist. Cannot Delete.");
			}
		} catch(Exception e){
			System.out.println("Deleting file failed.");
		}
	}
	
	public void cp(String src , String dst){
		File SRC = new File(src);
		File DST = new File(dst);
		if (!SRC.exists()) { //If the source to be copied does not exist, null the variables and end with message
			SRC = null;
			DST = null;
			System.out.println("Source path: " + src + " does not exist and cannot be moved.");
		} else {
			if (SRC.isFile()) {
				if (DST.exists() && DST.isFile()) { //File to file copying, confirm overwrites
					System.out.println("Destination file (" + DST.getName() + ") contains data. Overwrite? ");
					Scanner response = new Scanner(System.in);
					boolean hasResponded = false;
					while (!hasResponded) {
						System.out.print("(Y / N): ");
						
//============================================================================================
						
						if (response.nextLine().equalsIgnoreCase("y")) {
							hasResponded = true;
							//Copy the file over
							try {
								CopyOption[] options = new CopyOption[]{StandardCopyOption.REPLACE_EXISTING , StandardCopyOption.COPY_ATTRIBUTES};
								Files.copy(Paths.get(src) , Paths.get(dst) , options);
							} catch (Exception e) {
								System.out.println("Could not copy");
							}
						} else if (response.nextLine().equalsIgnoreCase("n")) {
							hasResponded = true;
							break;
						}
						
//============================================================================================
						
					}
					response = null;
				} else if (!DST.exists()) {
					//File does not exist yet, create new file
					try {
						DST.createNewFile();
						CopyOption[] options = new CopyOption[]{StandardCopyOption.REPLACE_EXISTING , StandardCopyOption.COPY_ATTRIBUTES};
						Files.copy(Paths.get(src) , Paths.get(dst) , options);
					} catch(Exception ex) {
						System.out.println("Error creating new file");
					}
				} else if (DST.exists() && DST.isDirectory()) {
					//Copy src INTO dst directory
					DST = null; //Null it!
					DST = new File(dst + SRC.getName()); //New path with same name as SRC
					try {
						DST.createNewFile(); //Create new file
						CopyOption[] options = new CopyOption[]{StandardCopyOption.REPLACE_EXISTING , StandardCopyOption.COPY_ATTRIBUTES};
						Files.copy(Paths.get(src) , Paths.get(DST.getAbsolutePath()) , options);
					} catch (Exception e) {
						System.out.println("Error creating new file");
					}
				}
			}
		}
	}
	
	public void mv(String src , String dst){
		cp(src , dst); //Copy it!
		try {
			File SRC = new File(src);
			SRC.delete();
		} catch(Exception e) {
			System.out.println("Error deleting the source file");
		}
	}
	
	public void clear() {
		System.out.print("\033\143");
	}
	
}
