package core;

import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.WinReg;

public class RegSetup {
	
	public static boolean checkReg() {
		String bPath = "*\\shell\\CreateVF\\command";
		String cPath = "Directory\\shell\\CreateVF\\command";
		if(Advapi32Util.registryKeyExists(WinReg.HKEY_CLASSES_ROOT, bPath) && Advapi32Util.registryKeyExists(WinReg.HKEY_CLASSES_ROOT, cPath)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void updateReg(String path) {
		String aPath = "\""+path+"\" \"%1\"";
		String bPath = "*\\shell\\CreateVF\\command";
		String cPath = "Directory\\shell\\CreateVF\\command";
		editReg(aPath, bPath);
		editReg(aPath, cPath);
	}
	
	private static void editReg(String path1, String path2) {
		String value = "";
		if(Advapi32Util.registryKeyExists(WinReg.HKEY_CLASSES_ROOT, path2)) {
			value = Advapi32Util.registryGetStringValue(WinReg.HKEY_CLASSES_ROOT, path2, "");
			if(value.equals(path1)) {
				return;
			} else {
				Advapi32Util.registrySetStringValue(WinReg.HKEY_CLASSES_ROOT, path2, "", path1);
			}
		} else {
			Advapi32Util.registryCreateKey(WinReg.HKEY_CLASSES_ROOT, path2);
			Advapi32Util.registrySetStringValue(WinReg.HKEY_CLASSES_ROOT, path2, "", path1);
		}
	}
	
	public static void removeReg() {
		remove("*\\shell\\CreateVF\\command");
		remove("*\\shell\\CreateVF");
		remove("Directory\\shell\\CreateVF\\command");
		remove("Directory\\shell\\CreateVF");
	}
	
	private static void remove(String path) {
		if(Advapi32Util.registryKeyExists(WinReg.HKEY_CLASSES_ROOT, path)) {
			Advapi32Util.registryDeleteKey(WinReg.HKEY_CLASSES_ROOT, path);
		}
	}
}
