package gui;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

import net.iharder.dnd.FileDrop;

public class CTextField extends JTextField {
	private static final long serialVersionUID = 1L;
	public CTextField(String text) {
		super(text);
		initialize(text);
		setForeground(Color.GRAY);
	}
	public CTextField() {
		super();
		initialize("");
	}
	private void initialize(final String text) {
		addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(getText().equals(text)) {
					setText("");
					setForeground(Color.BLACK);
				}
				else {
					selectAll();
				}
			}
			public void focusLost(FocusEvent e) {
				if(getText().equals("")) {
					setText(text);
					setForeground(Color.GRAY);
				}
			}
		});
		
		new  FileDrop( this, new FileDrop.Listener() {
			public void  filesDropped( java.io.File[] file ) {
				if(file.length == 1) {
					setText(file[0].getAbsolutePath());
					requestFocus();
					selectAll();
				}
			}
		});
	}
	public void setText(String text) {
		super.setText(text);
		setForeground(Color.BLACK);
	}
}
