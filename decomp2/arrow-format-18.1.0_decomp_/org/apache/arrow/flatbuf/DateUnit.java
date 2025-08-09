package org.apache.arrow.flatbuf;

public final class DateUnit {
   public static final short DAY = 0;
   public static final short MILLISECOND = 1;
   public static final String[] names = new String[]{"DAY", "MILLISECOND"};

   private DateUnit() {
   }

   public static String name(int e) {
      return names[e];
   }
}
