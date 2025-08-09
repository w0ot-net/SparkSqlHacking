package org.apache.arrow.flatbuf;

public final class SparseMatrixCompressedAxis {
   public static final short Row = 0;
   public static final short Column = 1;
   public static final String[] names = new String[]{"Row", "Column"};

   private SparseMatrixCompressedAxis() {
   }

   public static String name(int e) {
      return names[e];
   }
}
