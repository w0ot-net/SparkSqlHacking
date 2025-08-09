package org.apache.arrow.flatbuf;

public final class Precision {
   public static final short HALF = 0;
   public static final short SINGLE = 1;
   public static final short DOUBLE = 2;
   public static final String[] names = new String[]{"HALF", "SINGLE", "DOUBLE"};

   private Precision() {
   }

   public static String name(int e) {
      return names[e];
   }
}
