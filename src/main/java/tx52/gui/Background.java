package tx52.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

//Panel of the main frame where we put the background picture
public class Background extends JPanel
{
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g)
	{
		try
		{
			// Resources dans AFC vmutils
			URL url = getClass().getClassLoader().getResource("images/Terrain.png");
			assert (url != null);
			Image img = ImageIO.read(url);
			g.drawImage(img, 0, 0, this);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}