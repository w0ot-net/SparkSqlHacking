package org.apache.arrow.flatbuf;

public final class CompressionType {
   public static final byte LZ4_FRAME = 0;
   public static final byte ZSTD = 1;
   public static final String[] names = new String[]{"LZ4_FRAME", "ZSTD"};

   private CompressionType() {
   }

   public static String name(int e) {
      return names[e];
   }
}
