/**
 * ShoppingCenter.java
 * Purpose: Provide an ADT that manages the storage of customers, stock, and checkout queues in one package
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
	
	
}
