package org.apache.datasketches.hll;

public enum TgtHllType {
   HLL_4,
   HLL_6,
   HLL_8;

   private static final TgtHllType[] values = values();

   public static final TgtHllType fromOrdinal(int typeId) {
      return values[typeId];
   }
}
