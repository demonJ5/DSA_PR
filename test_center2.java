/*
 * Purpose: Shopping Center Test Program 2
 * Status: Complete
 * Last update: 12/10/18
 * Submitted:  12/10/18
 * Comment: 
 * @author: Joseph Demoneris
 * @version: 2018.10.12
 */
/**
 * test_center2.java
 * This application is used to test the functionality of ShoppingCenter.java.
 */
public class test_center2
{
	public static void main(String[] args)
	{
		System.out.println("Test stocks");	
		ShoppingCenter center = new ShoppingCenter(3,1,3);
		LineManager lineman = center.getLines();
		
		center.shoppersAdd(new Shopper("Takua"));
		System.out.println(center.getShopperList());
		
		int shopDex;
		shopDex = center.shopperFindByName("Takua");
		lineman.acceptShopper(center.shoppersRemove(shopDex));
		System.out.println("Whiile in");
		System.out.println(center.shoppersAdd(new Shopper("Takua")));
		System.out.println(center.getShopperList());

		System.out.println(lineman);
		System.out.println("Checkout");
		lineman.removeShopper(lineman.checkAllLines());
		System.out.println(lineman);
		System.out.println("Adding again");
		System.out.println(center.shoppersAdd(new Shopper("Takua")));
		System.out.println(center.getShopperList());


		
	}
}
