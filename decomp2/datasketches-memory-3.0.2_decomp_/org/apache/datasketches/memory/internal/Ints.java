package org.apache.datasketches.memory.internal;

public final class Ints {
   private Ints() {
   }

   public static int checkedCast(long v) {
      int result = (int)v;
      if ((long)result != v) {
         throw new IllegalArgumentException("Out of range: " + v);
      } else {
         return result;
      }
   }
}
