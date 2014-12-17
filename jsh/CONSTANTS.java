public class CONSTANTS {

	public static String OSNAME		= System.getProperty("os.name");
	public static String HOMEDIR	= System.getProperty("user.home");
	public static String DIRMARKER	= System.getProperty("file.separator");
	public static String OSARCH 	= System.getProperty("os.arch");
	public static String JSHRT		= HOMEDIR + DIRMARKER + ".jsh" + DIRMARKER;
	public static String JSHRC		= HOMEDIR + DIRMARKER + ".jshrc";
	public static String JVTMP		= JSHRT + "tmp.java";

/*
	public static void main(String[] args){
		System.out.println(OSNAME);
		System.out.println(HOMEDIR);
		System.out.println(DIRMARKER);
		System.out.println(OSARCH);
		System.out.println(JSHRT);
		System.out.println(JSHRC);
	}
*/

}