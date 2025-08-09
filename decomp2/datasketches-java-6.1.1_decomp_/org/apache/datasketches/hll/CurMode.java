package org.apache.datasketches.hll;

enum CurMode {
   LIST,
   SET,
   HLL;

   public static final CurMode[] values = values();

   public static CurMode fromOrdinal(int ordinal) {
      return values[ordinal];
   }
}
