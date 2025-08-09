package shaded.parquet.it.unimi.dsi.fastutil.booleans;

import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;

public interface BooleanBidirectionalIterator extends BooleanIterator, ObjectBidirectionalIterator {
   boolean previousBoolean();

   /** @deprecated */
   @Deprecated
   default Boolean previous() {
      return this.previousBoolean();
   }

   default int back(int n) {
      int i = n;

      while(i-- != 0 && this.hasPrevious()) {
         this.previousBoolean();
      }

      return n - i - 1;
   }

   default int skip(int n) {
      return BooleanIterator.super.skip(n);
   }
}
