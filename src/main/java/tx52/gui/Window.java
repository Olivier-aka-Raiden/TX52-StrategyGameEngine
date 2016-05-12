package tx52.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;

import tx52.environment.Environment;

public class Window extends JFrame implements  Runnable {

	private static final long serialVersionUID = 1L;

	Thread tempo; //pour la synchrinisation de l'affichage

	Hub displayer; // pour mettre des boutons etc.
	EventLog log; /*voir si on met une console 
					pour comprendre ce qu'il se passe dans l'affichage directement*/
	Background bg;//background de la map
	public Window(Environment environment){//World TBD
		// Creation of the JPanel and his
		
		// background image
		bg = new Background();
		bg.setSize(new Dimension(1033,867));
		//JLayeredPane : permet de représenter nos objets sur différentes couches
		JLayeredPane jlp = new JLayeredPane();
		jlp.setOpaque(false);
		jlp.setPreferredSize(new Dimension(bg.getWidth(),bg.getHeight()));
		

		// Create the HUB
		displayer = new Hub(environment);
		
		//Create the log area
		log = new EventLog (environment);

		
		
		
		// Create objects image
		/*for (objectList obj : EnvMap)
		{
			obj.setBounds(obj.getCoordX(), obj.getCoordY(), width, height);
			obj.setVisible(true);
		}*/

		// Add the images to the JLayeredPane with a different deep level
		jlp.add(bg, new Integer(1));//plus integer est grand grand, plus c'est avancé
		
		//JscrollPane
		JScrollPane scrollPane = new JScrollPane(jlp);
		scrollPane.setSize(new Dimension(800,600));
		this.setTitle("RTS Game Engine");
		this.setSize(new Dimension(scrollPane.getWidth()+displayer.getWidth(),scrollPane.getHeight()+log.getHeight()));
		this.setResizable(false);
		this.setLocationRelativeTo(null); // JFrame in the center of the window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add the JLayeredPane to the frame
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(displayer, BorderLayout.EAST);
		this.add(log, BorderLayout.SOUTH);
		this.setVisible(true);

	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//Boucle infine du thread affichage
		while(true)
		{
			displayer.updateLabels();
			this.sleep(99999);
			
		}
	}
	
	private void sleep(int second)
	{
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
}
