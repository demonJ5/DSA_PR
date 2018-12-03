public class test_order
{
	public static void main(String[] args)
	{
		System.out.println("Testing application.");
		ListOrdered<Shopper> list = new ListOrdered<>();
		list.add(new Shopper("alpha"));
		System.out.println(list.toString());
		list.add(new Shopper("beta"));
		System.out.println(list.toString());
		list.add(new Shopper("000a"));
		System.out.println(list.toString());
		int targfind = list.binSearch(new Shopper("000a"));
		System.out.println(targfind + " found for given 000a");
		list.remove(targfind - 1);
		System.out.println(list.toString());
	}
}
