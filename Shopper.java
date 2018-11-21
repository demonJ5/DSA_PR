public class Shopper
{
	private int items;
	private int timeSpent;
	private String name;

	public Shopper(String name)
	{
		items = 0;
		timeSpent  0;
		this.name = name;
	}

	public int getItems()
	{
		return items;
	}

	public int getTime()
	{
		return timeSpent;
	}

	public String getName()
	{
		return name;
	}

	public void addItem()
	{
		items++;
	}

	public void dropItem()
	{
		items--;
	}

	public void clearTime()
	{
		timeSpent = 0;
	}

	public void iterateTime()
	{
		timeSpent++;
	}

	public int compareTo(Shopper key)
	{
		return name.compareTo(key.getName());
	}

	
}
