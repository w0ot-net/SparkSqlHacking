package org.apache.arrow.flatbuf;

public final class DictionaryKind {
   public static final short DenseArray = 0;
   public static final String[] names = new String[]{"DenseArray"};

   private DictionaryKind() {
   }

   public static String name(int e) {
      return names[e];
   }
}
