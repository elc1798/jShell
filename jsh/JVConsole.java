import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class JVConsole {
	
	public BufferedWriter jvtmp = null;
	public ArrayList<String> contents = new ArrayList<String>();
	private static final String CLASSPREFIX = "public class Tmp { public static void main(String[] args) {";
	private static final String CLASSSUFFIX = "} }";
	
	public JVConsole() {
		
		startVirtualShell();
		
	}
	
	private void generateJSHELLFolder() {
		File JSHELLFOLDER = new File(CONSTANTS.JSHRT);
		if (!JSHELLFOLDER.isDirectory()) {
			try {
				JSHELLFOLDER.mkdir();
			} catch(Exception e) {
				System.out.println("Error creating jShell Root Directory");
				e.printStackTrace();
			}
		}
		File JSHELLTMP = new File(CONSTANTS.JVTMP);
		File JSHELLCLASS = new File(CONSTANTS.JVCLASS);
		try {
			JSHELLTMP.delete();	//Remove the file
			JSHELLTMP.createNewFile(); //Recreate the file
			JSHELLCLASS.delete();
			JSHELLCLASS = null;
			JSHELLTMP = null;
		} catch(Exception e) {
			System.out.println("Error creating the jShell Virtual Console");
		}
	}
	
	private void startVirtualShell() {
		generateJSHELLFolder();
		try {
			jvtmp = new BufferedWriter(new FileWriter(new File(CONSTANTS.JVTMP) , true));
		} catch(Exception e){
			System.out.println("Opening Temporary Java Console Session failed");
		}
	}
	
	private void closeVirtualShell() {
		try {
			jvtmp.flush();
			jvtmp.close();
		} catch(Exception e) {
			System.out.println("Temporary Java Console Session has not been opened yet");
		}
	}
	
	public void rebootVirtualShell() {
		closeVirtualShell();
		startVirtualShell();
	}
	
	public void console(String jvcInputStream){
		contents.add(jvcInputStream);
	}
	
	public void rmLast() {
		contents.remove(contents.size() - 1);
	}
	
	public void JVCFlush() {
		contents = null;
		contents = new ArrayList<String>();
	}
	
	public void compile() {
		try {
			
			jvtmp.write(CLASSPREFIX);
			for (int i = 0; i < contents.size(); i++) {
				jvtmp.write(contents.get(i));
			}
			jvtmp.write(CLASSSUFFIX);
		} catch(Exception e) {
			System.out.println("Java Virtual Console Writout failed.");
		} finally {
			closeVirtualShell();
		}
		JShell.subprocess("javac " + CONSTANTS.JVTMP);
		JShell.subprocess("java -cp " + CONSTANTS.JSHRT + " Tmp");
		startVirtualShell();
	}
	
/*
	public static void main(String[] args) {
		JVConsole cmd = new JVConsole();
		cmd.console("String S = \"HI\";");
		cmd.console("System.out.println(S);");
		cmd.compile();
	}
*/
	
}