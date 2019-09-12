package core;

import com.sun.jna.Native;

public class SymLink {
	
	public static void main(String args[]) {
		//Boolean a = Kernel32.INSTANCE.CreateSymbolicLink("C:\\Users\\James\\Desktop\\test_vf.txt", "C:\\Users\\James\\Desktop\\test.txt", 0x0);
		Boolean a = Kernel32.INSTANCE.CreateSymbolicLink("C:\\Users\\Chrispy\\Desktop\\Adobe Acrobat DC_vf", "C:\\Users\\Chrispy\\Desktop\\Adobe Acrobat DC", 0x0);
		System.out.println(a);
	}
	
	public interface Kernel32 extends com.sun.jna.platform.win32.Kernel32 {
	    Kernel32 INSTANCE = (Kernel32)Native.loadLibrary("kernel32", Kernel32.class, com.sun.jna.win32.W32APIOptions.DEFAULT_OPTIONS);
	    Boolean CreateSymbolicLink(String lpSymlinkFileName, String lpTargetFileName, int dwFlags);
	}
	
}
