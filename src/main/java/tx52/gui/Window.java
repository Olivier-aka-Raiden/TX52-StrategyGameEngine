package tx52.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;

import tx52.environment.AgentBody;
import tx52.environment.Environment;

public class Window extends JFrame implements  Runnable {

	private static final long serialVersionUID = 1L;

	Environment env;

	Thread tempo; //pour la synchrinisation de l'affichage

	Hub displayer; // pour mettre des boutons etc.
	EventLog log; /*voir si on met une console 
					pour comprendre ce qu'il se passe dans l'affichage directement*/
	Background bg;//background de la map
	JLayeredPane jlp;
	private Map<UUID,Unit> units = new TreeMap<>();
	public Window(Environment environment){//World TBD

		env = environment;
		// Creation of the JPanel and his

		// background image
		bg = new Background();
		bg.setSize(new Dimension(1033,867));
		//JLayeredPane : permet de représenter nos objets sur différentes couches
		jlp = new JLayeredPane();
		jlp.setOpaque(false);
		jlp.setPreferredSize(new Dimension(bg.getWidth(),bg.getHeight()));


		// Create the HUB
		displayer = new Hub(environment);

		//Create the log area
		log = new EventLog (environment);

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
		for (AgentBody body : env.getWorld().getAgentBodies())
		{
			if (body != null)
				units.put(body.getAgentId(),new Unit(body));
		}
		for (Unit u : Collections.unmodifiableMap(units).values())
		{
			u.move();
			u.setVisible(true);
			jlp.add(u, new Integer(2));
		}

	}
	
	public void updateWindow(){
		for (AgentBody body : env.getWorld().getAgentBodies())
		{
			if (body != null)
				units.put(body.getAgentId(),new Unit(body));
		}
		for (Unit u : Collections.unmodifiableMap(units).values())
		{
			u.move();
			u.setVisible(true);
			jlp.add(u, new Integer(2));
		}
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
