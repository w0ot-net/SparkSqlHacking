package com.codahale.metrics.jvm;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.MetricSet;
import java.lang.Thread.State;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ThreadStatesGaugeSet implements MetricSet {
   private static final int STACK_TRACE_DEPTH = 0;
   private final ThreadMXBean threads;
   private final ThreadDeadlockDetector deadlockDetector;

   public ThreadStatesGaugeSet() {
      this(ManagementFactory.getThreadMXBean(), new ThreadDeadlockDetector());
   }

   public ThreadStatesGaugeSet(ThreadMXBean threads, ThreadDeadlockDetector deadlockDetector) {
      this.threads = threads;
      this.deadlockDetector = deadlockDetector;
   }

   public Map getMetrics() {
      Map<String, Metric> gauges = new HashMap();

      for(Thread.State state : State.values()) {
         gauges.put(MetricRegistry.name(state.toString().toLowerCase(), new String[]{"count"}), (Gauge)() -> this.getThreadCount(state));
      }

      ThreadMXBean var10002 = this.threads;
      Objects.requireNonNull(var10002);
      gauges.put("count", var10002::getThreadCount);
      var10002 = this.threads;
      Objects.requireNonNull(var10002);
      gauges.put("daemon.count", var10002::getDaemonThreadCount);
      var10002 = this.threads;
      Objects.requireNonNull(var10002);
      gauges.put("peak.count", var10002::getPeakThreadCount);
      var10002 = this.threads;
      Objects.requireNonNull(var10002);
      gauges.put("total_started.count", var10002::getTotalStartedThreadCount);
      gauges.put("deadlock.count", (Gauge)() -> this.deadlockDetector.getDeadlockedThreads().size());
      ThreadDeadlockDetector var9 = this.deadlockDetector;
      Objects.requireNonNull(var9);
      gauges.put("deadlocks", var9::getDeadlockedThreads);
      return Collections.unmodifiableMap(gauges);
   }

   private int getThreadCount(Thread.State state) {
      ThreadInfo[] allThreads = this.getThreadInfo();
      int count = 0;

      for(ThreadInfo info : allThreads) {
         if (info != null && info.getThreadState() == state) {
            ++count;
         }
      }

      return count;
   }

   ThreadInfo[] getThreadInfo() {
      return this.threads.getThreadInfo(this.threads.getAllThreadIds(), 0);
   }
}
