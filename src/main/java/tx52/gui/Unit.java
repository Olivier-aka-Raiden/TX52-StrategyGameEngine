package tx52.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.arakhne.afc.math.continous.object2d.Point2f;
import org.arakhne.afc.math.continous.object2d.Rectangle2f;

import tx52.environment.AgentBody;

public class Unit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Rectangle2f box;
	protected UUID id;
	protected Point2f position;
	
	public Unit(AgentBody a){
		position = a.getPosition();
		box = a.getBox();
		id = a.getId();
		
	}
	
	public void paint(Graphics g, JPanel container)
	{
		Graphics2D g2d = (Graphics2D) g;
		try
		{
			// Resources dans AFC vmutils
			URL url = getClass().getClassLoader().getResource("images/unit.png");
	    	assert (url != null);
			Image img = ImageIO.read(url);
			g2d.drawImage(img, (int) position.getX() * 5, (int) position.getY() * 5, container);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
}