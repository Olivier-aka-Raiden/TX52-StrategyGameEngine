package tx52.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tx52.environment.AgentBody;
import tx52.environment.Environment;
import tx52.environment.EnvironmentEvent;

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

		// Create the HUB
		displayer = new Hub(environment);

		//Create the log area
		log = new EventLog (environment);

		// sizes
		Dimension bgSize = new Dimension(1033,867);
		Dimension viewportSize = new Dimension(800,600);
		
		// background image
		bg = new Background();
		bg.setSize(bgSize);
		bg.setOpaque(false);
		bg.setPreferredSize(bgSize);
		
		UnitPanel unitPanel = new UnitPanel();
		unitPanel.setSize(bgSize);
		unitPanel.setOpaque(false);
		unitPanel.setPreferredSize(bgSize);

		//JLayeredPane : permet de représenter nos objets sur différentes couches
		jlp = new JLayeredPane();
		jlp.setPreferredSize(bgSize);
		jlp.setOpaque(false);
		// Add the images to the JLayeredPane with a different deep level
		jlp.add(bg, new Integer(1));//plus integer est grand grand, plus c'est avancé
		jlp.add(unitPanel, new Integer(2));
		
		//JscrollPane
		JScrollPane scrollPane = new JScrollPane(jlp);
		scrollPane.setPreferredSize(viewportSize);
		this.setTitle("RTS Game Engine");
		this.setResizable(true);
		//this.setLocation
		//this.setLocationRelativeTo(null); // JFrame in the center of the window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add the JLayeredPane to the frame
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(displayer, BorderLayout.EAST);
		this.add(log, BorderLayout.SOUTH);
		for (AgentBody body : env.getWorld().getAgentBodies())
		{
			if (body != null)
				units.put(body.getId(),new Unit(body));
		}

		pack();
		this.setVisible(true);
	}
	
	public void updateWindow(){
		synchronized(getTreeLock()) {
			units = new TreeMap<>();
			for (AgentBody body : env.getWorld().getAgentBodies())
			{
				assert (body != null);
				units.put(body.getId(),new Unit(body));
			}
			displayer.updateLabels();
			for (EnvironmentEvent events : env.getEventFire()){
				this.log.updateLog("New spawn event.");
			}
		}
		repaint();
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

	private class UnitPanel extends JPanel {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void paint(Graphics g) {
			for (Unit unit : Window.this.units.values()) {
				unit.paint(g, this);
			}
		}
		
	}
}
