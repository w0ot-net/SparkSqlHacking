package com.codahale.metrics.jvm;

import com.codahale.metrics.Clock;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class CpuTimeClock extends Clock {
   private static final ThreadMXBean THREAD_MX_BEAN = ManagementFactory.getThreadMXBean();

   public long getTick() {
      return THREAD_MX_BEAN.getCurrentThreadCpuTime();
   }
}
