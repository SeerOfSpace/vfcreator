package core;

import gui.MainGUI;

import java.awt.EventQueue;
import java.io.File;

public class Main {
	
	public static String path = new File("VFCreator.exe").getAbsolutePath();
	
	public static void main(String[] args) {
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
		} else {
			createFolder(args[0], args[1]);
		}
	}
	
	public static void createFolder(String path1, String path2) {
		if(new File(path1).isFile() && !new File(path2).exists()) {
			Elevator.executeAsAdministrator("cmd.exe", "/c mklink "+"\""+path2+"\" \""+path1+"\"");
		} else if(new File(path1).isDirectory() && !new File(path2).exists()) {
			Elevator.executeAsAdministrator("cmd.exe", "/c mklink /D "+"\""+path2+"\" \""+path1+"\"");
		}
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
