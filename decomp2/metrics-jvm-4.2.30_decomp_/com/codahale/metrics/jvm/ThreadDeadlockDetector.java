package com.codahale.metrics.jvm;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ThreadDeadlockDetector {
   private static final int MAX_STACK_TRACE_DEPTH = 100;
   private final ThreadMXBean threads;

   public ThreadDeadlockDetector() {
      this(ManagementFactory.getThreadMXBean());
   }

   public ThreadDeadlockDetector(ThreadMXBean threads) {
      this.threads = threads;
   }

   public Set getDeadlockedThreads() {
      long[] ids = this.threads.findDeadlockedThreads();
      if (ids == null) {
         return Collections.emptySet();
      } else {
         Set<String> deadlocks = new HashSet();

         for(ThreadInfo info : this.threads.getThreadInfo(ids, 100)) {
            StringBuilder stackTrace = new StringBuilder();

            for(StackTraceElement element : info.getStackTrace()) {
               stackTrace.append("\t at ").append(element.toString()).append(String.format("%n"));
            }

            deadlocks.add(String.format("%s locked on %s (owned by %s):%n%s", info.getThreadName(), info.getLockName(), info.getLockOwnerName(), stackTrace.toString()));
         }

         return Collections.unmodifiableSet(deadlocks);
      }
   }
}
