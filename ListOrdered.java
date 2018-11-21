public class ListOrdered<T> implements ListOrderedInterface<T>
{
    protected T[] items; 
    protected int numItems;

    public ListOrdered()
    {
        items = (T[]) new Object[3];
        numItems = 0;
    } 

    public boolean isEmpty()
    {
        return (numItems == 0);
    }

    public int size()
    {
        return numItems;
    }

    public void removeAll()
    {
        items = (T[]) new Object[3];
        numItems = 0;
    }

    public void add(T item)
    throws  ListIndexOutOfBoundsException
    {
		// Check add
		//int determinant = search(item);
		int determinant = binSearch(item);
		if (determinant < 0) // If there is not this item already
		{
			// Convert determinant into an index
			int index = -(determinant + 1);
			// Add confirmed
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
		else
		{
			System.out.println("Duplicate value!");
		}
    }

	public int binSearch(T key)
	{
		int mid = 0;
		int low = 0;
		int high = numItems - 1;
		int result = -1;
		if(numItems > 0)
		{
			mid = (high + low) / 2;
			//System.out.println("[" + low + "," + mid + "," + high + "]");
			while (high > low)
			{
				if (key.compareTo(items[mid]) <= 0)
				{
					//System.out.println(key + " is less than or equal to " + items[mid]);
					high = mid;
				}
				else
				{
					//System.out.println(key + " is greater than " + items[mid]);
					low = mid + 1;
				}
				mid = (high + low) / 2;
				//System.out.println("[" + low + "," + mid + "," + high + "]");
			}
			if (key.compareTo(items[mid]) == 0)
			{
				result = mid + 1;
			}
			else if (key.compareTo(items[mid]) < 0)
			{
				result = -(mid + 1);
			}
			else
			{
				result = -(mid + 2);
			}
			//System.out.println(result);
		}
		else
		{
			result = -1;
		}
		 return result;
	}

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
