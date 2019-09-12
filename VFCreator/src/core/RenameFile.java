package core;

import java.io.File;

public class RenameFile {
	
	public static String rename(File file, String suffix) {
		return(renameWork(file, suffix));
	}
	
	public static String rename(String path, String suffix) {
		return(renameWork(new File(path), suffix));
	}
	
	private static String renameWork(File file, String suffix) {
		String path = file.getAbsolutePath();
		if(file.isDirectory()) {
			return(path+suffix);
		} else if(file.isFile()) {
			String p1 = path.substring(0, path.lastIndexOf("."));
			String p2 = path.substring(path.lastIndexOf("."), path.length());
			return(p1+suffix+p2);
		}
		return(null);
	}
	
	public static File renameFile(File file, String suffix)
	{
		String path = file.getAbsolutePath();
		if(file.isDirectory()) {
			return(new File(path+suffix));
		} else if(file.isFile()) {
			String p1 = path.substring(0, path.lastIndexOf("."));
			String p2 = path.substring(path.lastIndexOf("."), path.length());
			return(new File(p1+suffix+p2));
		}
		return(null);
	}
	
}
