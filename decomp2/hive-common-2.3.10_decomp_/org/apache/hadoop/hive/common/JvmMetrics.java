package org.apache.hadoop.hive.common;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.hadoop.log.metrics.EventCounter;
import org.apache.hadoop.metrics2.MetricsCollector;
import org.apache.hadoop.metrics2.MetricsInfo;
import org.apache.hadoop.metrics2.MetricsRecordBuilder;
import org.apache.hadoop.metrics2.MetricsSource;
import org.apache.hadoop.metrics2.MetricsSystem;
import org.apache.hadoop.metrics2.impl.MsInfo;
import org.apache.hadoop.metrics2.lib.DefaultMetricsSystem;
import org.apache.hadoop.metrics2.lib.Interns;

public class JvmMetrics implements MetricsSource {
   static final float M = 1048576.0F;
   final MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
   final List gcBeans = ManagementFactory.getGarbageCollectorMXBeans();
   final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
   final String processName;
   final String sessionId;
   private JvmPauseMonitor pauseMonitor = null;
   final ConcurrentHashMap gcInfoCache = new ConcurrentHashMap();

   JvmMetrics(String processName, String sessionId) {
      this.processName = processName;
      this.sessionId = sessionId;
   }

   public void setPauseMonitor(JvmPauseMonitor pauseMonitor) {
      this.pauseMonitor = pauseMonitor;
   }

   public static JvmMetrics create(String processName, String sessionId, MetricsSystem ms) {
      return (JvmMetrics)ms.register(JvmMetricsInfo.JvmMetrics.name(), JvmMetricsInfo.JvmMetrics.description(), new JvmMetrics(processName, sessionId));
   }

   public static JvmMetrics initSingleton(String processName, String sessionId) {
      return JvmMetrics.Singleton.INSTANCE.init(processName, sessionId);
   }

   public void getMetrics(MetricsCollector collector, boolean all) {
      MetricsRecordBuilder rb = collector.addRecord(JvmMetricsInfo.JvmMetrics).setContext("jvm").tag(MsInfo.ProcessName, this.processName).tag(MsInfo.SessionId, this.sessionId);
      this.getMemoryUsage(rb);
      this.getGcUsage(rb);
      this.getThreadUsage(rb);
      this.getEventCounters(rb);
   }

   private void getMemoryUsage(MetricsRecordBuilder rb) {
      MemoryUsage memNonHeap = this.memoryMXBean.getNonHeapMemoryUsage();
      MemoryUsage memHeap = this.memoryMXBean.getHeapMemoryUsage();
      Runtime runtime = Runtime.getRuntime();
      rb.addGauge(JvmMetricsInfo.MemNonHeapUsedM, (float)memNonHeap.getUsed() / 1048576.0F).addGauge(JvmMetricsInfo.MemNonHeapCommittedM, (float)memNonHeap.getCommitted() / 1048576.0F).addGauge(JvmMetricsInfo.MemNonHeapMaxM, (float)memNonHeap.getMax() / 1048576.0F).addGauge(JvmMetricsInfo.MemHeapUsedM, (float)memHeap.getUsed() / 1048576.0F).addGauge(JvmMetricsInfo.MemHeapCommittedM, (float)memHeap.getCommitted() / 1048576.0F).addGauge(JvmMetricsInfo.MemHeapMaxM, (float)memHeap.getMax() / 1048576.0F).addGauge(JvmMetricsInfo.MemMaxM, (float)runtime.maxMemory() / 1048576.0F);
   }

   private void getGcUsage(MetricsRecordBuilder rb) {
      long count = 0L;
      long timeMillis = 0L;

      for(GarbageCollectorMXBean gcBean : this.gcBeans) {
         long c = gcBean.getCollectionCount();
         long t = gcBean.getCollectionTime();
         MetricsInfo[] gcInfo = this.getGcInfo(gcBean.getName());
         rb.addCounter(gcInfo[0], c).addCounter(gcInfo[1], t);
         count += c;
         timeMillis += t;
      }

      rb.addCounter(JvmMetricsInfo.GcCount, count).addCounter(JvmMetricsInfo.GcTimeMillis, timeMillis);
      if (this.pauseMonitor != null) {
         rb.addCounter(JvmMetricsInfo.GcNumWarnThresholdExceeded, this.pauseMonitor.getNumGcWarnThreadholdExceeded());
         rb.addCounter(JvmMetricsInfo.GcNumInfoThresholdExceeded, this.pauseMonitor.getNumGcInfoThresholdExceeded());
         rb.addCounter(JvmMetricsInfo.GcTotalExtraSleepTime, this.pauseMonitor.getTotalGcExtraSleepTime());
      }

   }

   private MetricsInfo[] getGcInfo(String gcName) {
      MetricsInfo[] gcInfo = (MetricsInfo[])this.gcInfoCache.get(gcName);
      if (gcInfo == null) {
         gcInfo = new MetricsInfo[]{Interns.info("GcCount" + gcName, "GC Count for " + gcName), Interns.info("GcTimeMillis" + gcName, "GC Time for " + gcName)};
         MetricsInfo[] previousGcInfo = (MetricsInfo[])this.gcInfoCache.putIfAbsent(gcName, gcInfo);
         if (previousGcInfo != null) {
            return previousGcInfo;
         }
      }

      return gcInfo;
   }

   private void getThreadUsage(MetricsRecordBuilder rb) {
      int threadsNew = 0;
      int threadsRunnable = 0;
      int threadsBlocked = 0;
      int threadsWaiting = 0;
      int threadsTimedWaiting = 0;
      int threadsTerminated = 0;
      long[] threadIds = this.threadMXBean.getAllThreadIds();

      for(ThreadInfo threadInfo : this.threadMXBean.getThreadInfo(threadIds, 0)) {
         if (threadInfo != null) {
            switch (threadInfo.getThreadState()) {
               case NEW:
                  ++threadsNew;
                  break;
               case RUNNABLE:
                  ++threadsRunnable;
                  break;
               case BLOCKED:
                  ++threadsBlocked;
                  break;
               case WAITING:
                  ++threadsWaiting;
                  break;
               case TIMED_WAITING:
                  ++threadsTimedWaiting;
                  break;
               case TERMINATED:
                  ++threadsTerminated;
            }
         }
      }

      rb.addGauge(JvmMetricsInfo.ThreadsNew, threadsNew).addGauge(JvmMetricsInfo.ThreadsRunnable, threadsRunnable).addGauge(JvmMetricsInfo.ThreadsBlocked, threadsBlocked).addGauge(JvmMetricsInfo.ThreadsWaiting, threadsWaiting).addGauge(JvmMetricsInfo.ThreadsTimedWaiting, threadsTimedWaiting).addGauge(JvmMetricsInfo.ThreadsTerminated, threadsTerminated);
   }

   private void getEventCounters(MetricsRecordBuilder rb) {
      rb.addCounter(JvmMetricsInfo.LogFatal, EventCounter.getFatal()).addCounter(JvmMetricsInfo.LogError, EventCounter.getError()).addCounter(JvmMetricsInfo.LogWarn, EventCounter.getWarn()).addCounter(JvmMetricsInfo.LogInfo, EventCounter.getInfo());
   }

   static enum Singleton {
      INSTANCE;

      JvmMetrics impl;

      synchronized JvmMetrics init(String processName, String sessionId) {
         if (this.impl == null) {
            this.impl = JvmMetrics.create(processName, sessionId, DefaultMetricsSystem.instance());
         }

         return this.impl;
      }
   }
}
