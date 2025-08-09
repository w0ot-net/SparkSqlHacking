package shaded.parquet.it.unimi.dsi.fastutil.objects;

import java.util.Iterator;

public interface ObjectIterator extends Iterator {
   default int skip(int n) {
      if (n < 0) {
         throw new IllegalArgumentException("Argument must be nonnegative: " + n);
      } else {
         int i = n;

         while(i-- != 0 && this.hasNext()) {
            this.next();
         }

         return n - i - 1;
      }
   }
}
