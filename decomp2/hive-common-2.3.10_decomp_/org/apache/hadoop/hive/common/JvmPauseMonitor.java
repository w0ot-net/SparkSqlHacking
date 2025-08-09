package org.apache.hadoop.hive.common;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.common.metrics.common.Metrics;
import org.apache.hadoop.hive.common.metrics.common.MetricsFactory;
import org.apache.hadoop.util.Daemon;
import org.apache.hive.common.guava.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JvmPauseMonitor {
   private static final Logger LOG = LoggerFactory.getLogger(JvmPauseMonitor.class);
   private static final long SLEEP_INTERVAL_MS = 500L;
   private final long warnThresholdMs;
   private static final String WARN_THRESHOLD_KEY = "jvm.pause.warn-threshold.ms";
   private static final long WARN_THRESHOLD_DEFAULT = 10000L;
   private final long infoThresholdMs;
   private static final String INFO_THRESHOLD_KEY = "jvm.pause.info-threshold.ms";
   private static final long INFO_THRESHOLD_DEFAULT = 1000L;
   private long numGcWarnThresholdExceeded = 0L;
   private long numGcInfoThresholdExceeded = 0L;
   private long totalGcExtraSleepTime = 0L;
   private Thread monitorThread;
   private volatile boolean shouldRun = true;

   public JvmPauseMonitor(Configuration conf) {
      this.warnThresholdMs = conf.getLong("jvm.pause.warn-threshold.ms", 10000L);
      this.infoThresholdMs = conf.getLong("jvm.pause.info-threshold.ms", 1000L);
   }

   public void start() {
      Preconditions.checkState(this.monitorThread == null, "JvmPauseMonitor thread is Already started");
      this.monitorThread = new Daemon(new Monitor());
      this.monitorThread.start();
   }

   public void stop() {
      this.shouldRun = false;
      if (this.isStarted()) {
         this.monitorThread.interrupt();

         try {
            this.monitorThread.join();
         } catch (InterruptedException var2) {
            Thread.currentThread().interrupt();
         }
      }

   }

   public boolean isStarted() {
      return this.monitorThread != null;
   }

   public long getNumGcWarnThreadholdExceeded() {
      return this.numGcWarnThresholdExceeded;
   }

   public long getNumGcInfoThresholdExceeded() {
      return this.numGcInfoThresholdExceeded;
   }

   public long getTotalGcExtraSleepTime() {
      return this.totalGcExtraSleepTime;
   }

   private String formatMessage(long extraSleepTime, Map gcTimesAfterSleep, Map gcTimesBeforeSleep) {
      Set<String> gcBeanNames = Sets.intersection(gcTimesAfterSleep.keySet(), gcTimesBeforeSleep.keySet());
      List<String> gcDiffs = Lists.newArrayList();

      for(String name : gcBeanNames) {
         GcTimes diff = ((GcTimes)gcTimesAfterSleep.get(name)).subtract((GcTimes)gcTimesBeforeSleep.get(name));
         if (diff.gcCount != 0L) {
            gcDiffs.add("GC pool '" + name + "' had collection(s): " + diff.toString());
         }
      }

      String ret = "Detected pause in JVM or host machine (eg GC): pause of approximately " + extraSleepTime + "ms\n";
      if (gcDiffs.isEmpty()) {
         ret = ret + "No GCs detected";
      } else {
         ret = ret + Joiner.on("\n").join(gcDiffs);
      }

      return ret;
   }

   private Map getGcTimes() {
      Map<String, GcTimes> map = Maps.newHashMap();

      for(GarbageCollectorMXBean gcBean : ManagementFactory.getGarbageCollectorMXBeans()) {
         map.put(gcBean.getName(), new GcTimes(gcBean));
      }

      return map;
   }

   public static void main(String[] args) throws Exception {
      (new JvmPauseMonitor(new Configuration())).start();
      List<String> list = Lists.newArrayList();
      int i = 0;

      while(true) {
         list.add(String.valueOf(i++));
      }
   }

   private static class GcTimes {
      private final long gcCount;
      private final long gcTimeMillis;

      private GcTimes(GarbageCollectorMXBean gcBean) {
         this.gcCount = gcBean.getCollectionCount();
         this.gcTimeMillis = gcBean.getCollectionTime();
      }

      private GcTimes(long count, long time) {
         this.gcCount = count;
         this.gcTimeMillis = time;
      }

      private GcTimes subtract(GcTimes other) {
         return new GcTimes(this.gcCount - other.gcCount, this.gcTimeMillis - other.gcTimeMillis);
      }

      public String toString() {
         return "count=" + this.gcCount + " time=" + this.gcTimeMillis + "ms";
      }
   }

   private class Monitor implements Runnable {
      private Monitor() {
      }

      public void run() {
         Stopwatch sw = new Stopwatch();

         Map<String, GcTimes> gcTimesAfterSleep;
         for(Map<String, GcTimes> gcTimesBeforeSleep = JvmPauseMonitor.this.getGcTimes(); JvmPauseMonitor.this.shouldRun; gcTimesBeforeSleep = gcTimesAfterSleep) {
            sw.reset().start();

            try {
               Thread.sleep(500L);
            } catch (InterruptedException var6) {
               return;
            }

            long extraSleepTime = sw.elapsed(TimeUnit.MILLISECONDS) - 500L;
            gcTimesAfterSleep = JvmPauseMonitor.this.getGcTimes();
            if (extraSleepTime > JvmPauseMonitor.this.warnThresholdMs) {
               ++JvmPauseMonitor.this.numGcWarnThresholdExceeded;
               JvmPauseMonitor.LOG.warn(JvmPauseMonitor.this.formatMessage(extraSleepTime, gcTimesAfterSleep, gcTimesBeforeSleep));
               this.incrementMetricsCounter("jvm.pause.warn-threshold", 1L);
            } else if (extraSleepTime > JvmPauseMonitor.this.infoThresholdMs) {
               ++JvmPauseMonitor.this.numGcInfoThresholdExceeded;
               JvmPauseMonitor.LOG.info(JvmPauseMonitor.this.formatMessage(extraSleepTime, gcTimesAfterSleep, gcTimesBeforeSleep));
               this.incrementMetricsCounter("jvm.pause.info-threshold", 1L);
            }

            this.incrementMetricsCounter("jvm.pause.extraSleepTime", extraSleepTime);
            JvmPauseMonitor.this.totalGcExtraSleepTime = JvmPauseMonitor.this.totalGcExtraSleepTime + extraSleepTime;
         }

      }

      private void incrementMetricsCounter(String name, long count) {
         Metrics metrics = MetricsFactory.getInstance();
         if (metrics != null) {
            try {
               metrics.incrementCounter(name, count);
            } catch (Exception e) {
               JvmPauseMonitor.LOG.warn("Error Reporting JvmPauseMonitor to Metrics system", e);
            }
         }

      }
   }
}
