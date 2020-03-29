package de.hanfland.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import de.hanfland.main.School;

public class ClassButtonSubmit extends JButton implements ActionListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField datafield;
	
	public ClassButtonSubmit(JTextField field) {
		super();
		this.datafield = field;
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(this.datafield.getText() != null && this.datafield.getText().length() >= 3) {
			String classname = this.datafield.getText();
			
			if(School.classquery.exist(classname)) {
				this.datafield.setBorder(new LineBorder(Color.red, 1));
				JOptionPane.showMessageDialog(datafield,"Die Klasse gibt es schon!", "Klasseneditor",JOptionPane.ERROR_MESSAGE);
			} else {
				School.classquery.create(classname);
				this.datafield.setBorder(new LineBorder(Color.green, 1));
				this.datafield.setText("");
				JOptionPane.showMessageDialog(datafield,"Die Klasse wurde angelegt!", "Klasseneditor", JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			this.datafield.setBorder(new LineBorder(Color.RED, 1));
			JOptionPane.showMessageDialog(datafield,"Ungültiges Feld!", "Klasseneditor",JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
