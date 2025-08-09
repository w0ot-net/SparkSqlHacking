package org.apache.derby.impl.sql.catalog;

import org.apache.derby.catalog.SequencePreallocator;

public class SequenceRange implements SequencePreallocator {
   private static final int DEFAULT_PREALLOCATION_COUNT = 100;
   private int _rangeSize;

   public SequenceRange() {
      this(100);
   }

   public SequenceRange(int var1) {
      if (var1 <= 0) {
         var1 = 100;
      }

      this._rangeSize = var1;
   }

   public int nextRangeSize(String var1, String var2) {
      return this._rangeSize;
   }
}
