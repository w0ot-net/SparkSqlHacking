package org.apache.arrow.flatbuf;

public final class TimeUnit {
   public static final short SECOND = 0;
   public static final short MILLISECOND = 1;
   public static final short MICROSECOND = 2;
   public static final short NANOSECOND = 3;
   public static final String[] names = new String[]{"SECOND", "MILLISECOND", "MICROSECOND", "NANOSECOND"};

   private TimeUnit() {
   }

   public static String name(int e) {
      return names[e];
   }
}
