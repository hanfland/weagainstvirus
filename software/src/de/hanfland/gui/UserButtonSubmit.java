package de.hanfland.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import de.hanfland.main.School;

public class UserButtonSubmit extends JButton implements ActionListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField username_field;
	private JTextField password_field;
	private ClassComboBox class_field;
	
	public UserButtonSubmit(JTextField name, JTextField password, ClassComboBox combo) {
		super();
		this.username_field = name;
		this.password_field = password;
		this.class_field = combo;
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(this.username_field.getText().length() >= 3 && this.password_field.getText().length() >= 3) {
			if(this.class_field.getSelectedClassId() != -1) {
				String username = this.username_field.getText();
				String password = this.password_field.getText();
				if(School.classquery.exist(this.class_field.getSelectedClassId())) {
					if(School.userquery.exist(username)) {
						this.username_field.setBorder(new LineBorder(Color.RED, 1));
						JOptionPane.showMessageDialog(password_field,"Den Benutzer gibt es bereits!", "Benutzerkonten",JOptionPane.ERROR_MESSAGE);
					} else {
						School.userquery.create(username, password, this.class_field.getSelectedClassId());
						this.username_field.setText("");
						this.password_field.setText("");
					}
				} else {
					this.class_field.setBorder(new LineBorder(Color.RED, 1));
				}
			}
		} else {
			this.username_field.setBorder(new LineBorder(Color.RED, 1));
			this.password_field.setBorder(new LineBorder(Color.RED, 1));
			JOptionPane.showMessageDialog(password_field,"Ungültige Felder! Benutze min. 3 Zeichen", "Benutzerkonten",JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
