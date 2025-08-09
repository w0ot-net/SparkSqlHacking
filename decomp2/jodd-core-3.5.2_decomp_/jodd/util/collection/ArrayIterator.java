package jodd.util.collection;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator implements Iterator, Serializable {
   private Object[] array;
   private int ndx;
   private int endNdx;

   public ArrayIterator(Object[] array) {
      this.array = array;
      this.ndx = 0;
      this.endNdx = array.length;
   }

   public ArrayIterator(Object[] array, int offset, int len) {
      this.array = array;
      this.ndx = offset;
      this.endNdx = offset + len;
   }

   public boolean hasNext() {
      return this.ndx < this.endNdx;
   }

   public Object next() throws NoSuchElementException {
      if (this.ndx < this.endNdx) {
         ++this.ndx;
         return this.array[this.ndx - 1];
      } else {
         throw new NoSuchElementException();
      }
   }

   public void remove() throws UnsupportedOperationException {
      throw new UnsupportedOperationException();
   }
}
