package me.hawsoo.lib;

/**
 * List of items that the editor
 * utilizes for the map.
 * @author Hawsoo
 *
 */
public enum EditorItems
{
	PLAYER("Player", Resources.S_PLAYER),
	GND_REG("Ground Regular");
	
	public String name;
	public ItemTypes type;
	public Sprite icon;
	
	/**
	 * For Grounds.
	 * @param type
	 */
	private EditorItems(String name)
	{
		this.name = name;
		type = ItemTypes.GROUND;
	}
	
	/**
	 * For Entities.
	 * @param icon
	 */
	private EditorItems(String name, Sprite icon)
	{
		this.name = name;
		type = ItemTypes.ENTITY;
		this.icon = icon;
	}
	
	@Override
	public String toString()
	{
		String tag = name + " ";
		
		// Add tags
		if (type == ItemTypes.ENTITY)
		{
			tag += "<Entity> ";
		}
		else if (type == ItemTypes.GROUND)
		{
			tag += "<Ground> ";
		}
		
		// Return
		return tag;
	}
}
