public class test_center
{
	public static void main(String[] args)
	{
		// Test initial stock
		ShoppingCenter center = new ShoppingCenter(3,1,3);
		Stock stock = center.getStock();
		stock.addEntry("foo",2);
		stock.addEntry("bar",10);
		stock.addEntry("meme", 1);
		System.out.println(stock.getRestocks());
		 
		int barDex = stock.findItem("bar") - 1;
		stock.changeCount(barDex, -8);
		System.out.println(stock.getRestocks()); 
		
		center.shoppersAdd(new Shopper("Gregory"));
		center.shoppersAdd(new Shopper("Bane"));
		center.shoppersAdd(new Shopper("Matoro"));
		center.shoppersAdd(new Shopper("Kongu"));
		System.out.println(center.getShopperList().toString()); 
		
		
	}
}
