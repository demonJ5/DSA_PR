/*
 * Purpose: List ADT via Resizable Array
 * Status: Complete and tested 
 * Last update: 12/10/18
 * Submitted:  12/10/18
 * Comment: 
 * @author: Joseph Demoneris
 * @version: 2018.10.12
 */
/**
 * ListRA.java
 * Purpose: Manage a list data structure with a resizable array framwork.
 * @author Joseph Demoneris
 * @version 12/10/18
 */
public class ListRA<T> implements ListInterface<T>
{
    protected T[] items; 
    protected int numItems;

	/**
	 * Construct a new ListRA with a default array size of 3.
	 */
    public ListRA()
    {
        items = (T[]) new Object[3];
        numItems = 0;
    } 

	/**
	 * Returns whether or not the list is empty.
	 *
	 * @return boolean true if empty
	 */
    public boolean isEmpty()
    {
        return (numItems == 0);
    }

	/**
	 * Returns the amount of items in the list.
	 *
	 * @return int amount of items stored
	 */
    public int size()
    {
        return numItems;
    }

	/**
	 * Clear the current list by re-instantiating the internal reference.
	 */
    public void removeAll()
    {
        items = (T[]) new Object[3];
        numItems = 0;
    }

	/**
	 * Add a new item to the list in the specified index, offsetting if
	 * necessary.
	 *
	 * @param index The index to receive the item
	 * @param item The generic typed item to be inserted
	 */
    public void add(int index, T item)
    throws  ListIndexOutOfBoundsException
    {
        if (numItems == items.length){resize();}
		if (index >= 0 && index < numItems)
        {
            for (int pos = numItems-1; pos >= index; pos--)
            {
                items[pos+1] = items[pos];
            }
            items[index] = item;
            numItems++;
        }
		else if (index == numItems)
		{
			items[index] = item;
			numItems++;
		}
        else
        {
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on add");
        }
    }

	/**
	 * Retrieve the stored item from the given index.
	 *
	 * @param index The int index to be retrieved
	 * @return The generic type residing within the specified index
	 */
    public T get(int index)
    throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < numItems)
        {
            return items[index];
        }
        else
        {
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on get");
        }
    }

	/**
	 * Delete the supplied index from the list, shifting as necessary
	 *
	 * @param index int index to be deleted
	 */
    public void remove(int index)
    throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < numItems - 1)
        {
            for (int pos = index+1; pos < numItems; pos++)
            {
                items[pos-1] = items[pos];
				items[pos] = null;
            }
            numItems--;
        }
		else if (index == numItems - 1)
		{
			items[index] = null;
			numItems--;
		}
        else
        {
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on remove");
        }
    }

	/**
	 * Internal method responsible for scaling the internal array by 150%
	 * whenever the array reaches its max capacity.
	 */
	private void resize()
	{
		if (items.length == numItems)
		{
			T[] newArray = (T[]) new Object[(int) Math.ceil(numItems * 1.5)];
			for(int i = 0; i < items.length; i++)
			{
				newArray[i] = items[i];
			}
			items = newArray;
		}
	}

	/**
	 * Retrieve a String representation of the list
	 * in the format 1...n
	 *
	 * @return String List with each item on a new line
	 */
	public String toString()
	{
		String val = "";
		int size = numItems;
		for (int i = 0; i < size; i++)
		{
			val += (i + 1) + " : " + items[i].toString() + "\n"; 
		}
		return val;
	}
}
