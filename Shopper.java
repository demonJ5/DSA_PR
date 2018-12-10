public class Shopper implements Comparable<Shopper>
{
	private int items;
	private int timeSpent;
	private String name;

	public Shopper(String name)
	{
		this.items = 0;
		this.timeSpent = 0;
		this.name = name;
	}

	public Shopper(String name, int items)
	{
		this.items = items;
		this.timeSpent = 0;
		this.name = name;
	}

	public int getItems()
	{
		return items;
	}

	public void addItem()
	{
		items++;
	}

	public void dropItem()
	{
		items--;
	}

	public int getTime()
	{
		return timeSpent;
	}

	public void clearTime()
	{
		timeSpent = 0;
	}

	public void iterateTime()
	{
		timeSpent++;
	}

	public String getName()
	{
		return name;
	}

	public int compareTo(Shopper key)
	{
		return name.compareTo(key.getName());
	}

	public String toString()
	{
		return "" + name + "	Time Spent: " + timeSpent + "	Items: " + items;
	}

	
}
