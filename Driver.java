/*
 * Purpose: Data Structure and Algorithms Project
 * Status: Complete and well throughly tested
 * Last update: 12/10/18
 * Submitted: 12/11/18
 * Comment: test suite and sample run attached
 * @author: Franklin Adair
 * @version: 2018.12.10
 */

import java.io.*;
public class Driver {

	private static int choice = 0; // Choice the user makes in menu selection
	private static final BufferedReader INPUT_MAN =  
	new BufferedReader (new InputStreamReader(System.in));

	
	/**
	 * Takes in a string specified by the programmer and displays it, then asks for user input data.
	 * User input data is then formatted and trimmed, printed out and the value is returned.
	 * 
	 * @param display What text you want to display as the prompt for the user
	 * @return returns the String input by the user
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
	 * Takes in a string specified by the programmer and displays it, then asks for user input data.
	 * User input data is then casted to an integer, parsed and trimmed, printed out, and then the
	 *  value given is returned.
	 * 
	 * @param display What text you want to display as the prompt for the user
	 * @return returns the String input by the user
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
	 * Repeated code was often found in the driver so this is a helper class to find a 
	 * person in the shopping center that will definitely be there. Otherwise exceptions 
	 * are handled accordingly within each method.
	 * 
	 * @param shopCent The shopping center the we are referencing
	 * @param shopName String name of the shopper we want to find
	 * @return returns the shopper object found to the method that calls
	 * 		   this one
	 */
	private static Shopper requestShopper(ShoppingCenter shopCent, String shopName){
		int shopIndex = shopCent.shopperFindByName(shopName); //Index of requested shopper
		Shopper requestedShopper = shopCent.getShopperList().get(shopIndex); //Getting Shopper object by int index
		return requestedShopper;
	}

	/**
	 * This method allows for a customer to be added to the Shopping Center created
	 * in main. Customers will be asked for their name and checks will be employed
	 * to see if a customer is already in the shopping center.
	 * 
	 * @param shopCent The shopping center the we are referencing
	 * @throws IOException
	 */
	private static void enterShoppingCenter(ShoppingCenter shopCent) throws IOException {
		String customerName = requestIOString(">>Enter customer name : ");
		
		//Create the shopper
		Shopper theShopper = new Shopper(customerName);
		int check = shopCent.shoppersAdd(theShopper);
		while(check == 0){
			System.out.println(customerName +" is already in the Shopping Center.");
			customerName = requestIOString(">>Enter customer name : ");
			theShopper = new Shopper(customerName);
			check = shopCent.shoppersAdd(theShopper);
		}
		System.out.println("Customer " + customerName + " is now in the shopping center");
	}

	/**
	 * This method adds items to a shopper's cart. Manipulating of stock is done in 
	 * the ShoppingCenter class. 
	 * 
	 * @param shopCent The shopping center the we are referencing
	 * @throws IOException
	 */
	private static void shopperItemAdd(ShoppingCenter shopCent) throws IOException {
		ListOrdered<Shopper> shopCheck = shopCent.getShopperList(); //List of shoppers
		if(shopCheck.isEmpty()){
			System.out.println("Nobody is in the shopping center.");
		}
		else{
			String customer = requestIOString(">>Enter customer name : ");
			int shopIndex = shopCent.shopperFindByName(customer); //Index the customer is located at
			while(shopIndex < 0){ //Loop while invalid shopper names are entered
				System.out.println("That is not a valid shopper name.");
				customer = requestIOString(">>Enter customer name : ");
				shopIndex = shopCent.shopperFindByName(customer);
			}
			if(shopIndex >= 0){//If shopper name is successfully found we can add items to their cart
				String itemDesired = requestIOString(">>What item does " + customer + " want?");
				//Add the item to their cart
				Shopper requestedShopper = requestShopper(shopCent, customer); //gets the shopper object associated with 
																		      //customer name
				shopCent.shopperGetItem(requestedShopper, itemDesired); //gives item to the customer
		
				//if statement for gramatical efficiency
				if(requestedShopper.getItems() <= 1){
					System.out.println("Customer " + customer + " now has 1 item in their shopping cart.");
				}
				else{
					System.out.println("Customer " + customer + " now has " + requestedShopper.getItems() + " items in "+
			      				   	   "their shopping cart.");
				}
			}
		//Increase time for everyone is accomplished in shopperGetItem
		}
	}

	/**
	 * This method removes an item from a shopper's cart using the method defined in the
	 * ShoppingCenter class. 
	 * 
	 * @param shopCent The shopping center the we are referencing
	 * @throws IOException
	 */
	private static void shopperItemRemove(ShoppingCenter shopCent) throws IOException {
		ListOrdered<Shopper> shopCheck = shopCent.getShopperList(); //List of shoppers
		if(shopCheck.isEmpty()){
			System.out.println("Nobody is in the shopping center.");
		}
		else{
			String customer = requestIOString(">>Enter customer name : ");
			int shopIndex = shopCent.shopperFindByName(customer); //Index of the customer we want
			while(shopIndex < 0){
				System.out.println("That is not a valid shopper name.");
				customer = requestIOString(">>Enter customer name : ");
				shopIndex = shopCent.shopperFindByName(customer);
			}
			Shopper requestedShopper = requestShopper(shopCent, customer);
			shopCent.shopperRemoveItem(requestedShopper);
		
			System.out.println("Customer " + customer + " now has " + requestedShopper.getItems() + " items in "+
							   "their shopping cart.");
		//Increase time for everyone accomplished in shopperRemoveItem
		}
	}

	/**
	 * This method removes a shopper from the shoppingCenter and places them in a checkout lane
	 * based on the parameters referenced in shopperFindByTime
	 * 
	 * @param shopCent The shopping center the we are referencing
	 * @throws IOException
	 */
	private static void shopperDoneShopping(ShoppingCenter shopCent) throws IOException {
		ListOrdered<Shopper> shopCheck = shopCent.getShopperList();
		LineManager lineManager = shopCent.getLines();
		int shopIndex = shopCent.shopperFindByTime();
		System.out.println(shopIndex);
		
		if(shopCheck.size() > 0){
			Shopper selectedShopper = shopCent.shoppersRemove(shopIndex);
			if(selectedShopper.getItems() > 0){
				lineManager.acceptShopper(selectedShopper);
				System.out.println("After " + selectedShopper.getTime() + " minutes in the Shopping Center customer "
					     + selectedShopper.getName() + " with " + selectedShopper.getItems() + " items is now in the " + 
					     /*checkout line name*/  " checkout lane.");
				
			}
			else{
				String custRequest = requestIOString("Should customer " + selectedShopper.getName() + " leave or keep on "
						+ "shopping? Leave?(Y/N): ");
				
				switch(custRequest){
				
				case "Y":
					System.out.println("Customer " + selectedShopper.getName() +  " is now leaving the Shopping Center.");
					break;
				
				case "N":
					System.out.println("Customer " + selectedShopper.getName() + " with " + selectedShopper.getItems() + 
					           " items returned to shopping.");
					selectedShopper.clearTime();
					shopCent.shoppersAdd(selectedShopper);
					break;
					
				default:
					System.out.println("Incorrect option defaulting to leaving.");
				}
			}
		}
		else{
			System.out.println("There is nobody in the shopping center");
		}

	}

	/**
	 * Checks a customer out and asks them if they wish to keep shopping and return to the
	 * shopping center with 0 items and a reset time or if they wish to leave the store. 
	 * 
	 * @param shopCent The shopping center the we are referencing
	 * @throws IOException
	 */
	private static void shopperCheckOut(ShoppingCenter shopCent) throws IOException {
		LineManager lineManager = shopCent.getLines();
		if(lineManager.checkAllLines() < 0){
			System.out.println("No customers in any line.");
		}
		else{
			int queuePosition = lineManager.checkAllLines();
			Shopper shopper = lineManager.removeShopper(queuePosition);
			
			String customerOp = requestIOString("Should customer " + shopper.getName() + " check out or keep shopping? "
					                          + "Checkout? (Y/N):");
			if(customerOp.equals("Y")){
				System.out.println("Customer " + shopper.getName() +  " is now leaving the Shopping Center.");
			}
			else{
				System.out.println("Customer " + shopper.getName() + " with " + shopper.getItems() + " items returned to shopping.");
				shopper.clearTime();
				shopCent.shoppersAdd(shopper);
			}
		}
		
	}

	/**
	 * Prints a list of the customers that are currently shopping
	 * 
	 * @param shopCent The shopping center the we are referencing
	 * @throws IOException
	 */
	private static void printShoppingShoppers(ShoppingCenter shopCent) throws IOException {
		ListOrdered<Shopper> shoppers = shopCent.getShopperList();
		if(shoppers.isEmpty()){
			System.out.println("The shopping center is empty.");
		}
		else{
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

	}

	/**
	 * Prints information about the people who are currently in line.
	 * 
	 * @param shopCent The shopping center the we are referencing
	 * @throws IOException
	 */
	private static void printInLineShoppers(ShoppingCenter shopCent) throws IOException {
		LineManager lineManager = shopCent.getLines();
		System.out.println(lineManager.toString());
	}

	/**
	 * Utilizes the toString method found in the stock class to print out information
	 * about items at or below restocking level.
	 * 
	 * @param stock The stock that we are going to be checking for restocks. 
	 * @throws IOException
	 */
	private static void printRestockingInfo(Stock stock) throws IOException {
		System.out.println("Items at re-stocking level:");
		//Loop through the items that are <= than the global variable restockingNum
		System.out.println(stock.getRestocks());
	}

	/**
	 * This method adds to the current value of an item defined in the Stock 
	 * 
	 * @param shopCent shopping center we are referencing
	 * @param stock stock used to locate items and add to their current stock level
	 * @throws IOException
	 */
	private static void reorderItem(ShoppingCenter shopCent, Stock stock) throws IOException {
		String itemName = requestIOString("Enter item name to be re-ordered : ");
		int itemLocation = stock.findItem(itemName) - 1;
		if(itemLocation < 0){
			System.out.println(itemName + " is not in stock!");
		}
		else{
			int itemAmount = requestIOInt("Enter number of " + itemName + " to be re-ordered :");
			stock.changeCount(itemLocation, itemAmount);
			System.out.println("Stock now has " + stock.findCount(itemLocation) + " " + itemName + "s");
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		int restockNum = 0; //Number to use for checking restock values
		int firstLineIndex = 0; //Index of the first line to be checked out
		
		//Initial prompt to get stock information from user
		int itemCount = requestIOInt("Please specify stock. \n" +
				 "How many items do you have?");
		restockNum = requestIOInt("Please specify Restocking amount");
		String expressLane = requestIOString("Please select the checkout line that should check out customers " + 
                " first (regular1/regular2/express)");
		//Express lane is defined first so we can create the shopping center used
		//for this program so we can also define our stock and then add to it. This
		//is reflected in our modified input
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
		ShoppingCenter shopCent = new ShoppingCenter(3, firstLineIndex, restockNum);
		Stock stock = shopCent.getStock();
		//looping until amount of items in stock is reached to add items and their
		//stock levels to stock
		for(int index = 1; index <= itemCount; index++){
			String itemName = requestIOString(">>Enter Item name : ");
			int itemAmount = requestIOInt(">>How many " + itemName + "s");
			//Add item to Stock
			stock.addEntry(itemName, itemAmount);
			System.out.println(itemAmount + " items of " + itemName + " have been placed in stock.");
		}
		
		
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
									 + "have a nice day!");
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
					printInLineShoppers(shopCent);
					break;
				case 8:
					printRestockingInfo(stock);
					break;
				case 9:
					reorderItem(shopCent, stock);
					break;
				default:
					System.out.println("Invalid input.");
				}
		} while (choice < 10);
	}
}
