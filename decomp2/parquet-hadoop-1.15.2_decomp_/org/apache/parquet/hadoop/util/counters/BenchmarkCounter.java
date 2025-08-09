package org.apache.parquet.hadoop.util.counters;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.parquet.hadoop.util.counters.mapred.MapRedCounterLoader;
import org.apache.parquet.hadoop.util.counters.mapreduce.MapReduceCounterLoader;

public class BenchmarkCounter {
   private static final String ENABLE_BYTES_READ_COUNTER = "parquet.benchmark.bytes.read";
   private static final String ENABLE_BYTES_TOTAL_COUNTER = "parquet.benchmark.bytes.total";
   private static final String ENABLE_TIME_READ_COUNTER = "parquet.benchmark.time.read";
   private static final String COUNTER_GROUP_NAME = "parquet";
   private static final String BYTES_READ_COUNTER_NAME = "bytesread";
   private static final String BYTES_TOTAL_COUNTER_NAME = "bytestotal";
   private static final String TIME_READ_COUNTER_NAME = "timeread";
   private static ICounter bytesReadCounter = new NullCounter();
   private static ICounter totalBytesCounter = new NullCounter();
   private static ICounter timeCounter = new NullCounter();
   private static CounterLoader counterLoader;

   public static void initCounterFromContext(TaskAttemptContext context) {
      counterLoader = new MapReduceCounterLoader(context);
      loadCounters();
   }

   public static void initCounterFromReporter(Reporter reporter, Configuration configuration) {
      counterLoader = new MapRedCounterLoader(reporter, configuration);
      loadCounters();
   }

   private static void loadCounters() {
      bytesReadCounter = getCounterWhenFlagIsSet("parquet", "bytesread", "parquet.benchmark.bytes.read");
      totalBytesCounter = getCounterWhenFlagIsSet("parquet", "bytestotal", "parquet.benchmark.bytes.total");
      timeCounter = getCounterWhenFlagIsSet("parquet", "timeread", "parquet.benchmark.time.read");
   }

   private static ICounter getCounterWhenFlagIsSet(String groupName, String counterName, String counterFlag) {
      return counterLoader.getCounterByNameAndFlag(groupName, counterName, counterFlag);
   }

   public static void incrementTotalBytes(long val) {
      totalBytesCounter.increment(val);
   }

   public static long getTotalBytes() {
      return totalBytesCounter.getCount();
   }

   public static void incrementBytesRead(long val) {
      bytesReadCounter.increment(val);
   }

   public static long getBytesRead() {
      return bytesReadCounter.getCount();
   }

   public static void incrementTime(long val) {
      timeCounter.increment(val);
   }

   public static long getTime() {
      return timeCounter.getCount();
   }

   public static class NullCounter implements ICounter {
      public void increment(long val) {
      }

      public long getCount() {
         return 0L;
      }
   }
}
