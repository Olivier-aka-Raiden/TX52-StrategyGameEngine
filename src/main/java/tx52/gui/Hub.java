package tx52.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tx52.environment.Environment;

//The hub is a JPanel in the right of the frame where we put all informations related to the world
public class Hub extends JPanel{
	private static final long serialVersionUID = 1L;
	JLabel nbUnit;//example to know the number of unit on the map
	JPanel container;
	public Hub (Environment environment) {//World TBD
		super();
		container = new JPanel();
		nbUnit = new JLabel ("pop : 0/200");
		
		nbUnit.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		container.setBackground(Color.LIGHT_GRAY);
		container.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
		container.setLayout(new FlowLayout());
		this.add(nbUnit);
		this.add(container);
		this.setLayout(new GridLayout(6,1));
		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		this.setSize(new Dimension(150,600));
		this.setVisible(true);
	}
	
	//This method permit to update the informations displayed in the hub
	public void updateLabels() {
		nbUnit.setText("pop. : "+this.nbUnitDisplayed()+"/200");
	}

	private int nbUnitDisplayed() {
		// TODO Auto-generated method stub
		int nb=0;
		//TODO : check the number of objects(dynamic) in the world
		//		OR check the number of unit displayed on the GUI
		return nb;
	}
}
