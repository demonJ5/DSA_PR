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
