package org.apache.parquet.hadoop.util.counters.mapred;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.Counters;
import org.apache.hadoop.mapred.Reporter;
import org.apache.parquet.hadoop.util.counters.BenchmarkCounter;
import org.apache.parquet.hadoop.util.counters.CounterLoader;
import org.apache.parquet.hadoop.util.counters.ICounter;

public class MapRedCounterLoader implements CounterLoader {
   private Reporter reporter;
   private Configuration conf;

   public MapRedCounterLoader(Reporter reporter, Configuration conf) {
      this.reporter = reporter;
      this.conf = conf;
   }

   public ICounter getCounterByNameAndFlag(String groupName, String counterName, String counterFlag) {
      if (this.conf.getBoolean(counterFlag, true)) {
         Counters.Counter counter = this.reporter.getCounter(groupName, counterName);
         if (counter != null) {
            return new MapRedCounterAdapter(this.reporter.getCounter(groupName, counterName));
         }
      }

      return new BenchmarkCounter.NullCounter();
   }
}
