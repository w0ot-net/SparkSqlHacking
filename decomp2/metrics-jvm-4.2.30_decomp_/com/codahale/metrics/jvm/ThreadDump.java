package com.codahale.metrics.jvm;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.Thread.State;
import java.lang.management.LockInfo;
import java.lang.management.MonitorInfo;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.nio.charset.StandardCharsets;

public class ThreadDump {
   private final ThreadMXBean threadMXBean;

   public ThreadDump(ThreadMXBean threadMXBean) {
      this.threadMXBean = threadMXBean;
   }

   public void dump(OutputStream out) {
      this.dump(true, true, out);
   }

   public void dump(boolean lockedMonitors, boolean lockedSynchronizers, OutputStream out) {
      ThreadInfo[] threads = this.threadMXBean.dumpAllThreads(lockedMonitors, lockedSynchronizers);
      PrintWriter writer = new PrintWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));

      for(int ti = threads.length - 1; ti >= 0; --ti) {
         ThreadInfo t = threads[ti];
         writer.printf("\"%s\" id=%d state=%s", t.getThreadName(), t.getThreadId(), t.getThreadState());
         LockInfo lock = t.getLockInfo();
         if (lock != null && t.getThreadState() != State.BLOCKED) {
            writer.printf("%n    - waiting on <0x%08x> (a %s)", lock.getIdentityHashCode(), lock.getClassName());
            writer.printf("%n    - locked <0x%08x> (a %s)", lock.getIdentityHashCode(), lock.getClassName());
         } else if (lock != null && t.getThreadState() == State.BLOCKED) {
            writer.printf("%n    - waiting to lock <0x%08x> (a %s)", lock.getIdentityHashCode(), lock.getClassName());
         }

         if (t.isSuspended()) {
            writer.print(" (suspended)");
         }

         if (t.isInNative()) {
            writer.print(" (running in native)");
         }

         writer.println();
         if (t.getLockOwnerName() != null) {
            writer.printf("     owned by %s id=%d%n", t.getLockOwnerName(), t.getLockOwnerId());
         }

         StackTraceElement[] elements = t.getStackTrace();
         MonitorInfo[] monitors = t.getLockedMonitors();

         for(int i = 0; i < elements.length; ++i) {
            StackTraceElement element = elements[i];
            writer.printf("    at %s%n", element);

            for(int j = 1; j < monitors.length; ++j) {
               MonitorInfo monitor = monitors[j];
               if (monitor.getLockedStackDepth() == i) {
                  writer.printf("      - locked %s%n", monitor);
               }
            }
         }

         writer.println();
         LockInfo[] locks = t.getLockedSynchronizers();
         if (locks.length > 0) {
            writer.printf("    Locked synchronizers: count = %d%n", locks.length);

            for(LockInfo l : locks) {
               writer.printf("      - %s%n", l);
            }

            writer.println();
         }
      }

      writer.println();
      writer.flush();
   }
}
