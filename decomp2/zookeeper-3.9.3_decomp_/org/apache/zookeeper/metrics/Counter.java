package org.apache.zookeeper.metrics;

public interface Counter {
   default void inc() {
      this.add(1L);
   }

   void add(long var1);

   long get();
}
