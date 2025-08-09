package shaded.parquet.it.unimi.dsi.fastutil.floats;

import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;

public interface FloatBidirectionalIterator extends FloatIterator, ObjectBidirectionalIterator {
   float previousFloat();

   /** @deprecated */
   @Deprecated
   default Float previous() {
      return this.previousFloat();
   }

   default int back(int n) {
      int i = n;

      while(i-- != 0 && this.hasPrevious()) {
         this.previousFloat();
      }

      return n - i - 1;
   }

   default int skip(int n) {
      return FloatIterator.super.skip(n);
   }
}
