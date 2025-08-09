package org.apache.spark.unsafe;

public class UnsafeAlignedOffset {
   private static final int UAO_SIZE = Platform.unaligned() ? 4 : 8;
   private static int TEST_UAO_SIZE = 0;

   public static void setUaoSize(int size) {
      assert size == 0 || size == 4 || size == 8;

      TEST_UAO_SIZE = size;
   }

   public static int getUaoSize() {
      return TEST_UAO_SIZE == 0 ? UAO_SIZE : TEST_UAO_SIZE;
   }

   public static int getSize(Object object, long offset) {
      int var10000;
      switch (getUaoSize()) {
         case 4 -> var10000 = Platform.getInt(object, offset);
         case 8 -> var10000 = (int)Platform.getLong(object, offset);
         default -> throw new AssertionError("Illegal UAO_SIZE");
      }

      return var10000;
   }

   public static void putSize(Object object, long offset, int value) {
      switch (getUaoSize()) {
         case 4 -> Platform.putInt(object, offset, value);
         case 8 -> Platform.putLong(object, offset, (long)value);
         default -> throw new AssertionError("Illegal UAO_SIZE");
      }

   }
}
