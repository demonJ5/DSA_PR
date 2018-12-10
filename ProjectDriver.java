import java.util.Scanner;

/*
 * Purpose: Data Structure and Algorithms Project
 * Status: Incomplete
 * Last update: 12/9/18
 * Submitted: 12/11/18
 * Comment: test suite and sample run attached
 * @author: Franklin Adair
 * @version: 2018.01.21
 */

import java.io.*;
public class ProjectDriver {

	private static int choice = 0; // Choice the user makes in menu selection
	private static int restockNum = 0; //Number to use for checking restock values
	private static int firstLineIndex = 0; //Index of the first line to be checked out
	

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
	 * @param shopCent
	 * @param shopName
	 * @return
	 */
	private static Shopper requestShopper(ShoppingCenter shopCent, String shopName){
		int shopIndex = shopCent.shopperFindByName(shopName); //Index of requested shopper
		Shopper requestedShopper = shopCent.getShopperList().get(shopIndex); //Getting Shopper object by int index
		return requestedShopper;
	}
	
	/**
	 * 
	 * @param stock
	 * @throws IOException
	 */
	private static void initialPrompt(Stock stock) throws IOException{
		int itemCount = requestIOInt("Please specify stock. \n" +
									 "How many items do you have?");
		restockNum = requestIOInt("Please specify Restocking amount");
		for(int index = 1; index <= itemCount; index++){
			String itemName = requestIOString(">>Enter Item name : ");
			int itemAmount = requestIOInt(">>How many " + itemName + "s");
			//Add item to Stock
			stock.addEntry(itemName, itemAmount);
			System.out.println(itemAmount + " items of " + itemName + " have been placed in stock.");
		}
		String expressLane = requestIOString("Please select the checkout line that should check out customers " + 
									  " first (regular1/regular2/express)");
		switch(expressLane){
		
		case "regular1":
			firstLineIndex = 0;
			break;
		
		case "regular2":
			firstLineIndex = 1;
			break;
			
		case "express":
			firstLineIndex = 2;
			break;
			
		default:
			System.out.println("That is not a valid line entry.");
		
		}
		
		
	}

	/**
	 * 
	 * @param shopCent
	 * @throws IOException
	 */
	private static void enterShoppingCenter(ShoppingCenter shopCent) throws IOException {
		String customerName = requestIOString(">>Enter customer name : ");
		//Create the shopper
		Shopper theShopper = new Shopper(customerName);
		//Add customer to the shopping center collection
		shopCent.shoppersAdd(theShopper);
		//Method from shopping center to add customers will go here
		System.out.println("Customer " + customerName + " is now in the shopping center");
	}

	/**
	 * 
	 * @param shopCent
	 * @throws IOException
	 */
	private static void shopperItemAdd(ShoppingCenter shopCent) throws IOException {
		String customer = requestIOString(">>Enter customer name : ");
		String itemDesired = requestIOString(">>What item does " + customer + " want?");
		//Add the item to their cart
		Shopper requestedShopper = requestShopper(shopCent, customer);
		System.out.println(shopCent.shopperGetItem(requestedShopper, itemDesired));
		
		if(requestedShopper.getItems() <= 1){
				System.out.println("Customer " + customer + " now has 1 item in their shopping cart.");
		}
		else{
			System.out.println("Customer " + customer + " now has " + requestedShopper.getItems() + " items in "+
							   "their shopping cart.");
		}
		//Increase time for everyone is accomplished in shopperGetItem
	}

	/**
	 * 
	 * @param shopCent
	 * @throws IOException
	 */
	private static void shopperItemRemove(ShoppingCenter shopCent) throws IOException {
		String customer = requestIOString(">>Enter customer name : ");
		//Decrease amount of items in cart by 1
		Shopper requestedShopper = requestShopper(shopCent, customer);
		shopCent.shopperRemoveItem(requestedShopper);
		
		if(requestedShopper.getItems() <= 1){
				System.out.println("Customer " + customer + " now has 1 item in their shopping cart.");
		}
		else{
			System.out.println("Customer " + customer + " now has " + requestedShopper.getItems() + " items in "+
							   "their shopping cart.");
		}
		//Increase time for everyone accomplished in shopperRemoveItem
	}

	/**
	 * 
	 * @param shopCent
	 * @throws IOException
	 */
	private static void shopperDoneShopping(ShoppingCenter shopCent) throws IOException {
		//Loop through the customers to see who has the most time spent
		int shopIndex = shopCent.shopperFindByTime();
		Shopper requestedShopper = shopCent.getShopperList().get(shopIndex);
		LineManager lineManager = shopCent.getLines();
		lineManager.acceptShopper(requestedShopper);
		System.out.println("After " + requestedShopper.getTime() + " minutes in the Shopping Center customer "
						 + requestedShopper.getName() + " with " + requestedShopper.getItems() + " items is now in " + 
						   /*checkout line name*/  " checkout lane.");
		//Add customer to correct shopping lane, will be handled by line manager class

	}

	/**
	 * 
	 * @throws IOException
	 */
	private static void shopperCheckOut(ShoppingCenter shopCent) throws IOException {
		LineManager lineManager = shopCent.getLines();
		int queuePosition = lineManager.checkAllLines();
		Shopper shopper = lineManager.removeShopper(queuePosition);
		
		String customerOp = requestIOString("Should customer " + shopper.getName() + " check out or keep shopping? "
				                          + "Checkout? (Y/N):");
		if(customerOp == "Y"){
			System.out.println("Customer " + shopper.getName() +  "is now leaving the Shopping Center.");
		}
		else{
			System.out.println("Customer " + shopper.getName() + " with " + shopper.getItems() + " items returned to shopping.");
			shopper.clearTime();
			shopCent.shoppersAdd(shopper);
		}
	}

	/**
	 * 
	 * @param shoppers
	 * @throws IOException
	 */
	private static void printShoppingShoppers(ShoppingCenter shopCent) throws IOException {
		ListOrdered<Shopper> shoppers = shopCent.getShopperList();
		System.out.println("The following " + shoppers.size() + " customers are in the Shopping Center:");
		//Loop through shopping center collection to see who is still shopping
		for(int i = 0; i <= shoppers.size() -1; i++){
			//Loop through list of shoppers grabbed from the shopping center class
			String shopperName = shoppers.get(i).getName();
			int cartVal = shoppers.get(i).getItems();
			int timeSpent = shoppers.get(i).getTime();
			System.out.println("Customer " + shopperName + " with " + cartVal + " items present for " + 
			                   timeSpent + " minutes.");
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
	 * @param stock
	 * @throws IOException
	 */
	private static void printRestockingInfo(Stock stock) throws IOException {
		System.out.println("Items at re-stocking level:");
		//Loop through the items that are <= than the global variable restockingNum
		System.out.println(stock.getRestocks());
	}

	/**
	 * 
	 */
	private static void reorderItem() throws IOException {
		
	}

	public static void main(String[] args) throws IOException {
		
		//Still need to use index of express line instead of choice for shopCent
		ShoppingCenter shopCent = new ShoppingCenter(3, firstLineIndex, restockNum);
		Stock stock = shopCent.getStock();
		
		initialPrompt(stock);


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
			System.out.println("Make your menu selection now.");

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
					enterShoppingCenter(shopCent);
					break;

				case 2:
					shopperItemAdd(shopCent);
					break;

				case 3:
					shopperItemRemove(shopCent);
					break;

				case 4:
					shopperDoneShopping(shopCent);
					break;

				case 5:
					shopperCheckOut(shopCent);
					break;
				case 6:
					printShoppingShoppers(shopCent);
					break;
				case 7:
					printInLineShoppers();
					break;
				case 8:
					printRestockingInfo(stock);
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
