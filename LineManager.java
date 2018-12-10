/*
 * Purpose: ADT for managing Shopper Queues
 * Status: Complete and tested 
 * Last update: 12/10/18
 * Submitted:  12/10/18
 * Comment: 
 * @author: Joseph Demoneris
 * @version: 2018.10.12
 */
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
	// index 0 = ALWAYS PRIORITY
	private int nextOut; // The next line that will check out customers
	
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
	 * This is used internally only.
	 */
	private void iterateLine()
	{
		nextOut = (nextOut + 1)%openLines.size();
	}

	/**
	 * Retrieve the openLines index of the non-express queue with 
	 * the shortest number of currently waiting customers
	 * @return int index of shortest queue, or, 
	 * 			if no regular queues exist, -1
	 */
	private int getIndexShortestRegular()
	{
		int size = openLines.size();
		int retVal = -1;
		if (size > 1)
		{
			// Start with the first regular line
			QueueCRA<Shopper> smallestQueue = openLines.get(1);
			// ... and its index
			int smallestIndex = 1;
			int i = 1;
			do
			{
				// Continually check for the smallest
				// Update both the queue and index as necessary
				if (openLines.get(i).size() < smallestQueue.size())
				{
					smallestQueue = openLines.get(i);
					smallestIndex = i;
				}
				i++;
			} while (i < size);
			retVal = smallestIndex;
		}
		else
		{
			// A size of 1 or less means that no lines exist
			// or only the priority line does
			retVal = -1;
		}
		return retVal;
	}

	/**
	 * Given a shopper, put them into the appropriate queue
	 *
	 * If the shopper has less than or equal to 4 items, they 
	 * are elligible for the priority queue, but may still
	 * pick a non-priority queue if it is adequately shorter
	 *
	 * @param Shopper The shopper who is ready to enter a queue
	 */
	public void acceptShopper(Shopper selected)
	{
		int shopperItems = selected.getItems();
		/*
		 * Case 1: Shopper is elligible for priority
		 * may enter priority or non-priority depending
		 * on their size
		 */
		if (shopperItems <= 4)
		{
			// the priority line is always index 0
			int prioritySize = openLines.get(0).size();
			int smallestIndex = getIndexShortestRegular();
			int smallestSize = openLines.get(smallestIndex).size();	
			/*
			 * Subcase 1: Priority is 2x smallest regular
			 */
			if (prioritySize > 2 * smallestSize)
			{
				openLines.get(smallestIndex).enqueue(selected);
			}
			/*
			 * Subcase 2: Priority is less than 2x smallest
			 */
			else
			{
				openLines.get(0).enqueue(selected);
			}
		}
		/*
		 * Case 2: Shopper is inelligible for priority
		 * he or she will always go to the shortest
		 * non-priority line
		 */
		else
		{
			int smallestIndex = getIndexShortestRegular();
			openLines.get(smallestIndex).enqueue(selected);
		}

	}

	/**
	 * Note: THIS CLASS DIFFERS IN <RETURN> FROM THE DESIGN
	 *
	 * Dequeue a shopper from the queue of the given index
	 *
	 * This is intended to be used after calling checkAllLines()
	 * to see 1) if a customer is ready to be checked out and
	 * 2) to see WHICH customer needs to be checked out based
	 * on what queue they are in
	 *
	 * @param int The index of the queue which is to dequeue
	 * 				a shopper
	 * @return Shopper the shopper at the end of the line
	 */
	public Shopper removeShopper(int index)
	{
		return openLines.get(index).dequeue(); 
	}
	
	/**
	 * Iterate through all the lines in order until one is
	 * found that has a customer to be dealt with
	 * This is the core method of the LineManager class
	 *
	 * @return int -1 if there is nobody on line, otherwise
	 * 			the openLines index of the queue which should
	 * 			check out a customer next (handled by removeShopper)
	 */
	public int checkAllLines()
	{
		int retVal = -1;
		int numLines = openLines.size();
		boolean found = false;
		/*
		 * Clarification: While "i" is an iterator for the
		 * loop, it is merely used to ensure that we do not
		 * check all the queues multiple times. The data field
		 * "nextOut" is the integer that points to the
		 * index of the queue in openLines, and is iterated
		 * circularly by iterateLine()
		 */
		for (int i = 0; i < numLines && !found; i++)
		{
			if (openLines.get(nextOut).size() > 0)
			{
				found = true;
				retVal = nextOut;
			}
			iterateLine();
		}
		return retVal;
	}

	/**
	 * Retrieve the string representation of the LineManager
	 *
	 * @return String representation of all the queues
	 */
	public String toString()
	{
		String val = "";
		int numLines = openLines.size();
		if (numLines > 0)
		{
			// Decide whether to display priority
			if (openLines.get(0).size() > 0)
			{
				val += "Priority Queue Readout\n";
				val += openLines.get(0).toString();
			}
			else
			{
				val += "Priority Queue is empty\n";
			}
			// Decide whether to display the rest`:w
			for (int i = 1; i < numLines; i++)
			{
				if (openLines.get(i).size() > 0)
				{
					val += "Regular Queue Number " + i + "\n";
					val += openLines.get(i).toString();
				}
				else
				{
					val += "Regular Queue Number " + i + " is empty\n";
				}
			}
		}
		else
		{
			System.out.println("Something went wrong and though LineManager initialized openLines List, it failed to add queues if any were requested.");
		}
		return val;
	}
}
