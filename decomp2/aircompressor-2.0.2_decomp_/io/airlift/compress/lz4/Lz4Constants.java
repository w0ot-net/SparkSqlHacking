package io.airlift.compress.lz4;

final class Lz4Constants {
   public static final int LAST_LITERAL_SIZE = 5;
   public static final int MIN_MATCH = 4;
   public static final int SIZE_OF_SHORT = 2;
   public static final int SIZE_OF_INT = 4;
   public static final int SIZE_OF_LONG = 8;

   private Lz4Constants() {
   }
}
