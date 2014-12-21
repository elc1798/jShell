import java.util.ArrayList;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class JVConsole {
	
	public BufferedWriter jvtmp = null;
	public ArrayList<String> imports = new ArrayList<String>();
	public ArrayList<String> contents = new ArrayList<String>();
	public ArrayList<String> methods = new ArrayList<String>();
	private static final String CLASSPREFIX = "public class Tmp { public static void main(String[] args) {";
	private static final String CLOSEBRACKET = "}";
	
	/*
	 * The last thing added by the user:
	 * 0 = an import
	 * 1 = contents (in main)
	 * 2 = a method
	 */
	
	private int last = -1;
	
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
	
	public void addToMain(String jvcInputStream) {
		contents.add(jvcInputStream);
		last = 1;
	}
	
	public void rmLast() {
		switch (last) {
		case 0:
			imports.remove(imports.size() - 1);
			break;
		case 1:
			contents.remove(contents.size() - 1);
			break;
		case 2:
			methods.remove(methods.size() - 1);
			break;
		}
	}
	
	public void addImport(String jvcInputStream) {
		imports.add(jvcInputStream);
		last = 0;
	}
	
	public void addMethod(String jvcInputStream) {
		methods.add(jvcInputStream);
		last = 2;
	}
	
	public void JVCFlush() {
		contents = null;
		contents = new ArrayList<String>();
	}
	
	public void compile() {
		try {
			for (int i = 0; i < imports.size(); i++) {
				jvtmp.write(imports.get(i));
//				System.out.println(imports.get(i));
			}
			jvtmp.write(CLASSPREFIX); //Start main
//			System.out.println(CLASSPREFIX);
			for (int i = 0; i < contents.size(); i++) {
				jvtmp.write(contents.get(i)); //Insert into main
//				System.out.println(contents.get(i));
			}
			jvtmp.write(CLOSEBRACKET); //Close main
//			System.out.println(CLOSEBRACKET);
			for (int i = 0; i < methods.size(); i++) {
				jvtmp.write(methods.get(i)); //Add other methods
//				System.out.println(methods.get(i));
			}
			jvtmp.write(CLOSEBRACKET); //Close the file
//			System.out.println(CLOSEBRACKET);
		} catch(Exception e) {
			System.out.println("Java Virtual Console Writout failed.");
		} finally {
			closeVirtualShell();
		}
		JShell.subprocess("javac " + CONSTANTS.JVTMP);
		JShell.subprocess("java -cp " + CONSTANTS.JSHRT + " Tmp");
		//System.exit(0);
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