package tx52.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.arakhne.afc.math.continous.object2d.Point2f;
import org.arakhne.afc.math.continous.object2d.Rectangle2f;

import tx52.environment.AgentBody;

public class Unit {

	protected UUID id;
	protected int team;
	protected Point2f position;
	
	public Unit(AgentBody a){
		position = a.getPosition();
		team = a.getTeam();
		id = a.getId();
		
	}
	
	public void setPosition(Point2f position) {
		this.position = position;
	}

	public void paint(Graphics g, JPanel container)
	{
		Graphics2D g2d = (Graphics2D) g;
		try
		{
			// Resources dans AFC vmutils
			URL url;
			if (this.team == 1){
				url = getClass().getClassLoader().getResource("images/unit.png");
			} else {
				url = getClass().getClassLoader().getResource("images/unit2.png");
			}
	    	assert (url != null);
			Image img = ImageIO.read(url);
			float posX = position.getX() * 5;
			float posY = position.getY() * 5;
			g2d.drawImage(img, (int) posX, (int) posY, container);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
}