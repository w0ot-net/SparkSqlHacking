package org.apache.spark.unsafe.map;

public interface HashMapGrowthStrategy {
   HashMapGrowthStrategy DOUBLING = new Doubling();

   int nextCapacity(int var1);

   public static class Doubling implements HashMapGrowthStrategy {
      private static final int ARRAY_MAX = 2147483632;

      public int nextCapacity(int currentCapacity) {
         assert currentCapacity > 0;

         int doubleCapacity = currentCapacity * 2;
         return doubleCapacity > 0 && doubleCapacity <= 2147483632 ? doubleCapacity : 2147483632;
      }
   }
}
