package org.apache.parquet.hadoop.util.counters.mapreduce;

import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.parquet.hadoop.util.ContextUtil;
import org.apache.parquet.hadoop.util.counters.BenchmarkCounter;
import org.apache.parquet.hadoop.util.counters.CounterLoader;
import org.apache.parquet.hadoop.util.counters.ICounter;

public class MapReduceCounterLoader implements CounterLoader {
   private TaskAttemptContext context;

   public MapReduceCounterLoader(TaskAttemptContext context) {
      this.context = context;
   }

   public ICounter getCounterByNameAndFlag(String groupName, String counterName, String counterFlag) {
      return (ICounter)(ContextUtil.getConfiguration(this.context).getBoolean(counterFlag, true) ? new MapReduceCounterAdapter(ContextUtil.getCounter(this.context, groupName, counterName)) : new BenchmarkCounter.NullCounter());
   }
}
