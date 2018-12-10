/*
 * Purpose: Circular Arraybased Queue Data Structure
 * Status: Complete and thoroughly tested
 * Last update: 12/5/18
 * Submitted: 12/10/18
 * Comment: Nil
 * @author: Joseph Demoneris
 * @version: 2018.5.12
 */
/**
 * QueueCRA.java
 * Purpose: Provide a Queue data structure with a circular, resizable array-
 * 			based implementation.
 * @author Joseph Demoneris
 * @version 12/5/18
 */
public class QueueCRA<T> implements QueueInterface<T>
{
	protected T[] items;		// Array of items
	protected int numItems;	// Number of items
	protected int front;		// Front index
	protected int back;		// Back index

	/**
	 * Initialize the queue with an internal size of 3.
	 */
	public QueueCRA()
	{
		items = (T[]) new Object[3];
		numItems = 0;
		front = 0;
		back = 0;
	}
	
	/**
	 * Return whether or not the queue is empty.
	 *
	 * @return boolean true if empty
	 */
	public boolean isEmpty()
	{
		boolean val = true;
		if (numItems != 0){val = false;}
		return val;
	}

	/**
	 * Return the size of the queue.
	 *
	 * @return int amount of items stored in the queue
	 */
	public int size()
	{
		return numItems;
	}

	/**
	 * Add the specified object to the back of the queue.
	 *
	 * @param newItem Generic typed item to be enqueued
	 */
	public void enqueue(T newItem) throws QueueException
	{
		if (numItems == items.length)		// If the array is full, resize
		{
			resize();
		}
		items[back] = newItem;				// Add to the back
		back = (back + 1)%items.length;		// Circularly iterate backpos
		numItems++;
	}

	/**
	 * Dequeue and retrieve the item at the front of the queue.
	 *
	 * @return Generic typed object at the front of the queue
	 */
	public T dequeue() throws QueueException
	{
		if (items[front] == null) {throw new QueueException("Nothing to dequeue at the front.");}
		T val = items[front];				// Fetch the front
		items[front] = null;				// Nullify the front
		front = (front + 1)%items.length;	// Circularly iterate frontpos
		numItems--;
		return val;
	}

	/**
	 * Throw away the contents of the queue.
	 */
	public void dequeueAll()
	{
		front = 0;
		back = 0;
		numItems = 0;
		items = (T[]) new Object[3];		// Reinit new array
	}

	/**
	 * Retrieve the object at the front of the queue without
	 */
	public T peek() throws QueueException
	{
		if (items[front] == null) {throw new QueueException("Nothing to peek at the back.");}
		return items[front];
	}

	protected void resize()
	{
		// Make a new array 
		T[] newArray =  (T[]) new Object[(int) Math.ceil(items.length * 1.5)];
		for (int i = 0; i < items.length; i++)	// Iterate through the entire length of the
		{										// array, but calculate the circular index
			int n = (front + i)%items.length;	// based on frontpos and current i
			newArray[i] = items[n];
		}
		front = 0;
		back = numItems;
		items = newArray;
	}

	/**
	 * Retrieve a string in which each item of the queue is listed
	 * with their own toString() format on a seperate line in the
	 * order they are queued
	 *
	 * Numbers used are 1...n
	 * 				not 0...n-1
	 *
	 * @return String from 1st in queue to last
	 */
	public String toString()
	{
		String val = "";
		for (int i = 0; i < numItems; i++)
		{
			int n = (front + i)%items.length;
			val += (i + 1) + " : " + items[n].toString() + "\n";
		}
		return val;
	}

}

