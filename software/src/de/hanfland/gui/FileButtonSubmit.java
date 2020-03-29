package de.hanfland.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import de.hanfland.main.School;

public class FileButtonSubmit extends JButton implements ActionListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField filepath_field;
	private JTextArea description_field;
	private FileComboBox theme_field;
	
	public FileButtonSubmit(JTextField filepath_field, JTextArea description_field, FileComboBox theme_field) {
		super();
		this.filepath_field = filepath_field;
		this.description_field = description_field;
		this.theme_field = theme_field;
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(this.filepath_field.getText().length() != 0) {
			if(this.theme_field.getSelectedFileId() != -1) {
				String description = this.description_field.getText();
				String filepath = this.filepath_field.getText();
				if(School.themequery.exist(this.theme_field.getSelectedFileId())) {
					
					File f = new File(filepath);
					if(f != null) {
						File folder = new File(School.WEBSITE_PATH + theme_field.getSelectedFileId());
						String fdest = School.WEBSITE_PATH + "download\\" + theme_field.getSelectedFileId() + "\\" + f.getName();
						File dest = new File(fdest);
						if(dest.exists()) {
							this.filepath_field.setBorder(new LineBorder(Color.red, 1));
							JOptionPane.showMessageDialog(theme_field,"Den Dateinamen gibt es bereits!", "Dateimanager",JOptionPane.ERROR_MESSAGE);
						}
						if(!folder.exists()) {
							folder.mkdir();
						}
						try {
							System.out.println(filepath + " > " + fdest);
							Files.copy(Paths.get(filepath), Paths.get(fdest));
							School.filequery.create(fdest, f.getName(), description, this.theme_field.getSelectedFileId());
							this.filepath_field.setText("");
							this.description_field.setText("");
							JOptionPane.showMessageDialog(description_field,"Die Datei ist nun hochgeladen.", "Dateimanager", JOptionPane.INFORMATION_MESSAGE);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					
				} else {
					this.theme_field.setBorder(new LineBorder(Color.red, 1));
					JOptionPane.showMessageDialog(theme_field,"Das Thema gibt es nicht!", "Dateimanager",JOptionPane.ERROR_MESSAGE);
				}
			} else {
				this.theme_field.setBorder(new LineBorder(Color.red, 1));
				JOptionPane.showMessageDialog(theme_field,"Wähle ein Thema aus", "Dateimanager",JOptionPane.ERROR_MESSAGE);
			}
		} else {
			this.filepath_field.setBorder(new LineBorder(Color.RED, 1));
			JOptionPane.showMessageDialog(filepath_field,"Ungültige Felder! Gebe einen Pfad zur Datei an!", "Dateimanager",JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
