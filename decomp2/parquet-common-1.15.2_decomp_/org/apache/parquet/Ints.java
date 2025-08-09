package org.apache.parquet;

/** @deprecated */
@Deprecated
public final class Ints {
   private Ints() {
   }

   /** @deprecated */
   public static int checkedCast(long value) {
      int valueI = (int)value;
      if ((long)valueI != value) {
         throw new IllegalArgumentException(String.format("Overflow casting %d to an int", value));
      } else {
         return valueI;
      }
   }
}
