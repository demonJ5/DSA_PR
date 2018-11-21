public class ListRA<T> implements ListInterface<T>
{
    protected T[] items; 
    protected int numItems;

    public ListRA()
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
			val += i + " : " + items[i].toString() + " "; 
		}
		return val;
	}
}
