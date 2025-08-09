package org.sparkproject.jetty.util.thread;

import java.util.concurrent.BlockingQueue;
import org.sparkproject.jetty.util.BlockingArrayQueue;
import org.sparkproject.jetty.util.NanoTime;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.annotation.ManagedOperation;
import org.sparkproject.jetty.util.statistic.CounterStatistic;
import org.sparkproject.jetty.util.statistic.SampleStatistic;

@ManagedObject
public class MonitoredQueuedThreadPool extends QueuedThreadPool {
   private final CounterStatistic queueStats;
   private final SampleStatistic queueLatencyStats;
   private final SampleStatistic taskLatencyStats;
   private final CounterStatistic threadStats;

   public MonitoredQueuedThreadPool() {
      this(256);
   }

   public MonitoredQueuedThreadPool(int maxThreads) {
      this(maxThreads, maxThreads, 86400000, new BlockingArrayQueue(maxThreads, 256));
   }

   public MonitoredQueuedThreadPool(int maxThreads, int minThreads, int idleTimeOut, BlockingQueue queue) {
      super(maxThreads, minThreads, idleTimeOut, queue);
      this.queueStats = new CounterStatistic();
      this.queueLatencyStats = new SampleStatistic();
      this.taskLatencyStats = new SampleStatistic();
      this.threadStats = new CounterStatistic();
      this.addBean(this.queueStats);
      this.addBean(this.queueLatencyStats);
      this.addBean(this.taskLatencyStats);
      this.addBean(this.threadStats);
   }

   public void execute(final Runnable job) {
      this.queueStats.increment();
      final long begin = NanoTime.now();
      super.execute(new Runnable() {
         public void run() {
            long queueLatency = NanoTime.since(begin);
            MonitoredQueuedThreadPool.this.queueStats.decrement();
            MonitoredQueuedThreadPool.this.threadStats.increment();
            MonitoredQueuedThreadPool.this.queueLatencyStats.record(queueLatency);
            long start = NanoTime.now();
            boolean var11 = false;

            try {
               var11 = true;
               job.run();
               var11 = false;
            } finally {
               if (var11) {
                  long taskLatency = NanoTime.since(start);
                  MonitoredQueuedThreadPool.this.threadStats.decrement();
                  MonitoredQueuedThreadPool.this.taskLatencyStats.record(taskLatency);
               }
            }

            long taskLatency = NanoTime.since(start);
            MonitoredQueuedThreadPool.this.threadStats.decrement();
            MonitoredQueuedThreadPool.this.taskLatencyStats.record(taskLatency);
         }

         public String toString() {
            return job.toString();
         }
      });
   }

   @ManagedOperation(
      value = "resets the statistics",
      impact = "ACTION"
   )
   public void reset() {
      this.queueStats.reset();
      this.queueLatencyStats.reset();
      this.taskLatencyStats.reset();
      this.threadStats.reset(0L);
   }

   @ManagedAttribute("the number of tasks executed")
   public long getTasks() {
      return this.taskLatencyStats.getCount();
   }

   @ManagedAttribute("the maximum number of busy threads")
   public int getMaxBusyThreads() {
      return (int)this.threadStats.getMax();
   }

   @ManagedAttribute("the maximum task queue size")
   public int getMaxQueueSize() {
      return (int)this.queueStats.getMax();
   }

   @ManagedAttribute("the average time a task remains in the queue, in nanoseconds")
   public long getAverageQueueLatency() {
      return (long)this.queueLatencyStats.getMean();
   }

   @ManagedAttribute("the maximum time a task remains in the queue, in nanoseconds")
   public long getMaxQueueLatency() {
      return this.queueLatencyStats.getMax();
   }

   @ManagedAttribute("the average task execution time, in nanoseconds")
   public long getAverageTaskLatency() {
      return (long)this.taskLatencyStats.getMean();
   }

   @ManagedAttribute("the maximum task execution time, in nanoseconds")
   public long getMaxTaskLatency() {
      return this.taskLatencyStats.getMax();
   }
}
