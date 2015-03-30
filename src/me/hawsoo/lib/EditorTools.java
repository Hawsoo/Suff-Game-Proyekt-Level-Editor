package me.hawsoo.lib;

/**
 * The tools used by the editor for
 * manipulating objects.
 * @author Hawsoo
 *
 */
public enum EditorTools
{
	SELECT(0, "Select"),
	CREATE(1, "Create"),
	DELETE(2, "Delete");

	private int index;
	private String name;
	
	private EditorTools(int index, String name)
	{
		this.index = index;
		this.name = name;
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public String getName()
	{
		return name;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
