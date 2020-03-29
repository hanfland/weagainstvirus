package de.hanfland.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import de.hanfland.main.School;

public class ClassComboBox extends JComboBox<Object> implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int classId;
	
	public ClassComboBox(String[] entries) {
		super(entries);
		this.setSelectedIndex(0);
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        String name = (String)this.getSelectedItem();
        
        
        /*
         * GET ID IN DATABASE AND SET
         * 
         */
        
        this.classId = School.classquery.getId(name);
        System.out.println("Selected Class ID: " + this.classId);
	}
	
	public int getSelectedClassId() {
		return this.classId;
	}

}
