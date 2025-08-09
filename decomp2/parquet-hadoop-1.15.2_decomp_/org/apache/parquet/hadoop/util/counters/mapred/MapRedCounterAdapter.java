package org.apache.parquet.hadoop.util.counters.mapred;

import org.apache.hadoop.mapred.Counters;
import org.apache.parquet.hadoop.util.counters.ICounter;

public class MapRedCounterAdapter implements ICounter {
   private Counters.Counter adaptee;

   public MapRedCounterAdapter(Counters.Counter adaptee) {
      this.adaptee = adaptee;
   }

   public void increment(long val) {
      this.adaptee.increment(val);
   }

   public long getCount() {
      return this.adaptee.getCounter();
   }
}
