public class Stock
{
	private ListOrdered<String> itemNames;
	private ListRA<Integer> itemCounts;
	private int restockValue;

	public Stock()
	{
		this.itemNames = new ListOrdered<>();
		this.itemCounts = new ListRA<>();
		this.restockValue = 0;
	}

	public Stock(int restockValue)
	{
		this.itemNames = new ListOrdered<>();
		this.itemCounts = new ListRA<>();
		this.restockValue = restockValue;
	}

	public void addEntry(String name, int amount)
	{
		int determinant = itemNames.add(name); // add the new item, fetch its index
		if (determinant > 0)
		{
			itemCounts.add(determinant - 1, amount); // add the amount to the equivalent index
		}
		else
		{
			System.out.println("Attempted to add a duplicate item to the stock manifest.");
		}
	}

	public int findItem(String name)
	{
		return itemNames.binSearch(name);
	}

	public void changeCount(int index, int amount)
	{
		itemCounts.remove(index);
		itemCounts.add(index, amount); 
		// NOTE: could be expedited with a set(...) method
		// for the listRA
	}

	public String getRestocks()
	{
		String retString = "";
		int size = itemCounts.size(); // |itemCounts| should == |itemNames|
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
