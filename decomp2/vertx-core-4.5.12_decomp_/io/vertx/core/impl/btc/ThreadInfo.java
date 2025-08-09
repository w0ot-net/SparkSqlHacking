package io.vertx.core.impl.btc;

import java.util.concurrent.TimeUnit;

public final class ThreadInfo {
   public long startTime;
   public final TimeUnit maxExecTimeUnit;
   public final long maxExecTime;

   public ThreadInfo(TimeUnit maxExecTimeUnit, long maxExecTime) {
      this.maxExecTimeUnit = maxExecTimeUnit;
      this.maxExecTime = maxExecTime;
   }
}
