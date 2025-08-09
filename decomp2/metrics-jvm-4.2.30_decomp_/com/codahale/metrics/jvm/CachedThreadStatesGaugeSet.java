package com.codahale.metrics.jvm;

import com.codahale.metrics.CachedGauge;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.TimeUnit;

public class CachedThreadStatesGaugeSet extends ThreadStatesGaugeSet {
   private final CachedGauge threadInfo;

   public CachedThreadStatesGaugeSet(final ThreadMXBean threadMXBean, ThreadDeadlockDetector deadlockDetector, long interval, TimeUnit unit) {
      super(threadMXBean, deadlockDetector);
      this.threadInfo = new CachedGauge(interval, unit) {
         protected ThreadInfo[] loadValue() {
            return CachedThreadStatesGaugeSet.super.getThreadInfo();
         }
      };
   }

   public CachedThreadStatesGaugeSet(long interval, TimeUnit unit) {
      this(ManagementFactory.getThreadMXBean(), new ThreadDeadlockDetector(), interval, unit);
   }

   ThreadInfo[] getThreadInfo() {
      return (ThreadInfo[])this.threadInfo.getValue();
   }
}
