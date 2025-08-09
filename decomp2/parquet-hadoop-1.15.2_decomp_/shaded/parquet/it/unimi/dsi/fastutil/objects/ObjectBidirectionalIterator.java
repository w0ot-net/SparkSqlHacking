package shaded.parquet.it.unimi.dsi.fastutil.objects;

import shaded.parquet.it.unimi.dsi.fastutil.BidirectionalIterator;

public interface ObjectBidirectionalIterator extends ObjectIterator, BidirectionalIterator {
   default int back(int n) {
      int i = n;

      while(i-- != 0 && this.hasPrevious()) {
         this.previous();
      }

      return n - i - 1;
   }

   default int skip(int n) {
      return ObjectIterator.super.skip(n);
   }
}
