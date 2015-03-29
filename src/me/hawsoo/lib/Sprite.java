package me.hawsoo.lib;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Holds an image for use
 * as an icon.
 * @author Hawsoo
 *
 */
public class Sprite
{
	private BufferedImage image;
	private int xoff, yoff;
	
	public Sprite(String filename, int xoff, int yoff)
	{
		this.xoff = xoff;
		this.yoff = yoff;
		
		try
		{
			// Load an image
			ImageIO.read(getClass().getResource("/me/hawsoo/res/" + filename));
		} catch (IOException e) {}
	}
	
	/**
	 * Renders the sprite.
	 * @param g - The graphics context
	 */
	public void render(int x, int y, Graphics g)
	{
		g.drawImage(image, x - xoff, y - yoff, null);
	}
}
