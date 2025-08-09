package org.apache.arrow.flatbuf;

public final class IntervalUnit {
   public static final short YEAR_MONTH = 0;
   public static final short DAY_TIME = 1;
   public static final short MONTH_DAY_NANO = 2;
   public static final String[] names = new String[]{"YEAR_MONTH", "DAY_TIME", "MONTH_DAY_NANO"};

   private IntervalUnit() {
   }

   public static String name(int e) {
      return names[e];
   }
}
