package org.apache.arrow.flatbuf;

public final class Endianness {
   public static final short Little = 0;
   public static final short Big = 1;
   public static final String[] names = new String[]{"Little", "Big"};

   private Endianness() {
   }

   public static String name(int e) {
      return names[e];
   }
}
