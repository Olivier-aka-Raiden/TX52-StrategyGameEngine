package tx52.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

//Panel of the main frame where we put the background picture
public class Background extends JPanel
{
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g)
	{
		try
		{
			Image img = ImageIO.read(new File("src\\main\\resources\\images\\Terrain.png"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}