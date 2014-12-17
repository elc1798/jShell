import java.util.ArrayList;

public class JVConsole {
	
	public PrintWriter jvtmp = null;
	public ArrayList<String> contents = new ArrayList<String>();
	
	public JVConsole() {
		
		startVirtualShell();
		
	}
	
	private void startVirtualShell(){
		try {
			jvtmp = new PrintWriter(CONSTANTS.JVTMP);
		} catch(Exception e){
			System.out.println("Opening Temporary Java Console Session failed");
		}
	}
	
	public void console(String jvcInputStream){
		
	}
	
}