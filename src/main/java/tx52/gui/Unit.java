package tx52.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.arakhne.afc.math.continous.object2d.Point2f;
import org.arakhne.afc.math.continous.object2d.Rectangle2f;

import tx52.environment.AgentBody;

public class Unit extends JPanel{

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
		id = a.getAgentId();
		
	}
	
	public void paintComponent(Graphics g)
	{
		try
		{

			Image img = ImageIO.read(new File("src\\main\\resources\\images\\unit.png"));
			g.drawImage(img, 0, 0, this);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void move()
	{
		this.setBounds(position.x()*5,position.y()*5, 48,48);
	}
	
}