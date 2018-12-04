/**
 * LineManager.java
 * Purpose: Provide an ADT for managing cyclical rotation of checkout lines
 *
 * @author Joseph Demoneris
 * @version 12/3/18
 */
public class LineManager
{
	private ListRA<QueueCRA<Shopper>> openLines; // List of Queues of Shoppers
	int nextOut; // The next line that will check out customers
	
	/*
	 * CONSTRUCTORS
	 */

	/**
	 * Initalize the line manager with a given amount of lines
	 * and specify the line to first accept customers
	 *
	 * @param lines Number of QueueCRA's to instantiate, including the express line
	 * @param nextOut Index of the first line to check out customers
	 */
	public LineManager(int lines, int nextOut)
	{
		openLines = new ListRA<>();
		for (int i = 0; i < lines; i++)
		{
			openLines.add(i, new QueueCRA<>());
		}
		this.nextOut = nextOut;
	}
	
	/**
	 * Initalize the line manager with a given amount of lines
	 * NextOut defaults to 0, the priority line
	 *
	 * @param lines Number of QueueCRA's to instantiate, including the express line
	 */
	public LineManager(int lines)
	{
		openLines = new ListRA<>();
		for (int i = 0; i < lines; i++)
		{
			openLines.add(i, new QueueCRA<>());
		}
		this.nextOut = 0;
	}

	/**
	 * Iterate the active checkout line circularly
	 */
	public void iterateLine()
	{
		nextOut = (nextOut + 1)%openLines.size();
	}

	/*
	 * UNFINISHED
	 */
}
