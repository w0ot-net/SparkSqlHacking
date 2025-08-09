package org.apache.hadoop.hive.common;

import org.apache.hadoop.metrics2.MetricsInfo;
import org.apache.hive.common.guava.Objects;

public enum JvmMetricsInfo implements MetricsInfo {
   JvmMetrics("JVM related metrics etc."),
   MemNonHeapUsedM("Non-heap memory used in MB"),
   MemNonHeapCommittedM("Non-heap memory committed in MB"),
   MemNonHeapMaxM("Non-heap memory max in MB"),
   MemHeapUsedM("Heap memory used in MB"),
   MemHeapCommittedM("Heap memory committed in MB"),
   MemHeapMaxM("Heap memory max in MB"),
   MemMaxM("Max memory size in MB"),
   GcCount("Total GC count"),
   GcTimeMillis("Total GC time in milliseconds"),
   ThreadsNew("Number of new threads"),
   ThreadsRunnable("Number of runnable threads"),
   ThreadsBlocked("Number of blocked threads"),
   ThreadsWaiting("Number of waiting threads"),
   ThreadsTimedWaiting("Number of timed waiting threads"),
   ThreadsTerminated("Number of terminated threads"),
   LogFatal("Total number of fatal log events"),
   LogError("Total number of error log events"),
   LogWarn("Total number of warning log events"),
   LogInfo("Total number of info log events"),
   GcNumWarnThresholdExceeded("Number of times that the GC warn threshold is exceeded"),
   GcNumInfoThresholdExceeded("Number of times that the GC info threshold is exceeded"),
   GcTotalExtraSleepTime("Total GC extra sleep time in milliseconds");

   private final String desc;

   private JvmMetricsInfo(String desc) {
      this.desc = desc;
   }

   public String description() {
      return this.desc;
   }

   public String toString() {
      return Objects.toStringHelper((Object)this).add("name", this.name()).add("description", this.desc).toString();
   }
}
