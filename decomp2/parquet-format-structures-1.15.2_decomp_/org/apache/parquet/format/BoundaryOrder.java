package org.apache.parquet.format;

import shaded.parquet.org.apache.thrift.TEnum;
import shaded.parquet.org.apache.thrift.annotation.Nullable;

public enum BoundaryOrder implements TEnum {
   UNORDERED(0),
   ASCENDING(1),
   DESCENDING(2);

   private final int value;

   private BoundaryOrder(int value) {
      this.value = value;
   }

   public int getValue() {
      return this.value;
   }

   @Nullable
   public static BoundaryOrder findByValue(int value) {
      switch (value) {
         case 0:
            return UNORDERED;
         case 1:
            return ASCENDING;
         case 2:
            return DESCENDING;
         default:
            return null;
      }
   }
}
