/*
 * Purpose: Shopper ADT
 * Status: Complete and tested 
 * Last update: 12/10/18
 * Submitted:  12/10/18
 * Comment: 
 * @author: Joseph Demoneris
 * @version: 2018.10.12
 */
/**
 * Shopper.java
 * Purpose: Provide a framework for each shopper that is capable of maintaining
 * 			name, items, and timespent in a sortable-by-name Class
 * @author Joseph Demoneris
 * @version 12/10/18
 */	
public class Shopper implements Comparable<Shopper>
{
	private int items;
	private int timeSpent;
	private String name;

	/**
	 * Construct the Shopper with an explicit name
	 *
	 * @param name String name of the shopper
	 */
	public Shopper(String name)
	{
		this.items = 0;
		this.timeSpent = 0;
		this.name = name;
	}

	/**
	 * Construct the Shopper with both an explicit name and number of items.
	 * Used exclusively for debugging.
	 *
	 * @param name String name of the shopper
	 * @param items int number of items in their cart
	 */
	public Shopper(String name, int items)
	{
		this.items = items;
		this.timeSpent = 0;
		this.name = name;
	}

	/**
	 * Retrieve the number of items the shopper has.
	 *
	 * @return int count of items in the shopper's c art
	 */
	public int getItems()
	{
		return items;
	}

	/**
	 * Add a single item to the shopper's cart.
	 */
	public void addItem()
	{
		items++;
	}

	/**
	 * Remove a single item from the shopper's cart.
	 */
	public void dropItem()
	{
		items--;
	}

	/**
	 * Retrieve the time in minutes that the shopper has spent inside.
	 *
	 * @return int number of minutes the shopper has been inside
	 */
	public int getTime()
	{
		return timeSpent;
	}

	/**
	 * Reset the shopper's timer.
	 */
	public void clearTime()
	{
		timeSpent = 0;
	}

	/**
	 * Tic the customer's spent time by 1.
	 */
	public void iterateTime()
	{
		timeSpent++;
	}

	/**
	 * Retrieve the customer's name.
	 *
	 * @return String representation of the customer's name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Compare this Shopper to other instances of Shopper.
	 * Note: Necessitated by Comparable<Shopper>
	 *
	 * @param key Shopper being compared to
	 * @returns int less than 0 when this less than key
	 * 				equal to 0 when this equals key
	 * 				greater than zero when this is greater than key
	 */
	public int compareTo(Shopper key)
	{
		return name.compareTo(key.getName());
	}

	public String toString()
	{
		return "" + name + "	Time Spent: " + timeSpent + "	Items: " + items;
	}

	
}
