package core;

import gui.MainGUI;

import com.seerofspace.utils.windows.Elevator;

import java.awt.EventQueue;
import java.io.File;

public class Main {
	
	public static String path = new File("VFCreator.exe").getAbsolutePath();
	//public static String path = new File("VFCreator.jar").getAbsolutePath();
	
	public static void main(String args[]) {
		if(args.length == 0) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MainGUI window = new MainGUI();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} else if(args.length == 1) {
			if(args[0].equals("regRemove")) {
				RegSetup.removeReg();
			} else if(args[0].equals("regUpdate")) {
				RegSetup.updateReg(path);
			} else {
				createFolder(args[0], RenameFile.rename(args[0], "_vf"));
			}
		} else if (args.length == 2) {
			createFolder(args[0], args[1]);
		} else if (args.length == 3) {
			SymLinkWrapper.createSymLink(args[0], args[1], Integer.parseInt(args[2]));
		}
	}
	
	public static void createFolder(String source, String dest) {
		File fileSource = new File(source);
		File fileDest = new File(dest);
		int isDir = fileSource.isDirectory() ? 1 : 0;
		if(fileSource.exists() && !fileDest.exists()) {
			Elevator.executeAsAdministrator(path, addQuotes(source) + " " + addQuotes(dest) + " " + isDir);
		}
	}
	
	private static String addQuotes(String s) {
		return "\"" + s + "\"";
	}
	
	public static void updateReg() {
		Elevator.executeAsAdministrator(path, "regUpdate");
	}
	
	public static void removeReg() {
		Elevator.executeAsAdministrator(path, "regRemove");
	}
	
	public static boolean checkReg() {
		return RegSetup.checkReg();
	}
}
