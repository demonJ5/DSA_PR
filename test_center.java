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
		
		
		
		
	}
}
