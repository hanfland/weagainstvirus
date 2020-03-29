package de.hanfland.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import de.hanfland.gui.*;
import de.hanfland.sql.ClassQuery;
import de.hanfland.sql.FileQuery;
import de.hanfland.sql.MySQL;
import de.hanfland.sql.ThemeQuery;
import de.hanfland.sql.UserQuery;

public class School {
	
	public static String WEBSITE_PATH = "X:\\xampp\\htdocs\\";

	private static MySQL mysql;
	public static ClassQuery classquery = null;
	public static UserQuery userquery = null;
	public static ThemeQuery themequery = null;
	public static FileQuery filequery = null;
	
	public static void main(String[] args) { 
		mysql = new MySQL("localhost", "3306", "example", "root", "");
		mysql.open();
		classquery = new ClassQuery(mysql);
		classquery.intializeTable();
		userquery = new UserQuery(mysql);
		userquery.intializeTable();
		themequery = new ThemeQuery(mysql);
		themequery.intializeTable();
		filequery = new FileQuery(mysql);
		filequery.intializeTable();
		
		//openClassEditor();
		//openUserEditor();
		//openTreeEditor();
		//openThemeEditor();
		//openFileEditor();
		openMenu();
    }
	
	public static void openMenu() {
		JFrame jframe = new JFrame("Digitale-Schule Menü");
		jframe.setSize(225, 300);
		jframe.setResizable(false);
		
		JMenuBar menubar = new JMenuBar();
		JPanel panel = new JPanel();
		
		JMenu classes = new JMenu("Class");
		JMenuItem classes_item = new JMenuItem("Add");
		classes_item.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	panel.removeAll();
		    	jframe.remove(panel);
		    	
		    	classPanel(panel);
		    	jframe.add(panel);

		    	jframe.repaint(); 
		    	panel.updateUI();
		    }
		});
		classes.add(classes_item);
		menubar.add(classes);
		
		JMenu theme = new JMenu("Theme");
		JMenuItem theme_item = new JMenuItem("Add");
		theme_item.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	panel.removeAll();
		    	jframe.remove(panel);
		    	
		    	themePanel(panel);
		    	jframe.add(panel);

		    	jframe.repaint(); 
		    	panel.updateUI();
		    }
		});
		theme.add(theme_item);
		menubar.add(theme);
		
		
		JMenu file = new JMenu("File");
		JMenuItem file_item = new JMenuItem("Upload");
		file_item.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	panel.removeAll();
		    	jframe.remove(panel);
		    	filePanel(panel);
		    	jframe.add(panel);
		    	jframe.repaint(); 
		    	panel.updateUI();

		    }
		});
		file.add(file_item);
		menubar.add(file);
		
		JMenu user = new JMenu("User");
		JMenuItem user_item = new JMenuItem("Register");
		user_item.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent ev) {
		    	panel.removeAll();
		    	jframe.remove(panel);
		    	
		    	userPanel(panel);
		    	jframe.add(panel);
		    	jframe.repaint(); 
		    	panel.updateUI();
		    }
		});
		user.add(user_item);
		menubar.add(user);
		jframe.add(panel);
		jframe.setJMenuBar(menubar);
		
		jframe.setVisible(true);
	}
	
	public static void filePanel(JPanel panel) {
		
		String[] classes = themequery.getThemes();
		
		if(classes.length == 0) {
			classes = new String[] {"..."};
		}
		FileComboBox box = new FileComboBox(classes);
		box.addItem("...");
		box.setSelectedIndex(box.getItemCount()-1);
		box.setLocation(10, 115);
		box.setSize(200, 25);
		panel.add(box);
		
		
		
		JTextField username = new JTextField();
		username.setSize(200,25);
		username.setLocation(10, 5);
		
		panel.add(username);
		
		JTextArea description = new JTextArea();
		description.setSize(200,75);
		description.setLocation(10, 35);
		description.setText("Beschreibung");
		panel.add(description);
		

		FileButtonSubmit submit = new FileButtonSubmit(username, description, box);
		submit.setText("Datei hochladen");
		submit.setLocation(10, 145);
		submit.setVisible(true);
		submit.setSize(200,25);
		panel.add(submit);
		
		panel.setLayout(null);
		panel.setVisible(true);
		

		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int option = chooser.showOpenDialog(panel); // parentComponent must a component like JFrame, JDialog...
		if (option == JFileChooser.APPROVE_OPTION) {
		   File selectedFile = chooser.getSelectedFile();
		   String path = selectedFile.getAbsolutePath();
		   username.setText(path);
		}
		
	}
	
	
	public static void classPanel(JPanel jframe) {
		//jframe.setSize(225, 100);
		
		JTextField name = new JTextField();
		name.setSize(200,25);
		name.setLocation(10, 5);
		name.setText("Klasse Xy");
		jframe.add(name);

		ClassButtonSubmit submit = new ClassButtonSubmit(name);
		submit.setText("Klasse anlegen");
		submit.setLocation(10, 35);
		submit.setVisible(true);
		submit.setSize(200,25);
		jframe.add(submit);
		
		jframe.setLayout(null);
		jframe.setVisible(true);
		
	}
	
	public static void userPanel(JPanel jframe) {
		
		String[] classes = classquery.getClassNames();
		
		if(classes.length == 0) {
			classes = new String[] {"..."};
		}
		ClassComboBox box = new ClassComboBox(classes);
		box.addItem("...");
		box.setSelectedIndex(box.getItemCount()-1);
		box.setLocation(10, 65);
		box.setSize(200, 25);
		jframe.add(box);
		
		
		JTextField username = new JTextField();
		username.setSize(200,25);
		username.setLocation(10, 5);
		username.setText("Benutzername");
		jframe.add(username);
		
		JTextField password = new JTextField();
		password.setSize(200,25);
		password.setLocation(10, 35);
		password.setText("Passwort");
		jframe.add(password);
		

		UserButtonSubmit submit = new UserButtonSubmit(username,password,box);
		submit.setText("Benutzer anlegen");
		submit.setLocation(10, 95);
		submit.setVisible(true);
		submit.setSize(200,25);
		jframe.add(submit);
		
		jframe.setLayout(null);
		jframe.setVisible(true);
	}
	
	public static void openTreeEditor() {
		JFrame jframe = new JFrame("Thema anlegen");
		jframe.setSize(300, 500);
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("School");
		
		for(String classx : classquery.getClassNames()) {
			DefaultMutableTreeNode nodeclass = new DefaultMutableTreeNode(classx);
			
			
			for(String theme : themequery.getThemesOf(classx)) {
				DefaultMutableTreeNode themenode = new DefaultMutableTreeNode(theme);
				nodeclass.add(themenode);
				
			}
			
			
			top.add(nodeclass);
		}
		DefaultTreeModel treeModel = new DefaultTreeModel(top);
		treeModel.addTreeModelListener(new MyTreeModelListener());
		JTree jtree = new JTree(treeModel);
		jtree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		jframe.add(jtree);
		jframe.setVisible(true);
	}
	
	public static void themePanel(JPanel jframe) {
		
		String[] classes = classquery.getClassNames();
		
		if(classes.length == 0) {
			classes = new String[] {"..."};
		}
		ClassComboBox box = new ClassComboBox(classes);
		box.addItem("...");
		box.setSelectedIndex(box.getItemCount()-1);
		box.setLocation(10, 35);
		box.setSize(200, 25);
		jframe.add(box);
		
		
		JTextField username = new JTextField();
		username.setSize(200,25);
		username.setLocation(10, 5);
		username.setText("Themenname");
		jframe.add(username);
		

		ThemeButtonSubmit submit = new ThemeButtonSubmit(username,box);
		submit.setText("Thema anlegen");
		submit.setLocation(10, 65);
		submit.setVisible(true);
		submit.setSize(200,25);
		jframe.add(submit);
		
		jframe.setLayout(null);
		jframe.setVisible(true);
	}
	
}
