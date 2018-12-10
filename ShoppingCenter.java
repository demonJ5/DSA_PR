/**
 * ShoppingCenter.java
 * Purpose: Provide an ADT that manages the storage of customers, stock, and 
 * 			checkout queues in one package
 *
 * @author Joseph Demoneris
 * @version 12/7/18
 */
public class ShoppingCenter
{
	// Stores current shopping shoppers
	private final ListOrdered<Shopper> SHOPPER_LIST; 
	// Stores shoppers on line
	private final LineManager LINE_MAN;
	// Stores item stock information
	private final Stock STOCK_MAN;

	/**
	 * Constructor:
	 * Create a new ShoppingCenter by specifying the number of
	 * checkout lines, the first line to check out a customer,
	 * and the minimum number of stock for an item to be marked
	 * as needing to be restocked
	 *
	 * @param numLines int number of checkout lines, including
	 * 					the priority queue.
	 * @param firstLine int index of the first line to check out
	 * 					customers when called
	 * @param restockVal int minimum number of stock for an item to
	 * 					be marked as requiring a restock
	 */
	public ShoppingCenter(int numLines, int firstLine, int restockVal)
	{
		SHOPPER_LIST = new ListOrdered<>();
		LINE_MAN = new LineManager(numLines, firstLine);
		STOCK_MAN = new Stock(restockVal);
	}

	/*
	 * GETTER METHODS
	 */

	/**
	 * Retrieve the Stock ADT
	 *
	 * @return Stock ADT
	 */
	public Stock getStock()
	{
		return STOCK_MAN;
	}

	/**
	 * Retrieve the LineManager ADT
	 *
	 * @return LineManager ADT
	 */
	public LineManager getLines()
	{
		return LINE_MAN;
	}

	/**
	 * Retrieve the list of Shoppers
	 *
	 * @return ListOrdered<Shopper> ADT
	 */
	public ListOrdered<Shopper> getShopperList()
	{
		return SHOPPER_LIST;
	}
	
	/*
	 * METHODS
	 */

	/**
	 * Tic time for all shoppers in the SHOPPER_LIST by 1
	 */
	private void timeTic()
	{
		int totalShoppers = SHOPPER_LIST.size();
		for (int i = 0; i < totalShoppers; i++)
		{
			SHOPPER_LIST.get(i).iterateTime();
		}
	}

	/**
	 * Find a shopper by name and return their index in SHOPPER_LIST
	 * 
	 * @param searchName String name of the sought after Shopper
	 * @return If successful, the SHOPPER_LIST index for the Shopper,
	 * 			if not found, then -1
	 */
	public int shopperFindByName(String searchName)
	{
		int retVal = -1;
		// In order to search in SHOPPER_LIST, we need to search by the class
		// the generic is instantiated as: Shopper. Because Shopper's 
		// compareTo(Shopper) method only checks names for equality, this
		// should yield us the index of the Shopper whose name is the same as
		// "searchName"
		Shopper searchKey = new Shopper(searchName); 
		int determinant = SHOPPER_LIST.binSearch(searchKey);
		if (determinant > 0)
		{
			retVal = determinant - 1;
		} // else return -1, default value of retVal
		return retVal;
	}

	/**
	 * Find the first shopper with the highest time spent in SHOPPER_LIST
	 * and return their index
	 *
	 * @return int index of the shopper with the highest time spent shopping
	 * 			or -1 if there are no shoppers currently
	 */
	public int shopperFindByTime()
	{
		int retVal = -1;
		int shopListSize = SHOPPER_LIST.size();
		if (shopListSize > 0)
		{
			int maxIndex = 0;
			int maxTime = SHOPPER_LIST.get(0).getTime();
			for (int i = 0; i < shopListSize; i++)
			{
				Shopper currShopper = SHOPPER_LIST.get(i);
				int currTime = currShopper.getTime();
				if (currTime > maxTime)
				{
					maxTime = currTime;
					maxIndex = i;
				}
			}
			retVal = maxIndex;
		}
		return retVal;
	}
	
	/**
	 * Add a new Shopper to SHOPPER_LIST
	 *
	 * @param Shopper The new shopper; ideally generated in
	 * 				the driver
	 * @return int encoded index of insertion from 1...n within SHOPPER_LIST
	 * 			if successful, 0 if attempted to insert a duplicate
	 */
	public int shoppersAdd(Shopper newShopper)
	{
		return SHOPPER_LIST.add(newShopper);
	}

	/**
	 * Remove a shopper from SHOPPER_LIST
	 *
	 * @param index int index to remove the shopper from
	 * @return Shopper The removed shopper
	 */
	public Shopper shoppersRemove(int index)
	{
		Shopper retVal = SHOPPER_LIST.get(index);
		SHOPPER_LIST.remove(index);
		return retVal;
	}

	/**
	 * Given a selected shopper and the name of an item, decrease
	 * that item's stock by one and increase the shopper's cart by one
	 *
	 * @param selectedShopper Shopper picking the item
	 * @param itemName String name of the item to select
	 * @return int 2 if failed to find item, 1 if item is out of
	 * 			stock, 0 if successful, -1 if unknown failure
	 */
	public int shopperGetItem(Shopper selectedShopper, String itemName)
	{
		int retVal = -1;
		int itemIndexEval = STOCK_MAN.findItem(itemName);
		// Check if item found...
		if(itemIndexEval > 0)
		{
			// adjust from 1...n to 0...n-1
			int itemRealIndex = itemIndexEval - 1;
			int currCount = STOCK_MAN.findCount(itemRealIndex);
			// Check if item is in stock
			if(currCount > 0)
			{
				STOCK_MAN.changeCount(itemRealIndex, -1);
				selectedShopper.addItem();
				timeTic();
				retVal = 0; // Operation successful
			}
			else
			{
				retVal = 1; // Item out of stock
			}
		}
		else
		{
			retVal = 2; // Item not found
		}
		return retVal;
	}

	/**
	 * Given a selected shopper, attempt to remove an item from theiir cart
	 * If this results in a failure, time is NOT ticced as the action is
	 * not successful
	 *
	 * @param selectedShopper Shopper removing the item
	 * @return Boolean true if successful, false if had no items
	 */
	public boolean shopperRemoveItem(Shopper selectedShopper)
	{
		boolean retVal = false;
		int itemCount = selectedShopper.getItems();
		if (itemCount > 0)
		{
			selectedShopper.dropItem();
			timeTic();
			retVal = true;
		}
		return retVal;
	}

	
}
