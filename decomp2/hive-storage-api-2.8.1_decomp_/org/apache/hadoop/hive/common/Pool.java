package org.apache.hadoop.hive.common;

public interface Pool {
   Object take();

   void offer(Object var1);

   int size();

   default void clear() {
   }

   public interface PoolObjectHelper {
      Object create();

      void resetBeforeOffer(Object var1);
   }
}
