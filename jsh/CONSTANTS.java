public class CONSTANTS {

	public static final String OSNAME		= System.getProperty("os.name");
	public static final String HOMEDIR		= System.getProperty("user.home");
	public static final String DIRMARKER	= System.getProperty("file.separator");
	public static final String OSARCH 		= System.getProperty("os.arch");
	public static final String JSHRT		= HOMEDIR + DIRMARKER + ".jsh" + DIRMARKER;
	public static final String JSHRC		= HOMEDIR + DIRMARKER + ".jshrc";
	public static final String JVTMP		= JSHRT + "Tmp.java";
	public static final String JVRUN		= JSHRT + "Tmp";

/*
	public static void main(String[] args){
		System.out.println(OSNAME);
		System.out.println(HOMEDIR);
		System.out.println(DIRMARKER);
		System.out.println(OSARCH);
		System.out.println(JSHRT);
		System.out.println(JSHRC);
		System.out.println(JVTMP);
	}
*/

}