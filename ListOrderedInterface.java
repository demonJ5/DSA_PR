/*
 * Purpose: Ordered List Interface
 * Status: Complete and tested 
 * Last update: 12/10/18
 * Submitted:  12/10/18
 * Comment: 
 * @author: Joseph Demoneris
 * @version: 2018.10.12
 */
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
