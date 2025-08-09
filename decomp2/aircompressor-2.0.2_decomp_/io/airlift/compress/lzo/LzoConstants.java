package io.airlift.compress.lzo;

final class LzoConstants {
   public static final byte[] LZOP_MAGIC = new byte[]{-119, 76, 90, 79, 0, 13, 10, 26, 10};
   public static final byte LZO_1X_VARIANT = 1;
   public static final int SIZE_OF_SHORT = 2;
   public static final int SIZE_OF_INT = 4;
   public static final int SIZE_OF_LONG = 8;

   private LzoConstants() {
   }
}
