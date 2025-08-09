package pl.edu.icm.jlargearrays;

public class MemoryCounter {
   private static long counter = 0L;

   private MemoryCounter() {
   }

   public static long getCounter() {
      return counter;
   }

   public static void increaseCounter(long x) {
      counter += x;
   }

   public static void decreaseCounter(long x) {
      counter -= x;
      if (counter < 0L) {
         counter = 0L;
      }

   }
}
