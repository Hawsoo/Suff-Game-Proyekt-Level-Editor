package me.hawsoo.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import me.hawsoo.LevelEditor;
import me.hawsoo.lib.EditorTools;

/**
 * Updates and manages the main
 * view of the level editor.
 * @author Hawsoo
 *
 */
public class EditorView extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	private static final int FPS = 30;
	
	public int width, height;
	public int gridWidth = 32, gridHeight = 32;

	/**
	 * Creates the panel.
	 */
	public EditorView(int width, int height)
	{
		this.width = width;
		this.height = height;
		
		// Add clicking utility
		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseReleased(MouseEvent e)
			{
				// Check which type of event
				String name = ((EditorTools)LevelEditor.cmbEditorTools.getSelectedItem()).getName();
				switch (name)
				{
				case "Select":
					break;
					
				case "Create":
					break;
					
				case "Delete":
					// Delete object
					break;
				}
			}
		});
	}
	
	@Override
	public void run()
	{
		// Update drawing in a loop
		while (true)
		{
			// Update drawing per 30 fps
			update();
			repaint();
			
			try
			{
				// Sleep
				Thread.sleep(1000l / FPS);
			} catch (InterruptedException e) {}
		}
	}
	
	/**
	 * Updates the panel.
	 */
	public void update()
	{
		setPreferredSize(new Dimension(width, height));
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		// Draw background
		g.setColor(new Color(0, 128, 192));
		g.fillRect(0, 0, width, height);
		
		// Draw grid
		if (LevelEditor.chckbxmntmShowGrid.isSelected())
		{
			g.setColor(new Color(195, 195, 195));
			for (int x = gridWidth; x < width; x += gridWidth)
			{
				g.drawLine(x, 0, x, height);
			}
			
			for (int y = gridHeight; y < height; y += gridHeight)
			{
				g.drawLine(0, y, width, y);
			}
		}
		
		// Draw bounds
		g.setColor(new Color(255, 255, 0));
		g.drawRect(0, 0, width - 1, height - 1);
	}
}
