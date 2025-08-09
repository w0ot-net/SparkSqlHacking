package org.apache.zookeeper.metrics;

public interface CounterSet {
   default void inc(String key) {
      this.add(key, 1L);
   }

   void add(String var1, long var2);
}
