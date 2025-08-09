package org.apache.zookeeper.server.util;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.zookeeper.server.ServerConfig;
import org.apache.zookeeper.server.ServerMetrics;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JvmPauseMonitor {
   private static final Logger LOG = LoggerFactory.getLogger(JvmPauseMonitor.class);
   public static final String JVM_PAUSE_MONITOR_FEATURE_SWITCH_KEY = "jvm.pause.monitor";
   protected long sleepTimeMs;
   public static final String SLEEP_TIME_MS_KEY = "jvm.pause.sleep.time.ms";
   public static final long SLEEP_TIME_MS_DEFAULT = 500L;
   protected long warnThresholdMs;
   public static final String WARN_THRESHOLD_KEY = "jvm.pause.warn-threshold.ms";
   public static final long WARN_THRESHOLD_DEFAULT = 10000L;
   protected long infoThresholdMs;
   public static final String INFO_THRESHOLD_KEY = "jvm.pause.info-threshold.ms";
   public static final long INFO_THRESHOLD_DEFAULT = 1000L;
   private long numGcWarnThresholdExceeded = 0L;
   private long numGcInfoThresholdExceeded = 0L;
   private long totalGcExtraSleepTime = 0L;
   private Thread monitorThread;
   private volatile boolean shouldRun = true;

   public JvmPauseMonitor(QuorumPeerConfig config) {
      this.warnThresholdMs = config.getJvmPauseWarnThresholdMs();
      this.infoThresholdMs = config.getJvmPauseInfoThresholdMs();
      this.sleepTimeMs = config.getJvmPauseSleepTimeMs();
   }

   public JvmPauseMonitor(ServerConfig config) {
      this.warnThresholdMs = config.getJvmPauseWarnThresholdMs();
      this.infoThresholdMs = config.getJvmPauseInfoThresholdMs();
      this.sleepTimeMs = config.getJvmPauseSleepTimeMs();
   }

   public void serviceStart() {
      this.monitorThread = new Thread(new JVMMonitor());
      this.monitorThread.setDaemon(true);
      this.monitorThread.start();
   }

   public void serviceStop() {
      this.shouldRun = false;
      if (this.monitorThread != null) {
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

   public long getNumGcWarnThresholdExceeded() {
      return this.numGcWarnThresholdExceeded;
   }

   public long getNumGcInfoThresholdExceeded() {
      return this.numGcInfoThresholdExceeded;
   }

   public long getTotalGcExtraSleepTime() {
      return this.totalGcExtraSleepTime;
   }

   private String formatMessage(long extraSleepTime, Map gcTimesAfterSleep, Map gcTimesBeforeSleep) {
      Set<String> gcBeanNames = new HashSet(gcTimesAfterSleep.keySet());
      gcBeanNames.retainAll(gcTimesBeforeSleep.keySet());
      List<String> gcDiffs = new ArrayList();

      for(String name : gcBeanNames) {
         GcTimes diff = ((GcTimes)gcTimesAfterSleep.get(name)).subtract((GcTimes)gcTimesBeforeSleep.get(name));
         if (diff.gcCount != 0L) {
            gcDiffs.add("GC pool '" + name + "' had collection(s): " + diff.toString());
         }
      }

      String ret = String.format("Detected pause in JVM or host machine (eg GC): pause of approximately %d ms, total pause: info level: %d, warn level: %d %n", extraSleepTime, this.numGcInfoThresholdExceeded, this.numGcWarnThresholdExceeded);
      if (gcDiffs.isEmpty()) {
         ret = ret + "No GCs detected";
      } else {
         ret = ret + String.join("\n", gcDiffs);
      }

      return ret;
   }

   private Map getGcTimes() {
      Map<String, GcTimes> map = new HashMap();

      for(GarbageCollectorMXBean gcBean : ManagementFactory.getGarbageCollectorMXBeans()) {
         map.put(gcBean.getName(), new GcTimes(gcBean));
      }

      return map;
   }

   private static class GcTimes {
      private long gcCount;
      private long gcTimeMillis;

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

   private class JVMMonitor implements Runnable {
      private JVMMonitor() {
      }

      public void run() {
         Map<String, GcTimes> gcTimesBeforeSleep = JvmPauseMonitor.this.getGcTimes();
         JvmPauseMonitor.LOG.info("Starting JVM Pause Monitor with infoThresholdMs:{} warnThresholdMs:{} and sleepTimeMs:{}", new Object[]{JvmPauseMonitor.this.infoThresholdMs, JvmPauseMonitor.this.warnThresholdMs, JvmPauseMonitor.this.sleepTimeMs});

         while(JvmPauseMonitor.this.shouldRun) {
            long startTime = Instant.now().toEpochMilli();

            try {
               Thread.sleep(JvmPauseMonitor.this.sleepTimeMs);
            } catch (InterruptedException var9) {
               return;
            }

            long endTime = Instant.now().toEpochMilli();
            long extraSleepTime = endTime - startTime - JvmPauseMonitor.this.sleepTimeMs;
            if (extraSleepTime >= 0L) {
               ServerMetrics.getMetrics().JVM_PAUSE_TIME.add(extraSleepTime);
            }

            Map<String, GcTimes> gcTimesAfterSleep = JvmPauseMonitor.this.getGcTimes();
            if (extraSleepTime > JvmPauseMonitor.this.warnThresholdMs) {
               ++JvmPauseMonitor.this.numGcWarnThresholdExceeded;
               JvmPauseMonitor.LOG.warn(JvmPauseMonitor.this.formatMessage(extraSleepTime, gcTimesAfterSleep, gcTimesBeforeSleep));
            } else if (extraSleepTime > JvmPauseMonitor.this.infoThresholdMs) {
               ++JvmPauseMonitor.this.numGcInfoThresholdExceeded;
               JvmPauseMonitor.LOG.info(JvmPauseMonitor.this.formatMessage(extraSleepTime, gcTimesAfterSleep, gcTimesBeforeSleep));
            }

            JvmPauseMonitor.this.totalGcExtraSleepTime = extraSleepTime;
            gcTimesBeforeSleep = gcTimesAfterSleep;
         }

      }
   }
}
