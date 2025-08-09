package com.ibm.icu.util;

public final class NoUnit {
   public static final MeasureUnit BASE = null;
   public static final MeasureUnit PERCENT;
   public static final MeasureUnit PERMILLE;

   private NoUnit() {
   }

   static {
      PERCENT = MeasureUnit.PERCENT;
      PERMILLE = MeasureUnit.PERMILLE;
   }
}
