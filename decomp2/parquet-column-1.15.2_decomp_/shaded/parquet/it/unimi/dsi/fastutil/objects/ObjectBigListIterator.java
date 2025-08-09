package shaded.parquet.it.unimi.dsi.fastutil.objects;

import shaded.parquet.it.unimi.dsi.fastutil.BigListIterator;
import shaded.parquet.it.unimi.dsi.fastutil.SafeMath;

public interface ObjectBigListIterator extends ObjectBidirectionalIterator, BigListIterator {
   default void set(Object k) {
      throw new UnsupportedOperationException();
   }

   default void add(Object k) {
      throw new UnsupportedOperationException();
   }

   default long skip(long n) {
      long i = n;

      while(i-- != 0L && this.hasNext()) {
         this.next();
      }

      return n - i - 1L;
   }

   default long back(long n) {
      long i = n;

      while(i-- != 0L && this.hasPrevious()) {
         this.previous();
      }

      return n - i - 1L;
   }

   default int skip(int n) {
      return SafeMath.safeLongToInt(this.skip((long)n));
   }
}
