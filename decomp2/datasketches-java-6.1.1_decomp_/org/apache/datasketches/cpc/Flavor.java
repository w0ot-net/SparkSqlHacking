package org.apache.datasketches.cpc;

enum Flavor {
   EMPTY,
   SPARSE,
   HYBRID,
   PINNED,
   SLIDING;

   private static Flavor[] fmtArr = (Flavor[])Flavor.class.getEnumConstants();

   static Flavor ordinalToFlavor(int ordinal) {
      return fmtArr[ordinal];
   }
}
