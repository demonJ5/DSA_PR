/*
 * Purpose: Stock Test Class
 * Status: Complete 
 * Last update: 12/10/18
 * Submitted:  12/10/18
 * Comment: 
 * @author: Joseph Demoneris
 * @version: 2018.10.12
 */
/**
 * test_stock.java
 * This test class is for testing Stock.java.
 */
public class test_stock
{
	public static void main(String[] args)
	{
		Stock stockTest = new Stock(3);
		stockTest.addEntry("Foo", 2);
		stockTest.addEntry("Bar", 5);
		stockTest.addEntry("Fubar", 1);
		System.out.println(stockTest.getRestocks());
	}
}
