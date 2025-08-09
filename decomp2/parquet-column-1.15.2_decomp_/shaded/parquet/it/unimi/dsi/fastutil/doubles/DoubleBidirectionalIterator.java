package shaded.parquet.it.unimi.dsi.fastutil.doubles;

import shaded.parquet.it.unimi.dsi.fastutil.objects.ObjectBidirectionalIterator;

public interface DoubleBidirectionalIterator extends DoubleIterator, ObjectBidirectionalIterator {
   double previousDouble();

   /** @deprecated */
   @Deprecated
   default Double previous() {
      return this.previousDouble();
   }

   default int back(int n) {
      int i = n;

      while(i-- != 0 && this.hasPrevious()) {
         this.previousDouble();
      }

      return n - i - 1;
   }

   default int skip(int n) {
      return DoubleIterator.super.skip(n);
   }
}
