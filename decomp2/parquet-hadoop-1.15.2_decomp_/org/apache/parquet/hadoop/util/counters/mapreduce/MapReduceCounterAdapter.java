package org.apache.parquet.hadoop.util.counters.mapreduce;

import org.apache.hadoop.mapreduce.Counter;
import org.apache.parquet.hadoop.util.ContextUtil;
import org.apache.parquet.hadoop.util.counters.ICounter;

public class MapReduceCounterAdapter implements ICounter {
   private Counter adaptee;

   public MapReduceCounterAdapter(Counter adaptee) {
      this.adaptee = adaptee;
   }

   public void increment(long val) {
      ContextUtil.incrementCounter(this.adaptee, val);
   }

   public long getCount() {
      return this.adaptee.getValue();
   }
}
