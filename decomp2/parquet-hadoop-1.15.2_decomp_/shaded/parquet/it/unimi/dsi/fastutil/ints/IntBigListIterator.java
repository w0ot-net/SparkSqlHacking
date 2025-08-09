package shaded.parquet.it.unimi.dsi.fastutil.ints;

import shaded.parquet.it.unimi.dsi.fastutil.BigListIterator;
import shaded.parquet.it.unimi.dsi.fastutil.SafeMath;

public interface IntBigListIterator extends IntBidirectionalIterator, BigListIterator {
   default void set(int k) {
      throw new UnsupportedOperationException();
   }

   default void add(int k) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default void set(Integer k) {
      this.set(k);
   }

   /** @deprecated */
   @Deprecated
   default void add(Integer k) {
      this.add(k);
   }

   default long skip(long n) {
      long i = n;

      while(i-- != 0L && this.hasNext()) {
         this.nextInt();
      }

      return n - i - 1L;
   }

   default long back(long n) {
      long i = n;

      while(i-- != 0L && this.hasPrevious()) {
         this.previousInt();
      }

      return n - i - 1L;
   }

   default int skip(int n) {
      return SafeMath.safeLongToInt(this.skip((long)n));
   }
}
