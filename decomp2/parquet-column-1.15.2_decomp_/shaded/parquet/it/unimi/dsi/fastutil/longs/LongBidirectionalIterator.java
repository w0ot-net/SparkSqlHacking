package shaded.parquet.it.unimi.dsi.fastutil.longs;

import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;

public interface LongBidirectionalIterator extends LongIterator, ObjectBidirectionalIterator {
   long previousLong();

   /** @deprecated */
   @Deprecated
   default Long previous() {
      return this.previousLong();
   }

   default int back(int n) {
      int i = n;

      while(i-- != 0 && this.hasPrevious()) {
         this.previousLong();
      }

      return n - i - 1;
   }

   default int skip(int n) {
      return LongIterator.super.skip(n);
   }
}
