package gui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.iharder.dnd.FileDrop;
import core.Main;
import core.RenameFile;

import javax.swing.JMenuItem;
//import java.awt.Toolkit;

public class MainGUI {

	public JFrame frame;
	private CTextField textField;
	private CTextField textField1;
	private JFileChooser fileChooser;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private Component verticalStrut;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JCheckBoxMenuItem enableCM;
	private JMenuItem updateReg;

	public MainGUI() {
		initialize();
		fileChooser = new JFileChooser();
	}
	
	private void initialize() {
		frame = new JFrame();
		try {
			frame.setIconImage(ImageIO.read(MainGUI.class.getResourceAsStream("NewFolder.gif")));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		//frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/NewFolder.gif")));
		//frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainGUI.class.getResource("/javax/swing/plaf/metal/icons/ocean/newFolder.gif")));
		frame.setTitle("Virtual Folder Creator");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{38, 0, 261, 39, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(frame);
		
		JButton browse1 = new JButton("Browse");
		browse1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fileChooser.setSelectedFile(new File(textField.getText()));
				int a = fileChooser.showOpenDialog(frame);
				if(a == JFileChooser.APPROVE_OPTION) {
					String path = fileChooser.getSelectedFile().getAbsolutePath();
					textField.setText(path);
					textField1.setText(RenameFile.rename(path, "_vf"));
				}
			}
		});
		
		verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.fill = GridBagConstraints.HORIZONTAL;
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 1;
		gbc_verticalStrut.gridy = 0;
		frame.getContentPane().add(verticalStrut, gbc_verticalStrut);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
		gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_horizontalStrut.gridx = 0;
		gbc_horizontalStrut.gridy = 1;
		frame.getContentPane().add(horizontalStrut, gbc_horizontalStrut);
		GridBagConstraints gbc_browse1 = new GridBagConstraints();
		gbc_browse1.fill = GridBagConstraints.HORIZONTAL;
		gbc_browse1.insets = new Insets(0, 0, 5, 5);
		gbc_browse1.gridx = 1;
		gbc_browse1.gridy = 1;
		frame.getContentPane().add(browse1, gbc_browse1);
		
		textField = new CTextField("Source");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		frame.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		new  FileDrop( textField, new FileDrop.Listener() {
			public void  filesDropped( java.io.File[] file ) {
				if(file.length == 1) {
					String path = file[0].getAbsolutePath();
					textField.setText(path);
					textField1.setText(RenameFile.rename(path, "_vf"));
					textField.requestFocus();
					textField.selectAll();
				}
			}
		});
		
		JButton browse2 = new JButton("Browse");
		browse2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fileChooser.setSelectedFile(new File(textField1.getText()));
				int a = fileChooser.showSaveDialog(frame);
				if(a == JFileChooser.APPROVE_OPTION) {
					textField1.setText(fileChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
		gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalStrut_1.gridx = 3;
		gbc_horizontalStrut_1.gridy = 1;
		frame.getContentPane().add(horizontalStrut_1, gbc_horizontalStrut_1);
		GridBagConstraints gbc_browse2 = new GridBagConstraints();
		gbc_browse2.fill = GridBagConstraints.HORIZONTAL;
		gbc_browse2.insets = new Insets(0, 0, 5, 5);
		gbc_browse2.gridx = 1;
		gbc_browse2.gridy = 2;
		frame.getContentPane().add(browse2, gbc_browse2);
		
		textField1 = new CTextField("Virtual Folder");
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 2;
		frame.getContentPane().add(textField1, gbc_textField_1);
		textField1.setColumns(10);
		
		JButton createButton = new JButton("Create");
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.createFolder(textField.getText(), textField1.getText());
			}
		});
		GridBagConstraints gbc_createButton = new GridBagConstraints();
		gbc_createButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_createButton.insets = new Insets(0, 0, 0, 5);
		gbc_createButton.gridx = 1;
		gbc_createButton.gridy = 3;
		frame.getContentPane().add(createButton, gbc_createButton);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		enableCM = new JCheckBoxMenuItem("Enable Context Menu");
		enableCM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(enableCM.getState()) {
					try {
						Main.updateReg();
					} catch(java.lang.RuntimeException e1) {
						enableCM.setState(false);
					}
				} else {
					try {
						Main.removeReg();
					} catch(java.lang.RuntimeException e1) {
						enableCM.setState(true);
					}
				}
			}
		});
		if(Main.checkReg()) {
			enableCM.setState(true);
		} else {
			enableCM.setState(false);
		}
		mnNewMenu.add(enableCM);
		
		updateReg = new JMenuItem("Update Registries");
		updateReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(enableCM.getState()) {
					Main.updateReg();
				}
			}
		});
		mnNewMenu.add(updateReg);
	}

}
