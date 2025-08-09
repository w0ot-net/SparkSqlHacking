package org.apache.arrow.flatbuf;

public final class BodyCompressionMethod {
   public static final byte BUFFER = 0;
   public static final String[] names = new String[]{"BUFFER"};

   private BodyCompressionMethod() {
   }

   public static String name(int e) {
      return names[e];
   }
}
