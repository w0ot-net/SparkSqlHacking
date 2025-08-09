package org.apache.parquet.hadoop.util.counters;

public interface ICounter {
   void increment(long var1);

   long getCount();
}
