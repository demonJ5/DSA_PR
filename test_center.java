/*
 * Purpose: Shopping Center Test Program
 * Status: Complete
 * Last update: 12/10/18
 * Submitted:  12/10/18
 * Comment: 
 * @author: Joseph Demoneris
 * @version: 2018.10.12
 */
/**
 * test_center.java
 * This application is used to test the functionality of ShoppingCenter.java.
 */
public class test_center
{
	public static void main(String[] args)
	{
		System.out.println("Test stocks");	
		ShoppingCenter center = new ShoppingCenter(3,1,3);
		Stock stock = center.getStock();
		stock.addEntry("foo",2);
		stock.addEntry("bar",10);
		stock.addEntry("meme", 1);
		System.out.println(stock.getRestocks());
		
		System.out.println("Test changecounts"); 
		int barDex = stock.findItem("bar") - 1;
		stock.changeCount(barDex, -8);
		System.out.println(stock.getRestocks()); 
		
		System.out.println("Test shoppers");
		center.shoppersAdd(new Shopper("Gregory", 5));
		center.shoppersAdd(new Shopper("Bane", 2));
		center.shoppersAdd(new Shopper("Matoro", 2));
		center.shoppersAdd(new Shopper("Kongu", 0));
		center.shoppersAdd(new Shopper("Jaller", 2));
		center.shoppersAdd(new Shopper("Tahu", 1));
		center.shoppersAdd(new Shopper("Makuta", 15));
		center.shoppersAdd(new Shopper("Hydraxon", 2));	
		System.out.println(center.getShopperList().toString()); 

		System.out.println("Find shopper");
		int kongDex = center.shopperFindByName("Kongu");
		System.out.println(kongDex);
		Shopper kongu = center.getShopperList().get(kongDex);
		System.out.println(kongu.toString());
	
		System.out.println("Shopper get item");
		center.shopperGetItem(kongu, "bar");
		System.out.println(kongu.toString());
		System.out.println(stock.getRestocks()); 

		System.out.println("Shopper go on line");
		LineManager lineman = center.getLines();

		System.out.println(lineman.toString());
		lineman.acceptShopper(kongu);
		System.out.println(lineman.toString());

		Shopper tempSh;
		int temp;

		temp = center.shopperFindByName("Jaller");
		tempSh = center.getShopperList().get(temp);
		lineman.acceptShopper(tempSh);
		System.out.println(lineman);		
		temp = center.shopperFindByName("Tahu");
		tempSh = center.getShopperList().get(temp);
		lineman.acceptShopper(tempSh);
		System.out.println(lineman);		
		temp = center.shopperFindByName("Hydraxon");
		tempSh = center.getShopperList().get(temp);
		lineman.acceptShopper(tempSh);
		System.out.println(lineman);		
		temp = center.shopperFindByName("Gregory");
		tempSh = center.getShopperList().get(temp);
		lineman.acceptShopper(tempSh);
		System.out.println(lineman);		
		temp = center.shopperFindByName("Makuta");
		tempSh = center.getShopperList().get(temp);
		lineman.acceptShopper(tempSh);
		System.out.println(lineman);		

		System.out.println("Dequeue");
		tempSh = lineman.removeShopper(lineman.checkAllLines());
		System.out.println(lineman);
		tempSh = lineman.removeShopper(lineman.checkAllLines());
		System.out.println(lineman);
		tempSh = lineman.removeShopper(lineman.checkAllLines());
		System.out.println(lineman);
		tempSh = lineman.removeShopper(lineman.checkAllLines());
		System.out.println(lineman);
		tempSh = lineman.removeShopper(lineman.checkAllLines());
		System.out.println(lineman);
		tempSh = lineman.removeShopper(lineman.checkAllLines());
		System.out.println(lineman);
		
		System.out.println("Removal by find");
		System.out.println(center.getShopperList());
		center.shoppersAdd(new Shopper("Randal", 2));	
		System.out.println(center.getShopperList());
		temp = center.shopperFindByName("Randal");
		tempSh = center.shoppersRemove(temp);
		System.out.println(tempSh);
		System.out.println(center.getShopperList());

		System.out.println("Adding duplicates");
		center.shoppersAdd(new Shopper("Takua", 5));
		System.out.println(center.getShopperList());
		temp = center.shopperFindByName("Takua");
		tempSh = center.getShopperList().get(temp);
		System.out.println("Accept shopper");
		lineman.acceptShopper(tempSh);
		System.out.println(center.getShopperList());
		System.out.println(lineman);
		System.out.println("Try to add dupe");
		System.out.println(center.shoppersAdd(new Shopper("Takua",5)));
		System.out.println(center.getShopperList());
		System.out.println("Dequeue and readd");
		lineman.checkAllLines();
		System.out.println(lineman);
		center.shoppersAdd(new Shopper("Takua", 5));
		System.out.println(center.getShopperList());
		
	}
}
