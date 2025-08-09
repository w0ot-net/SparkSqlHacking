package org.apache.arrow.flatbuf;

public final class SparseTensorIndex {
   public static final byte NONE = 0;
   public static final byte SparseTensorIndexCOO = 1;
   public static final byte SparseMatrixIndexCSX = 2;
   public static final byte SparseTensorIndexCSF = 3;
   public static final String[] names = new String[]{"NONE", "SparseTensorIndexCOO", "SparseMatrixIndexCSX", "SparseTensorIndexCSF"};

   private SparseTensorIndex() {
   }

   public static String name(int e) {
      return names[e];
   }
}
