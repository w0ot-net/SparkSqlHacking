package io.vertx.core.impl;

import io.netty.util.concurrent.FastThreadLocalThread;
import io.vertx.core.impl.btc.ThreadInfo;
import java.util.concurrent.TimeUnit;

public class VertxThread extends FastThreadLocalThread {
   private final boolean worker;
   final ThreadInfo info;
   ContextInternal context;
   ClassLoader topLevelTCCL;

   public VertxThread(Runnable target, String name, boolean worker, long maxExecTime, TimeUnit maxExecTimeUnit) {
      super(target, name);
      this.worker = worker;
      this.info = new ThreadInfo(maxExecTimeUnit, maxExecTime);
   }

   ContextInternal context() {
      return this.context;
   }

   void executeStart() {
      if (this.context == null) {
         this.info.startTime = System.nanoTime();
      }

   }

   void executeEnd() {
      if (this.context == null) {
         this.info.startTime = 0L;
      }

   }

   public long startTime() {
      return this.info.startTime;
   }

   public boolean isWorker() {
      return this.worker;
   }

   public long maxExecTime() {
      return this.info.maxExecTime;
   }

   public TimeUnit maxExecTimeUnit() {
      return this.info.maxExecTimeUnit;
   }
}
