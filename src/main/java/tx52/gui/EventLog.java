package tx52.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import tx52.environment.Environment;

public class EventLog extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	JScrollPane scrollPane;
	JTextArea text;
	boolean vide = true;
	
	
	//Constructor of the EventLog, where we instantiate every component 
		public EventLog (Environment environment) {//World TBD
			super();
			text = new JTextArea();
			text.setEditable(false);
			
			scrollPane = new JScrollPane(text);
			scrollPane.setPreferredSize(new Dimension(800,85));
			this.add(scrollPane);
			
			this.setLayout(new GridLayout(1,1));
			this.setBackground(Color.getHSBColor(60, 50, 50));
			this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

			this.setPreferredSize(new Dimension(800,115));
		}
		
		public void updateLog (String lastLog) {
			if(vide)
			{
				text.append(lastLog);
				vide=false;
			}
			else
			{
				text.append("\n"+lastLog);
			}

			scrollPane.setAutoscrolls(true);
			text.setCaretPosition(text.getDocument().getLength()); 

		}
}
