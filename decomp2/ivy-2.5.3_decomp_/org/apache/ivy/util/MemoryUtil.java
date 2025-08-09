package org.apache.ivy.util;

public final class MemoryUtil {
   private static final int SAMPLING_SIZE = 100;
   private static final int SLEEP_TIME = 100;

   private MemoryUtil() {
   }

   public static long sizeOf(Class clazz) {
      long size = 0L;
      Object[] objects = new Object[100];

      try {
         clazz.newInstance();
         long startingMemoryUse = getUsedMemory();

         for(int i = 0; i < objects.length; ++i) {
            objects[i] = clazz.newInstance();
         }

         long endingMemoryUse = getUsedMemory();
         float approxSize = (float)(endingMemoryUse - startingMemoryUse) / (float)objects.length;
         size = (long)Math.round(approxSize);
      } catch (Exception e) {
         Message.warn("Couldn't instantiate " + clazz, e);
      }

      return size;
   }

   public static long getUsedMemory() {
      gc();
      long totalMemory = Runtime.getRuntime().totalMemory();
      gc();
      long freeMemory = Runtime.getRuntime().freeMemory();
      return totalMemory - freeMemory;
   }

   private static void gc() {
      try {
         System.gc();
         Thread.sleep(100L);
         System.runFinalization();
         Thread.sleep(100L);
         System.gc();
         Thread.sleep(100L);
         System.runFinalization();
         Thread.sleep(100L);
      } catch (Exception e) {
         Message.debug((Throwable)e);
      }

   }

   public static void main(String[] args) throws ClassNotFoundException {
      System.out.println(sizeOf(Class.forName(args[0])));
   }
}
