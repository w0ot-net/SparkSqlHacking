package shaded.parquet.it.unimi.dsi.fastutil.doubles;

import shaded.parquet.it.unimi.dsi.fastutil.BigListIterator;
import shaded.parquet.it.unimi.dsi.fastutil.SafeMath;

public interface DoubleBigListIterator extends DoubleBidirectionalIterator, BigListIterator {
   default void set(double k) {
      throw new UnsupportedOperationException();
   }

   default void add(double k) {
      throw new UnsupportedOperationException();
   }

   /** @deprecated */
   @Deprecated
   default void set(Double k) {
      this.set(k);
   }

   /** @deprecated */
   @Deprecated
   default void add(Double k) {
      this.add(k);
   }

   default long skip(long n) {
      long i = n;

      while(i-- != 0L && this.hasNext()) {
         this.nextDouble();
      }

      return n - i - 1L;
   }

   default long back(long n) {
      long i = n;

      while(i-- != 0L && this.hasPrevious()) {
         this.previousDouble();
      }

      return n - i - 1L;
   }

   default int skip(int n) {
      return SafeMath.safeLongToInt(this.skip((long)n));
   }
}
