package io.vertx.core.impl.btc;

public class BlockedThreadEvent {
   private final Thread thread;
   private final long maxExecTime;
   private final long duration;
   private final long warningExceptionTime;

   public BlockedThreadEvent(Thread thread, long duration, long maxExecTime, long warningExceptionTime) {
      this.thread = thread;
      this.duration = duration;
      this.maxExecTime = maxExecTime;
      this.warningExceptionTime = warningExceptionTime;
   }

   public Thread thread() {
      return this.thread;
   }

   public long maxExecTime() {
      return this.maxExecTime;
   }

   public long duration() {
      return this.duration;
   }

   public long warningExceptionTime() {
      return this.warningExceptionTime;
   }
}
