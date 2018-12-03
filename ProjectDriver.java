import java.util.Scanner;

/*
 * Purpose: Data Structure and Algorithms Project
 * Status: Incomplete
 * Last update: 11/21/18
 * Submitted:  
 * Comment: test suite and sample run attached
 * @author: Franklin Adair, Joseph Demoneris
 * @version: 2018.01.21
 */

import java.io.*;
public class ProjectDriver {

	private static int choice = 0; // Choice the user makes in menu selection
	private static int restockNum; //Number to use for checking restock values
	private static Stock stock; //So all methods in the driver can access the stock
	

	private static final BufferedReader INPUT_MAN =  
	new BufferedReader (new InputStreamReader(System.in));

	
	/**
	 * 
	 * @param display
	 * @return
	 * @throws IOException
	 */
	public static String requestIOString(String display) throws IOException
	{// Request a String for input and trim it.
		System.out.print(display + " > ");
		String input = INPUT_MAN.readLine().trim();
		System.out.println(input);
		return input;
	}

	/**
	 * 
	 * @param display
	 * @return
	 * @throws IOException
	 */
	public static int requestIOInt(String display) throws IOException
	{// Request an int for input. Trim the result and parse it
	 // as an integer.
		System.out.print(display + " > ");
		int input = (int) Integer.parseInt(INPUT_MAN.readLine().trim());
		System.out.println(input);
		return input;
	}
	
	/**
	 * 
	 * @param display
	 * @return
	 * @throws IOException
	 */
	public static double requestIODubs(String display) throws IOException
	{// Request an double for input. Trim the result and parse it
	 // as an double.
		System.out.print(display + " > ");
		double input = (double) Double.parseDouble(INPUT_MAN.readLine().trim());
		System.out.println(input);
		return input;
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	private static void initialPrompt() throws IOException{
		int itemCount = requestIOInt("Please specify stock. \n" +
									 "How many items do you have?");
		int restockNum = requestIOInt("Please specify Restocking amount");
		stock = new Stock(restockNum);
		for(int index = 1; index <= itemCount; index++){
			String itemName = requestIOString(">>Enter Item name : ");
			int itemAmount = requestIOInt(">>How many " + itemName + "s");
			//Add item to Stock
			stock.addEntry(itemName, itemAmount);
			System.out.println(itemAmount + " items of " + itemName + " have been placed in stock.");
		}
	}

	/**
	 * 
	 * @param shoppers
	 * @throws IOException
	 */
	private static void enterShoppingCenter(ListRA<ShoppingCenter> shoppers) throws IOException {
		String customerName = requestIOString(">>Enter customer name : ");
		//Create the shopper
		Shopper theShopper = new Shopper(customerName);
		//Add customer to the shopping center collection
		//Method from shopping center to add customers will go here
		System.out.println("Customer " + customerName + " is now in the shopping center");
	}

	/**
	 * 
	 * @param shoppers
	 * @throws IOException
	 */
	private static void shopperItemAdd(ListRA<ShoppingCenter> shoppers) throws IOException {
		String customer = requestIOString(">>Enter customer name : ");
		String itemDesired = requestIOString(">>What item does " + customer + " want?");
		//Use search method in shoppingCenter to find customer
		//Add the item to their cart
		/*
		if(Amount in cart is <= to 1){
				System.out.println("Customer " + customer + " now has " + "1 item in their shopping cart.");
		}
		else{
			System.out.println("Customer " + customer + " now has " + (amount of items in cart) + " items in "+
							   "their shopping cart.");
		*/
		//Increase time for everyone
	}

	/**
	 * 
	 * @throws IOException
	 */
	private static void shopperItemRemove() throws IOException {
		String customer = requestIOString(">>Enter customer name : ");
		//Use search method in shoppingCenter to find customer
		//Decrease amount of items in cart by 1
		/*
		if(Amount in cart is <= to 1){
				System.out.println("Customer " + customer + " now has " + "1 item in their shopping cart.");
		}
		else{
			System.out.println("Customer " + customer + " now has " + (amount of items in cart) + " items in "+
							   "their shopping cart.");
		*/
		//Increase time for everyone
	}

	/**
	 * 
	 * @throws IOException
	 */
	private static void shopperDoneShopping() throws IOException {
		//Loop through the customers to see who has the most time spent
		System.out.println("After " + /*customers amount of time spent*/ " minutes in the Shopping Center customer "
						 + /*customer name*/ " with " + /*amount of items in customers cart*/  " items is now in " + 
						   /*checkout line name*/  " checkout line.");

	}

	/**
	 * 
	 * @throws IOException
	 */
	private static void shopperCheckOut() throws IOException {

	}

	/**
	 * 
	 * @param shoppers
	 * @throws IOException
	 */
	private static void printShoppingShoppers(ListRA<ShoppingCenter> shoppers) throws IOException {
		//Loop through shopping center collection to see who is still shopping
		for(int i = 0; i <= shoppers.size() -1; i++){
			//Going to use the shopper toString method here to get
			//information about each shopper
		}

	}

	/**
	 * 
	 * @throws IOException
	 */
	private static void printInLineShoppers() throws IOException {

	}

	/**
	 * 
	 */
	private static void printRestockingInfo() throws IOException {
		System.out.println("Items at re-stocking level:");
		//Loop through the items that are <= than the global variable restockingNum
	}

	/**
	 * 
	 */
	private static void reorderItem() throws IOException {
		
	}

	public static void main(String[] args) throws IOException {
		
		ListRA<ShoppingCenter> shoppers = new ListRA<ShoppingCenter>();
		
		initialPrompt();


		System.out.println("Select from the following menu:");
		System.out.println("0. Close the Shopping Center. \n" +
                           "1. Customer enters Shopping Center. \n" +
                           "2. Customer picks an item and places it in the shopping cart. \n" +
                           "3. Customer removes an item from the shopping cart. \n" +
                           "4. Customer is done shopping. \n" +
                           "5. Customer checks out. \n" +
                           "6. Print info about customers who are shopping. \n" +
                           "7. Print info about customers in checkout lines. \n" +
                           "8. Print info about items at or below re-stocking level. \n" +
                           "9. Reorder an item. \n");
                           
                           
		do {
			System.out.println("You know the options.");

				try
				{// Request an integer for a command
					choice = requestIOInt("Selection number");
				}
				catch (Exception e)
				{
					choice = 999; // This triggers the invalid entry message
									// if the input is not valid
				}

				switch (choice) {

				case 0:
					System.out.println("The Shopping Center is now closing...Thank you for shopping with us, "
									 + " have a nice day!");
					System.exit(0);
					break;

				case 1:
					enterShoppingCenter(shoppers);
					break;

				case 2:
					shopperItemAdd(shoppers);
					break;

				case 3:
					shopperItemRemove();
					break;

				case 4:
					shopperDoneShopping();
					break;

				case 5:
					shopperCheckOut();
					break;
				case 6:
					printShoppingShoppers(shoppers);
					break;
				case 7:
					printInLineShoppers();
					break;
				case 8:
					printRestockingInfo();
					break;
				case 9:
					reorderItem();
					break;
				default:
					System.out.println("Invalid input.");
				}
		} while (choice < 8);
	}
}
