package org.apache.arrow.flatbuf;

public final class UnionMode {
   public static final short Sparse = 0;
   public static final short Dense = 1;
   public static final String[] names = new String[]{"Sparse", "Dense"};

   private UnionMode() {
   }

   public static String name(int e) {
      return names[e];
   }
}
