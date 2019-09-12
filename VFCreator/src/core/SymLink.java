package core;

import com.sun.jna.Native;

public class SymLink {
	
	public interface Kernel32 extends com.sun.jna.platform.win32.Kernel32 {
	    Kernel32 INSTANCE = (Kernel32)Native.loadLibrary("kernel32", Kernel32.class, com.sun.jna.win32.W32APIOptions.DEFAULT_OPTIONS);
	    Boolean CreateSymbolicLink(String lpSymlinkFileName, String lpTargetFileName, int dwFlags);
	}
	
}
