package core;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;

public class SymLinkWrapper {
	
	public static boolean createSymLink(String source, String dest, int flags) {
		boolean result = Kernel32.INSTANCE.CreateSymbolicLinkA(dest, source, flags);
		return result;
	}
	
	public interface Kernel32 extends StdCallLibrary {
	      Kernel32 INSTANCE = (Kernel32) Native.loadLibrary("kernel32", Kernel32.class);
	      boolean CreateSymbolicLinkA(String lpSymlinkFileName, String lpTargetFileName, int dwFlags);
	}
}
