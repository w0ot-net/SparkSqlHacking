package jodd.util.collection;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.NoSuchElementException;

public class ArrayEnumeration implements Enumeration, Serializable {
   private Object[] array;
   private int ndx;
   private int endNdx;

   public ArrayEnumeration(Object[] arr) {
      this(arr, 0, arr.length);
   }

   public ArrayEnumeration(Object[] arr, int offset, int length) {
      this.array = arr;
      this.ndx = offset;
      this.endNdx = offset + length;
   }

   public boolean hasMoreElements() {
      return this.ndx < this.endNdx;
   }

   public Object nextElement() throws NoSuchElementException {
      if (this.ndx < this.endNdx) {
         return this.array[this.ndx++];
      } else {
         throw new NoSuchElementException();
      }
   }
}
