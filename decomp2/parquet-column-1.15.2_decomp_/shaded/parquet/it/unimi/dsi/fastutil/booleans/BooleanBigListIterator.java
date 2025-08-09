package shaded.parquet.it.unimi.dsi.fastutil.booleans;

import shaded.parquet.it.unimi.dsi.fastutil.BigListIterator;
import shaded.parquet.it.unimi.dsi.fastutil.SafeMath;

public interface BooleanBigListIterator extends BooleanBidirectionalIterator, BigListIterator {
   default void set(boolean k) {
      throw new UnsupportedOperationException();
   }

   default void add(boolean k) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default void set(Boolean k) {
      this.set(k);
   }

   /** @deprecated */
   @Deprecated
   default void add(Boolean k) {
      this.add(k);
   }

   default long skip(long n) {
      long i = n;

      while(i-- != 0L && this.hasNext()) {
         this.nextBoolean();
      }

      return n - i - 1L;
   }

   default long back(long n) {
      long i = n;

      while(i-- != 0L && this.hasPrevious()) {
         this.previousBoolean();
      }

      return n - i - 1L;
   }

   default int skip(int n) {
      return SafeMath.safeLongToInt(this.skip((long)n));
   }
}
