package Clases;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
	private TextPanel textPanel;
	private JButton btn;
	private Toolbar toolbar;
	public MainFrame() {
		super("Hello World");
		
		setLayout(new BorderLayout());
		
		toolbar = new Toolbar();
		textPanel = new TextPanel();
		btn = new JButton("Click ME !");
		
		toolbar.setTextPanel(textPanel);
		
			
		
		add(toolbar, BorderLayout.NORTH);
		add(textPanel, BorderLayout.CENTER);
		
		setSize(600,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
