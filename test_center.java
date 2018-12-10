public class test_center
{
	public static void main(String[] args)
	{
		ShoppingCenter center = new ShoppingCenter(3,1,3);
		Stock stock = center.getStock();
		stock.addEntry("foo",2);
		stock.addEntry("bar",10);
		stock.addEntry("meme", 1);
		System.out.println(stock.getRestocks()); 
	}
}
