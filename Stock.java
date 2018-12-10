/**
 * Stock.java
 * Purpose: Maintains two arrays for grocery items and their respective amounts 
 * @author Joseph Demoneris
 * @version 12/3/18
 */
public class Stock
{
	private ListOrdered<String> itemNames; // Ordered list of names
	private ListRA<Integer> itemCounts; // Unordered list of integers
	/*
	 * These are fundamentally related with one another, an ordered 
	 * array of Strings for item names and a corresponding array  
	 * of the number of the respective items.
	 * Because both arrays are modified concurrently, itemNames[x] will always correspond
	 * to the name of the item whose number of objects is also itemCounts[x].
	 */
	private int restockValue;
	
	/*
	 * CONSTRUCTORS
	 */

	/**
	 * Constructor
	 * The default constructor does not specify a restock value
	 * The only items need to be restocked are those with none left
	 */
	public Stock()
	{
		this.itemNames = new ListOrdered<>();
		this.itemCounts = new ListRA<>();
		this.restockValue = 0;
	}

	/**
	 * Constructor
	 * As above, but specify a restock value
	 *
	 * @param restockValue The minimum number of an item to throw an alert when querried
	 */
	public Stock(int restockValue)
	{
		this.itemNames = new ListOrdered<>();
		this.itemCounts = new ListRA<>();
		this.restockValue = restockValue;
	}

	/*
	 * METHODS
	 */

	/**
	 * Use binarysearch to add a new item name and if successful add a count
	 * to the corresponding entry in the itemCounts array
	 *
	 * @param name The name of the new item to add
	 * @param amount The amount in stock of this new item
	 */
	public void addEntry(String name, int amount)
	{
		int determinant = itemNames.add(name); // add the new item, fetch its 
											  // index
		if (determinant < 0)
		{
			itemCounts.add(-(determinant + 1), amount); // add the amount to 
														// the equivalent index
		}
		else
		{
			System.out.println("Attempted to add a duplicate item to the stock manifest.");
		}
	}
	
	/**
	 * Given the name of an item, get its encoded location of data in both arrays
	 *
	 * @param name Name of the sought after item
	 * @return encoded location of the key from 1 ... n, with negative values 
	 * 			not existing
	 */
	public int findItem(String name)
	{
		return itemNames.binSearch(name);
	}

	/**
	 * Change the count index's value by a specified amount
	 * Expected to be used in conjunction with a fetched index from findItem()
	 *
	 * @param index The target index to modify
	 * @param amount The amount by which to change the count
	 */
	public void changeCount(int index, int amount)
	{
		int newVal = itemCounts.get(index) + amount;
		itemCounts.remove(index);
		itemCounts.add(index, newVal); 
		// NOTE: could be expedited with a set(...) method
		// for the listRA
	}

	/**
	 * Find the count of the item in the given index
	 *
	 * @param index Item to be queried
	 */
	public int findCount(int index)
	{
		return itemCounts.get(index);
	}

	/**
	 * Fetch the list of all items that need a refill 
	 *
	 * @return Return a string of all items and their counts that need a refill
	 * 			formatted as NAME : COUNT
	 */
	public String getRestocks()
	{
		String retString = "";
		int size = itemCounts.size(); // #itemCounts should == #itemNames
		for (int i = 0; i < size; i++) // for each count...
		{
			int count = itemCounts.get(i);
			if (count <= restockValue) // if its <= restock
			{
				retString += itemNames.get(i) + " : " + count + "\n";
				// print that index's name and its count
			}
		}
		return retString;
	}

}
