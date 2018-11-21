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

	private static final BufferedReader INPUT_MAN =  
	new BufferedReader (new InputStreamReader(System.in));

		
	public static String requestIOString(String display) throws IOException
	{// Request a String for input and trim it.
		System.out.print(display + " > ");
		String input = INPUT_MAN.readLine().trim();
		System.out.println(input);
		return input;
	}

	public static int requestIOInt(String display) throws IOException
	{// Request an int for input. Trim the result and parse it
	 // as an integer.
		System.out.print(display + " > ");
		int input = (int) Integer.parseInt(INPUT_MAN.readLine().trim());
		System.out.println(input);
		return input;
	}
	
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
	 */
	private static void enterShoppingCenter() {

	}

	/**
	 * 
	 */
	private static void shopperItemAdd() {

	}

	/**
	 * 
	 */
	private static void shopperItemRemove() {

	}

	/**
	 * 
	 */
	private static void shopperDoneShopping() {

	}

	/**
	 * 
	 */
	private static void ShopperCheckOut() {

	}

	/**
	 * 
	 */
	private static void printShoppingShoppers() {

	}

	/**
	 * 
	 */
	private static void printInLineShoppers() {

	}

	/**
	 * 
	 */
	private static void printRestockingInfo() {

	}

	/**
	 * 
	 */
	private static void reorderItem() {

	}

	public static void main(String[] args) throws IOException{


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

				case 1:

					break;

				case 2:

					break;

				case 3:

					break;

				case 4:

					break;

				case 5:

					break;

				case 6:
					System.out.println("Goodbye.");
					System.exit(0);
					break;

				default:
					System.out.println("Invalid input.");
				}
		} while (choice < 8);
	}
}
