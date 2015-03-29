package me.hawsoo;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.EnumSet;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import me.hawsoo.lib.EditorItems;
import me.hawsoo.lib.EditorTools;
import me.hawsoo.ui.EditorView;

/**
 * Sets up and runs the application.
 * @author Hawsoo
 *
 */
public class LevelEditor
{
	public static JFrame frame;
	public static JCheckBoxMenuItem chckbxmntmShowBoundingBoxes;
	public static JCheckBoxMenuItem chckbxmntmShowGrid;
	public static JComboBox<EditorTools> cmbEditorTools;
	public static JList<EditorItems> objectList;
	
	/**
	 * Sets up the application.
	 * @param argv
	 */
	public static void main(String[] argv)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					UIManager.setLookAndFeel(
							UIManager.getSystemLookAndFeelClassName());
					
					initialize();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {}
			}
		});
	}
	
	/**
	 * Creates the application.
	 */
	private static void initialize()
	{
		// Setup the frame
		frame = new JFrame("Level Editor - by Hawsoo");
		frame.setSize(1024, 576);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		frame.addWindowListener(
				new WindowAdapter()
				{
					@Override
					public void windowClosing(WindowEvent e)
					{
						// Exit application
						exit();
					}
				}
				);
		
		// Creates menu
		JMenuBar menuBar = new JMenuBar();
		springLayout.putConstraint(SpringLayout.NORTH, menuBar, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, menuBar, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, menuBar, 0, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(menuBar);
		
		// "File" section
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		{
			JMenuItem mntmNew = new JMenuItem("New");
			mnFile.add(mntmNew);
			
			mnFile.addSeparator();
			
			JMenuItem mntmOpen = new JMenuItem("Open");
			mnFile.add(mntmOpen);
			
			JMenuItem mntmSave = new JMenuItem("Save");
			mnFile.add(mntmSave);
			
			JMenuItem mntmSaveAs = new JMenuItem("Save As...");
			mnFile.add(mntmSaveAs);
			
			mnFile.addSeparator();
			
			JMenuItem mntmExit = new JMenuItem("Exit");
			{
				mntmExit.addActionListener(
						new ActionListener()
						{
							@Override
							public void actionPerformed(ActionEvent e)
							{
								// Exit Application
								exit();
							}
						}
						);
				mnFile.add(mntmExit);
			}
		}
		
		// "Settings" section
		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);
		{
			JMenuItem mntmRoomProperties = new JMenuItem("Room Properties...");
			mnSettings.add(mntmRoomProperties);
			
			chckbxmntmShowBoundingBoxes = new JCheckBoxMenuItem("Show Bounding Boxes");
			mnSettings.add(chckbxmntmShowBoundingBoxes);
			
			chckbxmntmShowGrid = new JCheckBoxMenuItem("Show Grid");
			mnSettings.add(chckbxmntmShowGrid);
		}
		
		// Create level editor pane
		EditorView editorPane = new EditorView(1024, 576);
		new Thread(editorPane).start();
		
		// Editor View for level
		JScrollPane editorView = new JScrollPane(editorPane);
		{
			editorView.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			editorView.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			springLayout.putConstraint(SpringLayout.WEST, editorView, 10, SpringLayout.WEST, menuBar);
			springLayout.putConstraint(SpringLayout.SOUTH, editorView, -10, SpringLayout.SOUTH, frame.getContentPane());
			frame.getContentPane().add(editorView);
		}
		
		// Editor Tools box
		cmbEditorTools = new JComboBox<EditorTools>();
		{
			// Add all tools from enum
			for (EditorTools tool : EnumSet.allOf(EditorTools.class))
				cmbEditorTools.addItem(tool);
			
			cmbEditorTools.setFocusable(false);
			springLayout.putConstraint(SpringLayout.NORTH, cmbEditorTools, 6, SpringLayout.SOUTH, menuBar);
			springLayout.putConstraint(SpringLayout.NORTH, editorView, 0, SpringLayout.NORTH, cmbEditorTools);
			springLayout.putConstraint(SpringLayout.EAST, editorView, -10, SpringLayout.WEST, cmbEditorTools);
			springLayout.putConstraint(SpringLayout.EAST, cmbEditorTools, -10, SpringLayout.EAST, frame.getContentPane());
			frame.getContentPane().add(cmbEditorTools);
		}
		
		// List of objects
		objectList = new JList<EditorItems>();
		{
			// Add all objects from enum
			EnumSet<EditorItems> objects = EnumSet.allOf(EditorItems.class);
			objectList.setListData(objects.toArray(new EditorItems[objects.size()]));
			
			objectList.setFocusable(false);
			objectList.setBorder(new LineBorder(Color.GRAY));
			objectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			springLayout.putConstraint(SpringLayout.NORTH, objectList, 6, SpringLayout.SOUTH, cmbEditorTools);
			springLayout.putConstraint(SpringLayout.WEST, cmbEditorTools, 0, SpringLayout.WEST, objectList);
			springLayout.putConstraint(SpringLayout.SOUTH, objectList, -10, SpringLayout.SOUTH, frame.getContentPane());
			springLayout.putConstraint(SpringLayout.EAST, objectList, 0, SpringLayout.EAST, cmbEditorTools);
			frame.getContentPane().add(objectList);
		}
	}
	
	/**
	 * Terminates the program.
	 */
	public static void exit()
	{
		// Exit Application
		System.exit(0);
	}
}
