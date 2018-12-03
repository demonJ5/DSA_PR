// ********************************************************
// Interface ListInterface for the ADT list.
// *********************************************************
public interface ListOrderedInterface<T> 
{
  boolean isEmpty();
  int size();
  int add(T item) 
                  throws ListIndexOutOfBoundsException;
  T get(int index) 
                    throws ListIndexOutOfBoundsException;
  void remove(int index) 
                     throws ListIndexOutOfBoundsException;
  void removeAll();
}  // end ListInterface
