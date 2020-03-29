package de.hanfland.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import de.hanfland.main.School;

public class ThemeButtonSubmit extends JButton implements ActionListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField themename_field;
	private ClassComboBox class_field;
	
	public ThemeButtonSubmit(JTextField name, ClassComboBox combo) {
		super();
		this.themename_field = name;
		this.class_field = combo;
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(this.themename_field.getText().length() >= 3) {
			if(this.class_field.getSelectedClassId() != -1) {
				String name = this.themename_field.getText();
				if(School.classquery.exist(this.class_field.getSelectedClassId())) {
					if(School.themequery.exist(name)) {
						this.themename_field.setBorder(new LineBorder(Color.RED, 1));
						JOptionPane.showMessageDialog(themename_field,"Das Thema ist bereits erstellt.", "Themeneditor",JOptionPane.ERROR_MESSAGE);
					} else {
						School.themequery.create(name, this.class_field.getSelectedClassId());
						this.themename_field.setText("");
					}
				} else {
					this.class_field.setBorder(new LineBorder(Color.RED, 1));
				}
			}
		} else {
			this.themename_field.setBorder(new LineBorder(Color.RED, 1));
			JOptionPane.showMessageDialog(themename_field,"Ungültiges Feld! Benutze min. 3 Zeichen", "Themeneditor",JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
